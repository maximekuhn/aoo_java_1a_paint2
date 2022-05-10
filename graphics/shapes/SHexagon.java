package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SHexagon extends Shape {

    private Polygon hexagon;
    private Point loc;
	private int nPoints;
	private int[] xHexagon;
	private int[] yHexagon;
	
	private int width;
	private int height;
	
	public SHexagon(Point loc, int width, int height) {
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.nPoints = 6;
		this.buildPolygon();
	}
	
	private void buildPolygon() {
		this.nPoints = 6;
		this.xHexagon = new int[this.nPoints];
		this.yHexagon = new int[this.nPoints];

		this.xHexagon[0] = this.loc.x + this.width / 4;
		this.yHexagon[0] = this.loc.y;
		this.xHexagon[1] = this.loc.x + this.width / 2;
		this.yHexagon[1] = this.loc.y;
		this.xHexagon[2] = this.loc.x + this.width;
		this.yHexagon[2] = this.loc.y + this.height / 2;
		this.xHexagon[3] = this.loc.x + this.width / 2;
		this.yHexagon[3] = this.loc.y + this.height;
		this.xHexagon[4] = this.loc.x + this.width / 4;
		this.yHexagon[4] = this.loc.y + this.height;
		this.xHexagon[5] = this.loc.x + this.width;
		this.yHexagon[5] = this.loc.y + this.height / 2;

		this.hexagon = new Polygon(this.xHexagon, this.yHexagon, this.nPoints);
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
		SHexagon hx = new SHexagon(new Point(this.getLoc()), this.getBounds().width, this.getBounds().height);
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
	
    public Polygon getPolygon(){
        return this.hexagon;

    }
    
    @Override
    public void accept(ShapeVisitor v){
        v.visitSHexagon(this);
    }



	@Override
	public Rectangle getBounds() {

		return this.hexagon.getBounds();
	}
	

	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		SHexagon hx = (SHexagon) this.copy();
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

