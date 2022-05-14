package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SKotlin extends SPolygon {

	public SKotlin(Point loc, int width, int height) {
		super(loc, width, height);
	}

	@Override
	public void buildPolygon() {
		this.nPoints = 5;
		this.xPolygon = new int[this.nPoints];
		this.yPolygon = new int[this.nPoints];
		
		this.xPolygon[0] = this.getLoc().x;
		this.yPolygon[0] = this.getLoc().y;
		this.xPolygon[1] = this.getLoc().x + this.width;
		this.yPolygon[1] = this.getLoc().y;
		this.xPolygon[2] = this.getLoc().x + this.width / 2;
		this.yPolygon[2] = this.getLoc().y + this.height /2;
		this.xPolygon[3] = this.getLoc().x + this.width;
		this.yPolygon[3] = this.getLoc().y + this.height;
		this.xPolygon[4] = this.getLoc().x;
		this.yPolygon[4] = this.getLoc().y + this.height;
		
		this.polygon = new Polygon(this.xPolygon, this.yPolygon, this.nPoints);
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


}
