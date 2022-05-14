package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SLine extends SPolygon {

	public SLine(Point loc, int width, int height) {
		super(loc, width, height);
	}

	@Override
	public void buildPolygon() {
		this.nPoints = 2;
		this.xPolygon = new int[this.nPoints];
		this.yPolygon = new int[this.nPoints];
		
		this.xPolygon[0] = this.getLoc().x;
		this.yPolygon[0] = this.getLoc().y + this.height;
		this.xPolygon[1] = this.getLoc().x + this.width;
		this.yPolygon[1] = this.getLoc().y;
		
		this.polygon = new Polygon(this.xPolygon, this.yPolygon, this.nPoints);
	}

	@Override
	public void accept(ShapeVisitor v) {
		v.visitSLine(this);
	}

	@Override
	public Shape copy() {
		SLine ln = new SLine(new Point(this.getLoc()), this.getBounds().width, this.getBounds().height);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		ln.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		ln.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		ln.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		ln.addAttributes(new LayerAttributes(la.getLayer()));
		return ln;
	}
	
	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		SLine ln = (SLine) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");
		
		// basic
		tmp.add(ln.getClass().getName());
		tmp.add(String.valueOf(ln.getLoc().x));
		tmp.add(String.valueOf(ln.getLoc().y));
		tmp.add(String.valueOf(ln.getBounds().width));
		tmp.add(String.valueOf(ln.getBounds().height));
		
		// color attributes
		ColorAttributes ca = (ColorAttributes) ln.getAttributes(ColorAttributes.ID);
		tmp.add(String.valueOf(ca.filled));
		tmp.add(String.valueOf(ca.stroked));
		tmp.add(String.valueOf(ca.filledColor.getRGB()));
		tmp.add(String.valueOf(ca.strokedColor.getRGB()));
		
		// selection attributes
		SelectionAttributes sa = (SelectionAttributes) ln.getAttributes(SelectionAttributes.ID);
		tmp.add(String.valueOf(sa.isSelected()));
		
		// rotation attributes
		RotationAttributes ra = (RotationAttributes) ln.getAttributes(RotationAttributes.ID);
		tmp.add(String.valueOf(ra.getAngle()));
		
		// layer attributes
		LayerAttributes la = (LayerAttributes) ln.getAttributes(LayerAttributes.ID);
		tmp.add(String.valueOf(la.getLayer()));
		
		return tmp.toString();
	}
}
