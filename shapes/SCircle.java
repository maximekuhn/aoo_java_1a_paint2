package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

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
		this.loc.setLocation(p);
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

}
