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

	private static final int HANDLER_SIZE = 6;
	
	public ShapesController(Object model) {
		super(model);
		this.lastClick = new Point();
		this.shiftDown = false;
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
			if(sa.isSelected() && this.isHandlerSelected(s)==0) {
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
				if (this.isHandlerSelected(s)==1) {
					s.translate(dx, dy);
					s.resize(-dx, -dy);
					this.getView().repaint();
				}
				else if (this.isHandlerSelected(s)==2) {
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
