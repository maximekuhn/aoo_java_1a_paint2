package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

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

	@Override
	public Shape copy() {
		SRectangle sr = new SRectangle(this.getLoc(), this.getBounds().width, this.getBounds().height);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		sr.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		sr.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		sr.addAttributes(ra);
		return sr;
	}

	public void resize(int dx, int dy) {
		this.rect.setSize((int)this.rect.getWidth()+dx, (int)this.rect.getHeight()+dy);
	}
}
