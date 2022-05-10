package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;

import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.STextBox;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.toolbar.SelectionActions;
import graphics.ui.Controller;

public class SelectionController extends Controller {
	
	private SelectionActions actionMode = SelectionActions.SELECT;
	
	private Point lastClick;
	private Point cursorPos;
	private boolean shiftDown;
	private boolean handlerSelected;
	private int handler;

	private static final int HANDLER_HITBOX = 16;
	private static final int MIN_SHAPE_SIZE = 10;
	
	private SRectangle selectionRectangle;
	
	public SelectionController(Object newModel) {
		super(newModel);
		this.lastClick = new Point();
		this.cursorPos = new Point();
		this.shiftDown = false;
		this.handlerSelected = false;
		this.handler = 0;
	}
	
	public void setActionMode(SelectionActions action) {
		this.actionMode = action;
	}
	
	public SelectionActions getActionMode() {
		return this.actionMode;
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		this.lastClick.setLocation(e.getPoint());
		
		if(this.actionMode.equals(SelectionActions.SELECT)) {
			this.doSelect(e);
			this.doTextCursor();
		}
		else if(this.actionMode.equals(SelectionActions.ERASE)) this.doErase(e);
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		this.lastClick.setLocation(e.getPoint());
		
		if(this.actionMode.equals(SelectionActions.SELECT)) this.doCreateSelectionRectangle(e);

		//TODO check if the cursor move slightly and count it as mouseClicked
	}
	
	@Override
	public void mouseDragged(MouseEvent evt)
	{	
		if(this.actionMode.equals(SelectionActions.TRANSLATE)) this.doTranslate(evt);
		else if(this.actionMode.equals(SelectionActions.SELECT)) this.doDraggingSelection(evt);
		else if(this.actionMode.equals(SelectionActions.RESIZE)) this.doResize(evt);
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		if(this.selectionRectangle != null) {
			((SCollection) this.getModel()).remove(this.selectionRectangle);
			this.selectionRectangle = null;
			this.getView().repaint();
		}
		this.handlerSelected = false;
	}

	public void mouseMoved(MouseEvent e) {
		this.cursorPos.setLocation(e.getPoint());
		if(this.actionMode.equals(SelectionActions.RESIZE) && !this.handlerSelected) this.doResizeCursor();
	}
	
	@Override
	public void keyPressed(KeyEvent evt)
	{
		if(evt.isShiftDown())
			this.shiftDown = true;
	}

	@Override
	public void keyReleased(KeyEvent evt)
	{
		if(evt.getKeyCode() == 16) // shift
			this.shiftDown = false;
	}

	public void keyTyped(KeyEvent e) {
		if(this.actionMode.equals(SelectionActions.SELECT)) this.doWriteText(e);
    }
	
	private void doWriteText(KeyEvent e) {
		SCollection model = (SCollection) this.getModel();
		Iterator<Shape> it = model.iterator();
		
		Shape s;
		while(it.hasNext()) {
			s = it.next();
			SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			if(sa == null) sa = new SelectionAttributes();
			
			if(sa.isSelected() && s.getClass().getName() == "graphics.shapes.STextBox") {
				STextBox tb = (STextBox) s;
				StringBuilder str = new StringBuilder();
				str.append(tb.getText());
				if ((int)e.getKeyChar() == 8)  {// backspace key
					if (str.length() > 0) str.deleteCharAt(str.length()-1); 
				}
				else if ((int)e.getKeyChar() == 10) { // newline key
					str.append(e.getKeyChar());
					str.append(' ');
				}
				else {  // other key
					if (str.toString().endsWith("\n ")) str.deleteCharAt(str.length()-1);
					str.append(e.getKeyChar());
				}
				tb.setText(str.toString());
			}
		}
		this.getView().repaint();
	}

	private void doCreateSelectionRectangle(MouseEvent e) {
		SRectangle selectRect = new SRectangle(e.getPoint(), 0, 0);
		selectRect.addAttributes(new SelectionAttributes());
		Color selectRectColor = new Color(Color.RED.getRed(), Color.RED.getBlue(), Color.RED.getGreen(), 16); // transparent color
		selectRect.addAttributes(new ColorAttributes(true, true, selectRectColor, Color.RED));
		selectRect.addAttributes(new LayerAttributes(((SCollection) this.getModel()).getLayerMax()));
		this.selectionRectangle = selectRect;
		((SCollection) this.getModel()).add(this.selectionRectangle);
	}
	
