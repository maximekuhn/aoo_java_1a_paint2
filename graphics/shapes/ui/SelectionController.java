package graphics.shapes.ui;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.toolbar.SelectionActions;
import graphics.ui.Controller;

public class SelectionController extends Controller {

	private SelectionActions actionMode = SelectionActions.SELECT;
	
	private Point lastClick;
	private boolean shiftDown;
	
	public SelectionController(Object newModel) {
		super(newModel);
		this.lastClick = new Point();
		this.shiftDown = false;
	}
	
	public void setActionMode(SelectionActions action) {
		this.actionMode = action;
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		this.lastClick.setLocation(e.getPoint());
		
		if(this.actionMode.equals(SelectionActions.SELECT)) this.doSelect(e);
		else if(this.actionMode.equals(SelectionActions.ERASE)) this.doErase(e);
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		this.lastClick.setLocation(e.getPoint());
	}
	
	@Override
	public void mouseDragged(MouseEvent evt)
	{	
		if(this.actionMode.equals(SelectionActions.TRANSLATE)) this.doTranslate(evt);
		
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
	
	private void doErase(MouseEvent e) {
		Shape s = this.getTarget();
		
		if(s == null) return;
		
		SCollection model = (SCollection) this.getModel();
		model.remove(s);
		
		this.getView().repaint();
	}
	
	public void doRotation() {
		// TODO : finish rotation
		SCollection model = (SCollection) this.getModel();
		Iterator<Shape> it = model.iterator();
		
		Shape s;
		while(it.hasNext()) {
			s = it.next();
			SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			if(sa == null) sa = new SelectionAttributes();
			if(sa.isSelected()) System.out.println("rotation");
		}
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
			if(s.getBounds().contains(lastClick))
				return s;
		}
		return null;
	}

}
