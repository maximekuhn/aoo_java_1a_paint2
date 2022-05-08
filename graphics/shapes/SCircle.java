	package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
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
	
	public void setRadius(int radius) {
		this.radius = radius;
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
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		sc.addAttributes(new LayerAttributes(la.getLayer()));
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
	
	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		SCircle sc = (SCircle) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");
		
		// basic
		tmp.add(sc.getClass().getName());
		tmp.add(String.valueOf(sc.getLoc().x));
		tmp.add(String.valueOf(sc.getLoc().y));
		tmp.add(String.valueOf(sc.getRadius()));
		
		// color attributes
		ColorAttributes ca = (ColorAttributes) sc.getAttributes(ColorAttributes.ID);
		tmp.add(String.valueOf(ca.filled));
		tmp.add(String.valueOf(ca.stroked));
		tmp.add(String.valueOf(ca.filledColor));
		tmp.add(String.valueOf(ca.strokedColor));
		
		// selection attributes
		SelectionAttributes sa = (SelectionAttributes) sc.getAttributes(SelectionAttributes.ID);
		tmp.add(String.valueOf(sa.isSelected()));
		
		// rotation attributes
		RotationAttributes ra = (RotationAttributes) sc.getAttributes(RotationAttributes.ID);
		tmp.add(String.valueOf(ra.getAngle()));
		
		// layer attributes
		LayerAttributes la = (LayerAttributes) sc.getAttributes(LayerAttributes.ID);
		tmp.add(String.valueOf(la.getLayer()));
		
		return tmp.toString();
	}
	
}