	private void doDraggingSelection(MouseEvent evt) {
		Rectangle bounds = new Rectangle(
				this.selectionRectangle.getLoc().x,
				this.selectionRectangle.getLoc().y,
				evt.getPoint().x - this.selectionRectangle.getLoc().x,
				evt.getPoint().y - this.selectionRectangle.getLoc().y);
	
	this.selectionRectangle.setBounds(bounds);
	this.doSelectWithSelectionRectangle();
	this.getView().repaint();
	}
	
	private void doSelectWithSelectionRectangle() {
		SCollection model = (SCollection) this.getModel();
		Iterator<Shape> iterator = model.iterator();
		
		Shape s;
		while(iterator.hasNext()) {
			s = iterator.next();
			if(s == this.selectionRectangle) continue;
			SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			if(sa == null) sa = new SelectionAttributes();
			if(this.selectionRectangle.getBounds().intersects(s.getBounds())) sa.select();
			else if(!this.shiftDown) sa.unselect();
		}
	}
		
	private void doSelect(MouseEvent e) {
		this.lastClick.setLocation(e.getPoint());
		
		Shape s = this.getTarget();
		if(!this.shiftDown) this.unselectAll();
		if(s != null) {
			SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			sa.toggleSelection();
		}
		this.getView().repaint();
		
	}
	
	private void doTranslate(MouseEvent evt) {
		int dx = evt.getX() - this.lastClick.x;
		int dy = evt.getY() - this.lastClick.y;
		
		this.translateSelected(dx, dy);
		
		this.lastClick.setLocation(evt.getPoint());
	}

	private void doResize(MouseEvent evt) {
		int dx = evt.getX() - this.lastClick.x;
		int dy = evt.getY() - this.lastClick.y;

		this.resizeSelected(dx, dy);

		this.lastClick.setLocation(evt.getPoint());
	}
	
	private void doErase(MouseEvent e) {
		Shape s = this.getTarget();
		
		if(s == null) return;
		
		SCollection model = (SCollection) this.getModel();
		model.remove(s);
		
		this.getView().repaint();
	}
	
	public void doRotation() {
		SCollection model = (SCollection) this.getModel();
		Iterator<Shape> it = model.iterator();
		
		Shape s;
		while(it.hasNext()) {
			s = it.next();
			SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			if(sa == null) sa = new SelectionAttributes();
			
			if(sa.isSelected()) {
				RotationAttributes ra = (RotationAttributes) s.getAttributes(RotationAttributes.ID);
				if(ra == null) {
					ra = new RotationAttributes();
					s.addAttributes(ra);
				}
				ra.rotate90Right();
			}
		}
		this.getView().repaint();
	}
	
	public void doGroup() {
		SCollection model = (SCollection) this.getModel();
		Iterator<Shape> it = model.iterator();
		
		LinkedList<Shape> shapesToGroup = new LinkedList<>();
		
		Shape s;
		while(it.hasNext()) {
			s = it.next();
			SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			if(sa == null) sa = new SelectionAttributes();
			if(sa.isSelected()) shapesToGroup.add(s);
		}
		
		SCollection group = new SCollection();
		if(shapesToGroup.size() == 0) return;
		for(Shape shape : shapesToGroup) {
			model.remove(shape);
			group.add(shape);
			SelectionAttributes satt = (SelectionAttributes) shape.getAttributes(SelectionAttributes.ID);
			if(satt == null) satt = new SelectionAttributes();
			satt.unselect();
		}
		group.addAttributes(new SelectionAttributes(true));
		
		model.add(group);
		
		this.getView().repaint();
	}
	
