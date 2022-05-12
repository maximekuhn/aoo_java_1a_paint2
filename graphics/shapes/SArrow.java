package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SArrow extends Shape {

    private Polygon arrow;
    private Point loc;
	private int nPoints;
	private int[] xArrow;
	private int[] yArrow;
	
	private int width;
	private int height;
	
	public SArrow(Point loc, int width, int height) {
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.nPoints = 7;
		this.buildPolygon();
	}
	
	private void buildPolygon() {
		this.nPoints = 7;
		this.xArrow = new int[this.nPoints];
		this.yArrow = new int[this.nPoints];

		this.xArrow[0] = this.loc.x + this.width * 1/3;
		this.yArrow[0] = this.loc.y + this.height * 1/2;
		this.xArrow[1] = this.loc.x;
		this.yArrow[1] = this.loc.y + this.height * 1/2;
		this.xArrow[2] = this.loc.x + this.width / 2;
		this.yArrow[2] = this.loc.y;
		this.xArrow[3] = this.loc.x + this.width;
		this.yArrow[3] = this.loc.y + this.height * 1/2;
		this.xArrow[4] = this.loc.x + this.width * 2/3;
		this.yArrow[4] = this.loc.y + this.height * 1/2;
		this.xArrow[5] = this.loc.x + this.width * 2/3;
		this.yArrow[5] = this.loc.y + this.height;
		this.xArrow[6] = this.loc.x + this.width * 1/3;
		this.yArrow[6] = this.loc.y + this.height;

		this.arrow = new Polygon(this.xArrow, this.yArrow, this.nPoints);
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
		SArrow sar = new SArrow(new Point(this.getLoc()), this.getBounds().width, this.getBounds().height);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		sar.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		sar.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		sar.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		sar.addAttributes(new LayerAttributes(la.getLayer()));
		return sar;
	}

	@Override
	public void resize(int dx, int dy) {
		this.width += dx;
		this.height += dy;
		this.buildPolygon();
	}
	
    public Polygon getPolygon(){
        return this.arrow;

    }
    
    @Override
    public void accept(ShapeVisitor v){
        v.visitSArrow(this);
    }



	@Override
	public Rectangle getBounds() {

		return this.arrow.getBounds();
	}
	

	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		SArrow sar = (SArrow) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");
		
		// basic
		tmp.add(sar.getClass().getName());
		tmp.add(String.valueOf(sar.getLoc().x));
		tmp.add(String.valueOf(sar.getLoc().y));
		tmp.add(String.valueOf(sar.getBounds().width));
		tmp.add(String.valueOf(sar.getBounds().height));
		
		// color attributes
		ColorAttributes ca = (ColorAttributes) sar.getAttributes(ColorAttributes.ID);
		tmp.add(String.valueOf(ca.filled));
		tmp.add(String.valueOf(ca.stroked));
		tmp.add(String.valueOf(ca.filledColor.getRGB()));
		tmp.add(String.valueOf(ca.strokedColor.getRGB()));
		
		// selection attributes
		SelectionAttributes sa = (SelectionAttributes) sar.getAttributes(SelectionAttributes.ID);
		tmp.add(String.valueOf(sa.isSelected()));
		
		// rotation attributes
		RotationAttributes ra = (RotationAttributes) sar.getAttributes(RotationAttributes.ID);
		tmp.add(String.valueOf(ra.getAngle()));
		
		// layer attributes
		LayerAttributes la = (LayerAttributes) sar.getAttributes(LayerAttributes.ID);
		tmp.add(String.valueOf(la.getLayer()));
		
		return tmp.toString();
	}

}

