package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SSixPointedStar extends SPolygon {

	public SSixPointedStar(Point loc, int width, int height) {
		super(loc, width, height);
	}

	@Override
	public void buildPolygon() {
		this.nPoints = 12;
		this.xPolygon = new int[this.nPoints];
		this.yPolygon = new int[this.nPoints];
		
		this.xPolygon[0] = this.getLoc().x + this.width / 2;
		this.yPolygon[0] = this.getLoc().y;
		this.xPolygon[1] = this.getLoc().x + this.width * 2/3;
		this.yPolygon[1] = this.getLoc().y + this.height * 1/4;
		this.xPolygon[2] = this.getLoc().x + this.width;
		this.yPolygon[2] = this.getLoc().y + this.height * 1/4;
		this.xPolygon[3] = this.getLoc().x + this.width * 4/5;
		this.yPolygon[3] = this.getLoc().y + this.height / 2;
		this.xPolygon[4] = this.getLoc().x + this.width;
		this.yPolygon[4] = this.getLoc().y + this.height * 3/4;
		this.xPolygon[5] = this.getLoc().x + this.width * 2/3;
		this.yPolygon[5] = this.getLoc().y + this.height * 3/4;
		this.xPolygon[6] = this.getLoc().x + this.width /2;
		this.yPolygon[6] = this.getLoc().y + this.height;
		this.xPolygon[7] = this.getLoc().x + this.width * 1/3;
		this.yPolygon[7] = this.getLoc().y + this.height * 3/4;
		this.xPolygon[8] = this.getLoc().x;
		this.yPolygon[8] = this.getLoc().y + this.height * 3/4;
		this.xPolygon[9] = this.getLoc().x + this.width * 1/5;
		this.yPolygon[9] = this.getLoc().y + this.height / 2;
		this.xPolygon[10] = this.getLoc().x;
		this.yPolygon[10] = this.getLoc().y + this.height * 1/4;
		this.xPolygon[11] = this.getLoc().x + this.width * 1/3;
		this.yPolygon[11] = this.getLoc().y + this.height * 1/4;
		
		this.polygon = new Polygon(this.xPolygon, this.yPolygon, this.nPoints);
	}

	@Override
	public void accept(ShapeVisitor v) {
		v.visitSSixPointedStar(this);
	}

	@Override
	public Shape copy() {
		SSixPointedStar ssp = new SSixPointedStar(new Point(this.getLoc()), this.getBounds().width, this.getBounds().height);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		ssp.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		ssp.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		ssp.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		ssp.addAttributes(new LayerAttributes(la.getLayer()));
		return ssp;
	}
	
	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		SSixPointedStar ssp = (SSixPointedStar) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");

		// basic
		tmp.add(ssp.getClass().getName());
		tmp.add(String.valueOf(ssp.getLoc().x));
		tmp.add(String.valueOf(ssp.getLoc().y));
		tmp.add(String.valueOf(ssp.getBounds().width));
		tmp.add(String.valueOf(ssp.getBounds().height));

		// color attributes
		ColorAttributes ca = (ColorAttributes) ssp.getAttributes(ColorAttributes.ID);
		tmp.add(String.valueOf(ca.filled));
		tmp.add(String.valueOf(ca.stroked));
		tmp.add(String.valueOf(ca.filledColor.getRGB()));
		tmp.add(String.valueOf(ca.strokedColor.getRGB()));

		// selection attributes
		SelectionAttributes sa = (SelectionAttributes) ssp.getAttributes(SelectionAttributes.ID);
		tmp.add(String.valueOf(sa.isSelected()));

		// rotation attributes
		RotationAttributes ra = (RotationAttributes) ssp.getAttributes(RotationAttributes.ID);
		tmp.add(String.valueOf(ra.getAngle()));

		// layer attributes
		LayerAttributes la = (LayerAttributes) ssp.getAttributes(LayerAttributes.ID);
		tmp.add(String.valueOf(la.getLayer()));

		return tmp.toString();
	}


}
