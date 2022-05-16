package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SDecagon extends SPolygon {
	
	public SDecagon(Point loc, int width, int height) {
		super(loc, width, height);
	}
	
	@Override
	public void buildPolygon() {
		this.nPoints = 10;
		this.xPolygon = new int[this.nPoints];
		this.yPolygon = new int[this.nPoints];

        this.xPolygon[0] = this.getLoc().x + this.width * 1/3;
        this.yPolygon[0] = this.getLoc().y;
        this.xPolygon[1] = this.getLoc().x + this.width * 2/3;
        this.yPolygon[1] = this.getLoc().y;
        this.xPolygon[2] = this.getLoc().x + this.width * 9/10;
        this.yPolygon[2] = this.getLoc().y + this.height * 1/5;
        this.xPolygon[3] = this.getLoc().x + this.width;
        this.yPolygon[3] = this.getLoc().y + this.height * 1/2;
        this.xPolygon[4] = this.getLoc().x + this.width * 9/10;
        this.yPolygon[4] = this.getLoc().y + this.height * 4/5;
        this.xPolygon[5] = this.getLoc().x + this.width * 2/3;
        this.yPolygon[5] = this.getLoc().y + this.height;
        this.xPolygon[6] = this.getLoc().x + this.width * 1/3;
        this.yPolygon[6] = this.getLoc().y + this.height;
        this.xPolygon[7] = this.getLoc().x + this.width * 1/10;
        this.yPolygon[7] = this.getLoc().y + this.height * 4/5;
        this.xPolygon[8] = this.getLoc().x;
        this.yPolygon[8] = this.getLoc().y + this.height * 1/2;
        this.xPolygon[9] = this.getLoc().x + this.width * 1/10 ;
        this.yPolygon[9] = this.getLoc().y + this.height * 1/5;

		this.polygon = new Polygon(this.xPolygon, this.yPolygon, this.nPoints);
		}


	@Override
	public Shape copy() {
		SDecagon hx = new SDecagon(new Point(this.getLoc()), this.getBounds().width, this.getBounds().height);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		hx.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		hx.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		hx.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		hx.addAttributes(new LayerAttributes(la.getLayer()));
		return hx;
	}

	@Override
	public void resize(int dx, int dy) {
		this.width += dx;
		this.height += dy;
		this.buildPolygon();
	}
    
    @Override
    public void accept(ShapeVisitor v){
        v.visitSDecagon(this);
    }


	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		SDecagon hx = (SDecagon) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");
		
		// basic
		tmp.add(hx.getClass().getName());
		tmp.add(String.valueOf(hx.getLoc().x));
		tmp.add(String.valueOf(hx.getLoc().y));
		tmp.add(String.valueOf(hx.getBounds().width));
		tmp.add(String.valueOf(hx.getBounds().height));
		
		// color attributes
		ColorAttributes ca = (ColorAttributes) hx.getAttributes(ColorAttributes.ID);
		tmp.add(String.valueOf(ca.filled));
		tmp.add(String.valueOf(ca.stroked));
		tmp.add(String.valueOf(ca.filledColor.getRGB()));
		tmp.add(String.valueOf(ca.strokedColor.getRGB()));
		
		// selection attributes
		SelectionAttributes sa = (SelectionAttributes) hx.getAttributes(SelectionAttributes.ID);
		tmp.add(String.valueOf(sa.isSelected()));
		
		// rotation attributes
		RotationAttributes ra = (RotationAttributes) hx.getAttributes(RotationAttributes.ID);
		tmp.add(String.valueOf(ra.getAngle()));
		
		// layer attributes
		LayerAttributes la = (LayerAttributes) hx.getAttributes(LayerAttributes.ID);
		tmp.add(String.valueOf(la.getLayer()));
		
		return tmp.toString();
	}

}

