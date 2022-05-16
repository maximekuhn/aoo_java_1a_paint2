package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SOctagon extends SPolygon {
	
	public SOctagon(Point loc, int width, int height) {
		super(loc, width, height);
	}
	
	@Override
	public void buildPolygon() {
		this.nPoints = 8;
		this.xPolygon = new int[this.nPoints];
		this.yPolygon = new int[this.nPoints];

		this.xPolygon[0] = this.getLoc().x + this.width * 1/4;
		this.yPolygon[0] = this.getLoc().y;
		this.xPolygon[1] = this.getLoc().x + this.width * 3/4;
		this.yPolygon[1] = this.getLoc().y;
		this.xPolygon[2] = this.getLoc().x + this.width;
		this.yPolygon[2] = this.getLoc().y + this.height * 1/3;
		this.xPolygon[3] = this.getLoc().x + this.width;
		this.yPolygon[3] = this.getLoc().y + this.height * 2/3;
		this.xPolygon[4] = this.getLoc().x + this.width * 3/4;
		this.yPolygon[4] = this.getLoc().y + this.height;
		this.xPolygon[5] = this.getLoc().x + this.width * 1/4;
		this.yPolygon[5] = this.getLoc().y + this.height;
		this.xPolygon[6] = this.getLoc().x;
		this.yPolygon[6] = this.getLoc().y + this.height * 2/3;
		this.xPolygon[7] = this.getLoc().x;
		this.yPolygon[7] = this.getLoc().y + this.height * 1/3;

		this.polygon = new Polygon(this.xPolygon, this.yPolygon, this.nPoints);
		}


	@Override
	public Shape copy() {
		SOctagon soc = new SOctagon(new Point(this.getLoc()), this.getBounds().width, this.getBounds().height);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		soc.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		soc.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		soc.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		soc.addAttributes(new LayerAttributes(la.getLayer()));
		return soc;
	}

	@Override
	public void resize(int dx, int dy) {
		this.width += dx;
		this.height += dy;
		this.buildPolygon();
	}
    
    @Override
    public void accept(ShapeVisitor v){
        v.visitSOctagon(this);
    }

	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		SOctagon soc = (SOctagon) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");
		
		// basic
		tmp.add(soc.getClass().getName());
		tmp.add(String.valueOf(soc.getLoc().x));
		tmp.add(String.valueOf(soc.getLoc().y));
		tmp.add(String.valueOf(soc.getBounds().width));
		tmp.add(String.valueOf(soc.getBounds().height));
		
		// color attributes
		ColorAttributes ca = (ColorAttributes) soc.getAttributes(ColorAttributes.ID);
		tmp.add(String.valueOf(ca.filled));
		tmp.add(String.valueOf(ca.stroked));
		tmp.add(String.valueOf(ca.filledColor.getRGB()));
		tmp.add(String.valueOf(ca.strokedColor.getRGB()));
		
		// selection attributes
		SelectionAttributes sa = (SelectionAttributes) soc.getAttributes(SelectionAttributes.ID);
		tmp.add(String.valueOf(sa.isSelected()));
		
		// rotation attributes
		RotationAttributes ra = (RotationAttributes) soc.getAttributes(RotationAttributes.ID);
		tmp.add(String.valueOf(ra.getAngle()));
		
		// layer attributes
		LayerAttributes la = (LayerAttributes) soc.getAttributes(LayerAttributes.ID);
		tmp.add(String.valueOf(la.getLayer()));
		
		return tmp.toString();
	}

}

