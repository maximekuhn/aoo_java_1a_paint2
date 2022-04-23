package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SCollection extends Shape {

	private LinkedList<Shape> shapes;
	
	public SCollection() {
		super();
		this.shapes = new LinkedList<Shape>();
	}
	
	@Override
	public Point getLoc() {
		Point loc = new Point(this.shapes.get(0).getLoc());
		for(Shape s : this.shapes) {
			if(s.getLoc().x < loc.x && s.getLoc().y < loc.y) {
				loc.setLocation(s.getLoc());
			}
		}
		return loc;
	}

	@Override
	public void setLoc(Point p) {
		int dx = p.x - this.getLoc().x;
		int dy = p.y - this.getLoc().y;
		for(Shape s : this.shapes)
			s.translate(dx, dy);
	}

	@Override
	public void translate(int dx, int dy) {
		for(Shape s : this.shapes)
			s.translate(dx, dy);
	}

	@Override
	public Rectangle getBounds() {
		Rectangle bounds = new Rectangle(this.shapes.get(0).getBounds());
		for(Shape s : this.shapes)
			bounds.add(s.getBounds());
		return bounds;
	}

	@Override
	public void accept(ShapeVisitor v) {
		v.visitCollection(this);
	}
	
	public void add(Shape s) {
		this.shapes.add(s);
	}
	
	public void remove(Shape s) {
		this.shapes.remove(s);
	}
	
	public Iterator<Shape> iterator() {
		return this.shapes.iterator();
	}

	@Override
	public Shape copy() {
		// TODO : fix copy in SCollection
		SCollection sc = new SCollection();
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		sc.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		sc.addAttributes(new SelectionAttributes(sa.isSelected()));
		for(Shape shape : this.shapes)
			sc.add(shape.copy());
		return sc;
	}
	
}
