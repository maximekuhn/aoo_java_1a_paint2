package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SRhombus extends Shape {

    private Polygon rhombus;
    private Point loc;
	private int nPoints;
	private int[] xRhombus;
	private int[] yRhombus;
	
	private int width;
	private int height;
	
	public SRhombus(Point loc, int width, int height) {
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.nPoints = 4;
		this.buildPolygon();
	}
	
	private void buildPolygon() {
		this.nPoints = 4;
		this.xRhombus = new int[this.nPoints];
		this.yRhombus = new int[this.nPoints];

		this.xRhombus[0] = this.loc.x + this.width * 1/2;
		this.yRhombus[0] = this.loc.y;
		this.xRhombus[1] = this.loc.x + this.width * 5/6;
		this.yRhombus[1] = this.loc.y + this.height * 1/2;
		this.xRhombus[2] = this.loc.x + this.width * 1/2;
		this.yRhombus[2] = this.loc.y + this.height;
		this.xRhombus[3] = this.loc.x + this.width * 1/6;
		this.yRhombus[3] = this.loc.y + this.height * 1/2;


		this.rhombus = new Polygon(this.xRhombus, this.yRhombus, this.nPoints);
		}

	@Override
	public void setLoc(Point p) {
		this.loc.setLocation(p);
		this.buildPolygon();
	}

	@Override
	public Point getLoc() {
		return this.loc;
	}

    public void translate(int dx, int dy) {
		this.setLoc(new Point(this.loc.x + dx, this.loc.y + dy));
		this.buildPolygon();
	}


	@Override
	public Shape copy() {
		SRhombus srh = new SRhombus(new Point(this.getLoc()), this.getBounds().width, this.getBounds().height);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		srh.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		srh.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		srh.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		srh.addAttributes(new LayerAttributes(la.getLayer()));
		return srh;
	}

	@Override
	public void resize(int dx, int dy) {
		this.width += dx;
		this.height += dy;
		this.buildPolygon();
	}
	
    public Polygon getPolygon(){
        return this.rhombus;

    }
    
    @Override
    public void accept(ShapeVisitor v){
        v.visitSRhombus(this);
    }



	@Override
	public Rectangle getBounds() {

		return this.rhombus.getBounds();
	}
	

	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		SRhombus srh = (SRhombus) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");
		
		// basic
		tmp.add(srh.getClass().getName());
		tmp.add(String.valueOf(srh.getLoc().x));
		tmp.add(String.valueOf(srh.getLoc().y));
		tmp.add(String.valueOf(srh.getBounds().width));
		tmp.add(String.valueOf(srh.getBounds().height));
		
		// color attributes
		ColorAttributes ca = (ColorAttributes) srh.getAttributes(ColorAttributes.ID);
		tmp.add(String.valueOf(ca.filled));
		tmp.add(String.valueOf(ca.stroked));
		tmp.add(String.valueOf(ca.filledColor.getRGB()));
		tmp.add(String.valueOf(ca.strokedColor.getRGB()));
		
		// selection attributes
		SelectionAttributes sa = (SelectionAttributes) srh.getAttributes(SelectionAttributes.ID);
		tmp.add(String.valueOf(sa.isSelected()));
		
		// rotation attributes
		RotationAttributes ra = (RotationAttributes) srh.getAttributes(RotationAttributes.ID);
		tmp.add(String.valueOf(ra.getAngle()));
		
		// layer attributes
		LayerAttributes la = (LayerAttributes) srh.getAttributes(LayerAttributes.ID);
		tmp.add(String.valueOf(la.getLayer()));
		
		return tmp.toString();
	}

}

