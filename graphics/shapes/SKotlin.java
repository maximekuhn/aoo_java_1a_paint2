package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

/*
 * Kotlin logo
 */
public class SKotlin extends Shape {
	
	private Polygon kotlin;
	private Point loc;
	
	private int nPoints;
	private int[] xKotlin;
	private int[] yKotlin;
	
	private int width;
	private int height;
	
	public SKotlin(Point loc, int width, int height) {
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.nPoints = 5;
		this.buildPolygon();
	}
	
	private void buildPolygon() {
		this.nPoints = 5;
		this.xKotlin = new int[this.nPoints];
		this.yKotlin = new int[this.nPoints];
		
		this.xKotlin[0] = this.loc.x;
		this.yKotlin[0] = this.loc.y;
		this.xKotlin[1] = this.loc.x + this.width;
		this.yKotlin[1] = this.loc.y;
		this.xKotlin[2] = this.loc.x + this.width / 2;
		this.yKotlin[2] = this.loc.y + this.height / 2;
		this.xKotlin[3] = this.loc.x + this.width;
		this.yKotlin[3] = this.loc.y + this.height;
		this.xKotlin[4] = this.loc.x;
		this.yKotlin[4] = this.loc.y + this.height;
		
		this.kotlin = new Polygon(this.xKotlin, this.yKotlin, this.nPoints);
	}
	
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
		return this.kotlin.getBounds();
	}

	@Override
	public void accept(ShapeVisitor v) {
		v.visitSKotlin(this);
	}

	@Override
	public Shape copy() {
		SKotlin sk = new SKotlin(new Point(this.getLoc()), this.getBounds().width, this.getBounds().height);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		sk.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		sk.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		sk.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		sk.addAttributes(new LayerAttributes(la.getLayer()));
		return sk;
	}

	@Override
	public void resize(int dx, int dy) {
		// TODO Auto-generated method stub

	}
	
	public Polygon getPolygon() {
		return this.kotlin;
	}

}
