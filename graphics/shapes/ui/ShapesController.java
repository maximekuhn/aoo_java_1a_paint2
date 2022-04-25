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
				s.translate(dx, dy);
				this.getView().repaint();
			}
		}
	}
	
}
