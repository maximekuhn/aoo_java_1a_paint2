package graphics.shapes;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

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

	@Override
	public Shape copy() {
		SImage si = new SImage(this.getLoc(), this.img);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		si.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		si.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes  ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		si.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		si.addAttributes(new LayerAttributes(la.getLayer()));
		return si;
	}

	@Override
	public void resize(int dx, int dy) {
		// TODO Auto-generated method stub
		
	}
	
}
