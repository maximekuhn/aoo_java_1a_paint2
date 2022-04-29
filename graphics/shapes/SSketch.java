package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resize(int dx, int dy) {
		// TODO Auto-generated method stub
		
	}
	
	public void addPoint(Point p) {
		this.points.add(p);
	}
	
	public LinkedList<Point> getPoints(){
		return this.points;
	}

}
