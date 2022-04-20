package graphics.shapes;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public class SImage extends Shape {

	private Rectangle rect;
	private Image img;
	
	public SImage(Point point, Image img) {
		this.rect = new Rectangle(point.x, point.y, img.getWidth(null), img.getHeight(null));
		this.img = img;
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
		v.visitImage(this);
	}
	
	public Image getImage() {
		return this.img;
	}
	
	public Rectangle getRect() {
		return this.rect;
	}

}
