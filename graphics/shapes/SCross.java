package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;




public class SCross extends SPolygon{
	
	public SCross(Point loc, int width, int height) {
		super(loc, width, height);
	}
	
	@Override
	public void buildPolygon() {
		this.nPoints = 12;
		this.xPolygon = new int[this.nPoints];
		this.yPolygon = new int[this.nPoints];

        this.xPolygon[0] = this.getLoc().x;
        this.yPolygon[0] = this.getLoc().y + this.height * 1/8;
        
        this.xPolygon[1] = this.getLoc().x + this.width * 1/5;
        this.yPolygon[1] = this.getLoc().y;
        
        this.xPolygon[2] = this.getLoc().x + this.width / 2;
        this.yPolygon[2] = this.getLoc().y + this.height * 3/8;
        
        this.xPolygon[3] = this.getLoc().x + this.width * 4/5;
        this.yPolygon[3] = this.getLoc().y;
        
        this.xPolygon[4] = this.getLoc().x + this.width;
        this.yPolygon[4] = this.getLoc().y + this.height * 1/8;
        
        this.xPolygon[5] = this.getLoc().x + this.width * 5/8;
        this.yPolygon[5] = this.getLoc().y + this.height / 2;
      
        this.xPolygon[6] = this.getLoc().x + this.width;
        this.yPolygon[6] = this.getLoc().y + this.height * 7/8;
        
        this.xPolygon[7] = this.getLoc().x + this.width * 7/8;
        this.yPolygon[7] = this.getLoc().y + this.height;
        
        this.xPolygon[8] = this.getLoc().x + this.width / 2;
        this.yPolygon[8] = this.getLoc().y + this.height * 5/8;
        
        this.xPolygon[9] = this.getLoc().x + this.width * 1/5;
        this.yPolygon[9] = this.getLoc().y + this.height;
        
        this.xPolygon[10] = this.getLoc().x;
        this.yPolygon[10] = this.getLoc().y + this.height * 7/8;
        
        this.xPolygon[11] = this.getLoc().x + this.width * 3/8 ;
        this.yPolygon[11] = this.getLoc().y + this.height / 2;

		this.polygon = new Polygon(this.xPolygon, this.yPolygon, this.nPoints);
		}

	@Override
	public Shape copy() {
		SCross cr = new SCross(new Point(this.getLoc()), this.getBounds().width, this.getBounds().height);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		cr.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		cr.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		cr.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		cr.addAttributes(new LayerAttributes(la.getLayer()));
		return cr;
	}

	@Override
	public void resize(int dx, int dy) {
		this.width += dx;
		this.height += dy;
		this.buildPolygon();
	}

    
    @Override
    public void accept(ShapeVisitor v){
        v.visitSCross(this);
    }

	

	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		SCross cr = (SCross) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");
		
		// basic
		tmp.add(cr.getClass().getName());
		tmp.add(String.valueOf(cr.getLoc().x));
		tmp.add(String.valueOf(cr.getLoc().y));
		tmp.add(String.valueOf(cr.getBounds().width));
		tmp.add(String.valueOf(cr.getBounds().height));
		
		// color attributes
		ColorAttributes ca = (ColorAttributes) cr.getAttributes(ColorAttributes.ID);
		tmp.add(String.valueOf(ca.filled));
		tmp.add(String.valueOf(ca.stroked));
		tmp.add(String.valueOf(ca.filledColor.getRGB()));
		tmp.add(String.valueOf(ca.strokedColor.getRGB()));
		
		// selection attributes
		SelectionAttributes sa = (SelectionAttributes) cr.getAttributes(SelectionAttributes.ID);
		tmp.add(String.valueOf(sa.isSelected()));
		
		// rotation attributes
		RotationAttributes ra = (RotationAttributes) cr.getAttributes(RotationAttributes.ID);
		tmp.add(String.valueOf(ra.getAngle()));
		
		// layer attributes
		LayerAttributes la = (LayerAttributes) cr.getAttributes(LayerAttributes.ID);
		tmp.add(String.valueOf(la.getLayer()));
		
		return tmp.toString();
	}

}

