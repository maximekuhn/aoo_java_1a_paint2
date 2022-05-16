package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SDecagon extends Shape {

    private Polygon decagon;
    private Point loc;
	private int nPoints;
	private int[] xDecagon;
	private int[] yDecagon;
	
	private int width;
	private int height;
	
	public SDecagon(Point loc, int width, int height) {
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.nPoints = 10;
		this.buildPolygon();
	}
	
	private void buildPolygon() {
		this.nPoints = 10;
		this.xDecagon = new int[this.nPoints];
		this.yDecagon = new int[this.nPoints];

        this.xDecagon[0] = this.loc.x + this.width * 1/3;
        this.yDecagon[0] = this.loc.y;
        this.xDecagon[1] = this.loc.x + this.width * 2/3;
        this.yDecagon[1] = this.loc.y;
        this.xDecagon[2] = this.loc.x + this.width * 9/10;
        this.yDecagon[2] = this.loc.y + this.height * 1/5;
        this.xDecagon[3] = this.loc.x + this.width;
        this.yDecagon[3] = this.loc.y + this.height * 1/2;
        this.xDecagon[4] = this.loc.x + this.width * 9/10;
        this.yDecagon[4] = this.loc.y + this.height * 4/5;
        this.xDecagon[5] = this.loc.x + this.width * 2/3;
        this.yDecagon[5] = this.loc.y + this.height;
        this.xDecagon[6] = this.loc.x + this.width * 1/3;
        this.yDecagon[6] = this.loc.y + this.height;
        this.xDecagon[7] = this.loc.x + this.width * 1/10;
        this.yDecagon[7] = this.loc.y + this.height * 4/5;
        this.xDecagon[8] = this.loc.x;
        this.yDecagon[8] = this.loc.y + this.height * 1/2;
        this.xDecagon[9] = this.loc.x + this.width * 1/10 ;
        this.yDecagon[9] = this.loc.y + this.height * 1/5;

		this.decagon = new Polygon(this.xDecagon, this.yDecagon, this.nPoints);
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
	
    public Polygon getPolygon(){
        return this.decagon;

    }
    
    @Override
    public void accept(ShapeVisitor v){
        v.visitSDecagon(this);
    }



	@Override
	public Rectangle getBounds() {

		return this.decagon.getBounds();
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

