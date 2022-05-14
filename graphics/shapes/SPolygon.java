package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

public abstract class SPolygon extends Shape {

	private Point loc;
	
	protected Polygon polygon;
	
	protected int width;
	protected int height;
	
	protected int nPoints;
	protected int[] xPolygon;
	protected int[] yPolygon;
	
	public SPolygon(Point loc, int width, int height) {
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.buildPolygon();
	}
	
	public abstract void buildPolygon();
	
	@Override
	public Point getLoc() {
		return this.loc;
	}
	
	@Override
	public void setLoc(Point p) {
		this.loc.setLocation(p);
		this.buildPolygon();
	}
	
	@Override
	public void translate(int dx, int dy) {
		this.setLoc(new Point(this.loc.x + dx, this.loc.y + dy));
		this.buildPolygon();
	}
	
	@Override
	public Rectangle getBounds() {
		return this.polygon.getBounds();
	}
	
	@Override
	public void resize(int dx, int dy) {
		this.width += dx;
		this.height += dy;
		this.buildPolygon();
	}
	
	public Polygon getPolygon() {
		return this.polygon;
	}
	
}
