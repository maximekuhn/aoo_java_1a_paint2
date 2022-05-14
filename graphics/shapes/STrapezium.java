package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class STrapezium extends Shape {

    private Polygon trapezium;
    private Point loc;
	private int nPoints;
	private int[] xTrapezium;
	private int[] yTrapezium;
	
	private int width;
	private int height;
	
	public STrapezium(Point loc, int width, int height) {
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.nPoints = 4;
		this.buildPolygon();
	}
	
	private void buildPolygon() {
		this.nPoints = 4;
		this.xTrapezium = new int[this.nPoints];
		this.yTrapezium = new int[this.nPoints];

		this.xTrapezium[0] = this.loc.x + this.width * 1/7;
		this.yTrapezium[0] = this.loc.y;
		this.xTrapezium[1] = this.loc.x + this.width * 6/7;
		this.yTrapezium[1] = this.loc.y;
		this.xTrapezium[2] = this.loc.x + this.width;
		this.yTrapezium[2] = this.loc.y + this.height;
		this.xTrapezium[3] = this.loc.x;
		this.yTrapezium[3] = this.loc.y + this.height;

		this.trapezium = new Polygon(this.xTrapezium, this.yTrapezium, this.nPoints);
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
		STrapezium stp = new STrapezium(new Point(this.getLoc()), this.getBounds().width, this.getBounds().height);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		stp.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		stp.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		stp.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		stp.addAttributes(new LayerAttributes(la.getLayer()));
		return stp;
	}

	@Override
	public void resize(int dx, int dy) {
		this.width += dx;
		this.height += dy;
		this.buildPolygon();
	}
	
    public Polygon getPolygon(){
        return this.trapezium;

    }
    
    @Override
    public void accept(ShapeVisitor v){
        v.visitSTrapezium(this);
    }



	@Override
	public Rectangle getBounds() {

		return this.trapezium.getBounds();
	}
	

	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		STrapezium stp = (STrapezium) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");
		
		// basic
		tmp.add(stp.getClass().getName());
		tmp.add(String.valueOf(stp.getLoc().x));
		tmp.add(String.valueOf(stp.getLoc().y));
		tmp.add(String.valueOf(stp.getBounds().width));
		tmp.add(String.valueOf(stp.getBounds().height));
		
		// color attributes
		ColorAttributes ca = (ColorAttributes) stp.getAttributes(ColorAttributes.ID);
		tmp.add(String.valueOf(ca.filled));
		tmp.add(String.valueOf(ca.stroked));
		tmp.add(String.valueOf(ca.filledColor.getRGB()));
		tmp.add(String.valueOf(ca.strokedColor.getRGB()));
		
		// selection attributes
		SelectionAttributes sa = (SelectionAttributes) stp.getAttributes(SelectionAttributes.ID);
		tmp.add(String.valueOf(sa.isSelected()));
		
		// rotation attributes
		RotationAttributes ra = (RotationAttributes) stp.getAttributes(RotationAttributes.ID);
		tmp.add(String.valueOf(ra.getAngle()));
		
		// layer attributes
		LayerAttributes la = (LayerAttributes) stp.getAttributes(LayerAttributes.ID);
		tmp.add(String.valueOf(la.getLayer()));
		
		return tmp.toString();
	}

}

