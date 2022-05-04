package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SCircle extends Shape {

	private int radius;
	private Point loc;
	
	public SCircle(Point p, int radius) {
		this.loc = p;
		this.radius = radius;
	}
	
	@Override
	public Point getLoc() {
		return this.loc;
	}

	@Override
	public void setLoc(Point p) {
		this.loc = new Point(p);
	}

	@Override
	public void translate(int dx, int dy) {
		this.loc.setLocation(this.loc.x + dx, this.loc.y + dy);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.loc.x, this.loc.y, this.radius, this.radius);
	}

	@Override
	public void accept(ShapeVisitor v) {
		v.visitCircle(this);
	}
	 
	public int getRadius() {
		return this.radius;
	}

	@Override
	public Shape copy() {
		SCircle sc = new SCircle(new Point(this.getLoc()), this.getRadius());
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		sc.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		sc.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		sc.addAttributes(new RotationAttributes(ra.getAngle()));
		return sc;
	}

	@Override
	public void resize(int dx, int dy) {
		if (dx > dy) {
			this.radius += dx;
		}
		else {
			this.radius += dy;
		}
	}
	
}