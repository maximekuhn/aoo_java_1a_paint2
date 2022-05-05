package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SSketch extends Shape {

	private Point loc;
	private LinkedList<Point> points;
	
	public SSketch(Point loc) {
		this.loc = loc;
		this.points = new LinkedList<>();
		this.addPoint(this.loc);
	}
	
	@Override
	public Point getLoc() {
		Point topLeft = this.loc;
		for(Point p : this.points) {
			if(p.x < topLeft.x && p.y < topLeft.y)
				topLeft = p;
		}
		return topLeft;
	}

	@Override
	public void setLoc(Point p) {
		int dx = p.x - this.getLoc().x;
		int dy = p.y - this.getLoc().y;
		for(Point point : this.points)
			point.translate(dx, dy);
	}

	@Override
	public void translate(int dx, int dy) {
		for(Point p : this.points)
			p.translate(dx, dy);
	}

	@Override
	public Rectangle getBounds() {
		Rectangle bounds = new Rectangle(this.getLoc().x, this.getLoc().y, 0, 0);
		Rectangle b;
		for(Point p : this.points) {
			b = new Rectangle(p.x, p.y, 0, 0);
			bounds.add(b);
		}
		return bounds;
	}

	@Override
	public void accept(ShapeVisitor v) {
		v.visitSSketch(this);
	}

	@Override
	public Shape copy() {
		SSketch sketch = new SSketch(new Point(this.getLoc()));
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		sketch.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		sketch.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		sketch.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		sketch.addAttributes(new LayerAttributes(la.getLayer()));
		
		for(Point p : this.points) {
			sketch.addPoint(new Point(p));
		}
		return sketch;
	}

	@Override
	public void resize(int dx, int dy) {
		// TODO: fix resize
		Point center = this.getCenter();
		int width = this.getBounds().width;
		int height = this.getBounds().height;
		
		for(Point p : this.points) {
			if(width != 0)
				p.x += (float) dx * (p.x - center.x) / width;
			if(height != 0)
				p.y += (float) dy * (p.y - center.y) / height;
		}
	}
	
	private Point getCenter() {
		return new Point((int) this.getBounds().getCenterX(), (int) this.getBounds().getCenterY());
	}
	
	public void addPoint(Point p) {
		this.points.add(p);
	}
	
	public LinkedList<Point> getPoints(){
		return this.points;
	}

}