	public void doUngroup() {
		SCollection model = (SCollection) this.getModel();
		Iterator<Shape> iterator = model.iterator();
		
		LinkedList<Shape> separatedShapes = new LinkedList<>();
		LinkedList<SCollection> SCollectionToRemove = new LinkedList<>();
		
		Shape shape;
		while(iterator.hasNext()) {
			shape = iterator.next();
			
			SelectionAttributes sa = (SelectionAttributes) shape.getAttributes(SelectionAttributes.ID);
			if(sa == null) sa = new SelectionAttributes();
			
			if(sa.isSelected()) {
				// check if shape is an SCollection using model
				if(shape.getClass().getName().equals(model.getClass().getName())) {
					SCollection sc = (SCollection) shape;
					Iterator<Shape> it = sc.iterator();
					LinkedList<Shape> shapesToRemoveFromSC = new LinkedList<>();
					Shape s;
					while(it.hasNext()) {
						s = it.next();
						separatedShapes.add(s);
					}
					for(Shape str : shapesToRemoveFromSC)
						sc.remove(str);
					SCollectionToRemove.add(sc);
				}
			}
		}
		
		for(Shape sh : separatedShapes)
			model.add(sh);
		
		for(SCollection scollection : SCollectionToRemove)
			model.remove(scollection);
		
		this.getView().repaint();
	}

	private void translateSelected(int dx, int dy) {
		SCollection model = (SCollection) this.getModel();
		Iterator<Shape> it = model.iterator();
		
		while(it.hasNext()) {
			Shape s = it.next();
			SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			if(sa.isSelected()) {
				s.translate(dx, dy);
				this.getView().repaint();
			}
		}
	}

	private void resizeSelected(int dx, int dy) {
		SCollection model = (SCollection) this.getModel();
		Iterator<Shape> it = model.iterator();
		
		while(it.hasNext()) {
			Shape s = it.next();
			SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			if(sa.isSelected()) {
				if (!handlerSelected) {
					this.handler = this.isHandlerSelected(s);
					this.handlerSelected = true;
				}
				if (handler==1) {
					if (s.getBounds().width-dx < MIN_SHAPE_SIZE) {
						dx = 0;
					}
					if (s.getBounds().height-dy < MIN_SHAPE_SIZE ) {
						dy = 0;
					}
					s.translate(dx, dy);
					s.resize(-dx, -dy);
					this.getView().repaint();
				}
				else if (handler==2) {
					if (s.getBounds().width+dx < MIN_SHAPE_SIZE) {
						dx = 0;
					}
					if (s.getBounds().height+dy < MIN_SHAPE_SIZE) {
						dy = 0;
					}
					s.resize(dx, dy);
					this.getView().repaint();
				}
			}
		}
	}

	private int isHandlerSelected(Shape s) {
		int xleft = s.getBounds().x;
		int xright = s.getBounds().x + s.getBounds().width;
		int ytop = s.getBounds().y;
		int ybottom = s.getBounds().y + s.getBounds().height;
		if (this.cursorPos.x >= xleft-HANDLER_HITBOX && this.cursorPos.x <= xleft && this.cursorPos.y >= ytop-HANDLER_HITBOX && this.cursorPos.y <= ytop) {
			// if top left handler is selected
			return 1;
		}
		else if (this.cursorPos.x >= xright && this.cursorPos.x <= xright+HANDLER_HITBOX && this.cursorPos.y >= ybottom && this.cursorPos.y <= ybottom+HANDLER_HITBOX) {
			// if bottom right handler is selected
			return 2;
		}
		else {
			return 0;
		}
	}

	private void doResizeCursor() {
		SCollection model = (SCollection) this.getModel();
		Iterator<Shape> it = model.iterator();
		while(it.hasNext()) {
			Shape s = it.next();
			SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			if(sa.isSelected()) {
				int hand = this.isHandlerSelected(s);
				if (hand == 1) 
					this.getView().setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
				else if (hand == 2) 
					this.getView().setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
				else 
					this.getView().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}

	private void doTextCursor() {
		Shape s = this.getTarget();
		if(s != null) {
			SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			if(sa.isSelected() && s.getClass().getName() == "graphics.shapes.STextBox") 
				this.getView().setCursor(new Cursor(Cursor.TEXT_CURSOR));
		}
		else this.getView().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	private void unselectAll() {
		SCollection model = (SCollection) this.getModel();
		Iterator<Shape> it = model.iterator();
		
		while(it.hasNext()) {
			Shape s = it.next();
			SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			sa.unselect();
		}
	}

	private Shape getTarget() {
		SCollection model = (SCollection) this.getModel();
		Iterator<Shape> it = model.iterator();
		
		while(it.hasNext()) {
			Shape s = it.next();
			if(s.getBounds().contains(this.lastClick))
				return s;
		}
		return null;
	}

}
