package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class SRectangle extends Shape {

	private Rectangle rect;
	
	public SRectangle(Point point, int width, int height) {
		this.rect = new Rectangle(point.x, point.y, width, height);
	}

	@Override
	public Point getLoc() {
		return new Point(this.rect.x, this.rect.y);
	}

	@Override
	public void setLoc(Point p) {
		this.rect.setLocation(p);
	}

	@Override
	public void translate(int dx, int dy) {
		this.rect.setLocation(this.rect.x + dx, this.rect.y + dy);
	}

	@Override
	public Rectangle getBounds() {
		return this.rect.getBounds();
	}

	@Override
	public void accept(ShapeVisitor v) {
		v.visitRectangle(this);
	}

}
