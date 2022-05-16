package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;




public class STriangleScale extends SPolygon{

	public STriangleScale(Point loc, int width, int height) {
		super(loc, width, height);
	}
	
	@Override
	public void buildPolygon() {
		this.nPoints = 3;
		this.xPolygon = new int[this.nPoints];
		this.yPolygon = new int[this.nPoints];

		this.xPolygon[0] = this.getLoc().x;
		this.yPolygon[0] = this.getLoc().y;
		this.xPolygon[1] = this.getLoc().x + this.width * 1/4;
		this.yPolygon[1] = this.getLoc().y + this.height;
		this.xPolygon[2] = this.getLoc().x + this.width;
		this.yPolygon[2] = this.getLoc().y + this.height;

		this.polygon = new Polygon(this.xPolygon, this.yPolygon, this.nPoints);
		}


	@Override
	public Shape copy() {
		STriangleScale stc = new STriangleScale(new Point(this.getLoc()), this.getBounds().width, this.getBounds().height);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		stc.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		stc.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		stc.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		stc.addAttributes(new LayerAttributes(la.getLayer()));
		return stc;
	}

	@Override
	public void resize(int dx, int dy) {
		this.width += dx;
		this.height += dy;
		this.buildPolygon();
	}
	
    
    @Override
    public void accept(ShapeVisitor v){
        v.visitSTriangleScale(this);
    }
	

	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		STriangleScale stc = (STriangleScale) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");
		
		// basic
		tmp.add(stc.getClass().getName());
		tmp.add(String.valueOf(stc.getLoc().x));
		tmp.add(String.valueOf(stc.getLoc().y));
		tmp.add(String.valueOf(stc.getBounds().width));
		tmp.add(String.valueOf(stc.getBounds().height));
		
		// color attributes
		ColorAttributes ca = (ColorAttributes) stc.getAttributes(ColorAttributes.ID);
		tmp.add(String.valueOf(ca.filled));
		tmp.add(String.valueOf(ca.stroked));
		tmp.add(String.valueOf(ca.filledColor.getRGB()));
		tmp.add(String.valueOf(ca.strokedColor.getRGB()));
		
		// selection attributes
		SelectionAttributes sa = (SelectionAttributes) stc.getAttributes(SelectionAttributes.ID);
		tmp.add(String.valueOf(sa.isSelected()));
		
		// rotation attributes
		RotationAttributes ra = (RotationAttributes) stc.getAttributes(RotationAttributes.ID);
		tmp.add(String.valueOf(ra.getAngle()));
		
		// layer attributes
		LayerAttributes la = (LayerAttributes) stc.getAttributes(LayerAttributes.ID);
		tmp.add(String.valueOf(la.getLayer()));
		
		return tmp.toString();
	}

}

