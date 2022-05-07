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
	private LinkedList<Double> doublePosx;
	private LinkedList<Double> doublePosy;
	
	public SSketch(Point loc) {
		this.loc = loc;
		this.points = new LinkedList<>();
		this.doublePosx = new LinkedList<>();
		this.doublePosy = new LinkedList<>();
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
		for(Point point : this.points) {
			point.translate(dx, dy);
			int index = this.points.indexOf(p);
			this.doublePosx.set(index, this.doublePosx.get(index)+dx);
			this.doublePosy.set(index, this.doublePosy.get(index)+dy);
		}
	}

	@Override
	public void translate(int dx, int dy) {
		for(Point p : this.points) {
			p.translate(dx, dy);
			int index = this.points.indexOf(p);
			this.doublePosx.set(index, this.doublePosx.get(index)+dx);
			this.doublePosy.set(index, this.doublePosy.get(index)+dy);
		}
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
		int locX = this.getLoc().x;
		int locY = this.getLoc().y;
		int boundX = this.getBounds().width;
		int boundY = this.getBounds().height;
		for(Point p : this.points) {
			int index = this.points.indexOf(p);
			
			this.doublePosx.set(index, this.doublePosx.get(index) + ((this.doublePosx.get(index)-locX)/boundX)*dx);
			this.doublePosy.set(index, this.doublePosy.get(index) + ((this.doublePosy.get(index)-locY)/boundY)*dy);
			p.translate((int)(this.doublePosx.get(index)-p.x), (int)(this.doublePosy.get(index)-p.y));
		}
	}
	
	public void addPoint(Point p) {
		this.points.add(p);
		this.doublePosx.add((double)p.x);
		this.doublePosy.add((double)p.y);
	}
	
	public LinkedList<Point> getPoints(){
		return this.points;
	}

}
