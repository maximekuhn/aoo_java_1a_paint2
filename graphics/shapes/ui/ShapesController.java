package graphics.shapes.ui;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

public class ShapesController extends Controller {
	
	private Point lastClick;
	private boolean shiftDown;
	private boolean handlerSelected;
	private int handler;

	private static final int HANDLER_SIZE = 12;
	private static final int MIN_SHAPE_SIZE = 6;
	
	public ShapesController(Object model) {
		super(model);
		this.lastClick = new Point();
		this.shiftDown = false;
		this.handlerSelected = false;
		this.handler = 0;
	}
	
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		this.lastClick.setLocation(e.getPoint());
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		this.lastClick.setLocation(e.getPoint());
		
		Shape s = this.getTarget();
		if(!this.shiftDown) this.unselectAll();
		if(s != null) {
			SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			sa.toggleSelection();
		}
		this.getView().repaint();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		this.handlerSelected = false;
	}
	
	@Override
	public void mouseDragged(MouseEvent evt)
	{
		int dx = evt.getX() - this.lastClick.x;
		int dy = evt.getY() - this.lastClick.y;
		
		this.translateSelected(dx, dy);
		this.resizeSelected(dx, dy);
		
		this.lastClick.setLocation(evt.getPoint());
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
	
	private void unselectAll() {
		SCollection model = (SCollection) this.getModel();
		Iterator<Shape> it = model.iterator();
		
		while(it.hasNext()) {
			Shape s = it.next();
			SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			sa.unselect();
		}
	}
	
	private void translateSelected(int dx, int dy) {
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
				if (this.handler==0) {
					s.translate(dx, dy);
					this.getView().repaint();
				}
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
					if (s.getBounds().width-dx < MIN_SHAPE_SIZE && dx > 0) {
						dx = 0;
					}
					if (s.getBounds().height-dy < MIN_SHAPE_SIZE && dy > 0) {
						dy = 0;
					}
					s.translate(dx, dy);
					s.resize(-dx, -dy);
					this.getView().repaint();
				}
				else if (handler==2) {
					if (s.getBounds().width-dx < MIN_SHAPE_SIZE && dx < 0) {
						dx = 0;
					}
					if (s.getBounds().height-dy < MIN_SHAPE_SIZE && dy < 0) {
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
		if (this.lastClick.x >= xleft-HANDLER_SIZE && this.lastClick.x <= xleft && this.lastClick.y >= ytop-HANDLER_SIZE && this.lastClick.y <= ytop) {
			// if top left handler is selected
			return 1;
		}
		else if (this.lastClick.x >= xright && this.lastClick.x <= xright+HANDLER_SIZE && this.lastClick.y >= ybottom && this.lastClick.y <= ybottom+HANDLER_SIZE) {
			// if bottom right handler is selected
			return 2;
		}
		else {
			return 0;
		}
	}
}
