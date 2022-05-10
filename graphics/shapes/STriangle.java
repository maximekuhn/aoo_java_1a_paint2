package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;




public class STriangle extends Shape{
    private Polygon triangle;
    private Point loc;
	private int nPoints;
	private int[] xTriangle;
	private int[] yTriangle;
	
	private int width;
	private int height;
	
	public STriangle(Point loc, int width, int height) {
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.nPoints = 3;
		this.buildPolygon();
	}
	
	private void buildPolygon() {
		this.nPoints = 3;
		this.xTriangle = new int[this.nPoints];
		this.yTriangle = new int[this.nPoints];

		this.xTriangle[0] = this.loc.x + this.width / 2;
		this.yTriangle[0] = this.loc.y;
		this.xTriangle[1] = this.loc.x;
		this.yTriangle[1] = this.loc.y + this.height;
		this.xTriangle[2] = this.loc.x + this.width;
		this.yTriangle[2] = this.loc.y + this.height;

		this.triangle = new Polygon(this.xTriangle, this.yTriangle, this.nPoints);
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
		STriangle tr = new STriangle(new Point(this.getLoc()), this.getBounds().width, this.getBounds().height);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		tr.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		tr.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		tr.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		tr.addAttributes(new LayerAttributes(la.getLayer()));
		return tr;
	}

	@Override
	public void resize(int dx, int dy) {
		this.width += dx;
		this.height += dy;
		this.buildPolygon();
	}
	
    public Polygon getPolygon(){
        return this.triangle;

    }
    
    @Override
    public void accept(ShapeVisitor v){
        v.visitSTriangle(this);
    }



	@Override
	public Rectangle getBounds() {

		return this.triangle.getBounds();
	}
	

	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		STriangle tr = (STriangle) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");
		
		// basic
		tmp.add(tr.getClass().getName());
		tmp.add(String.valueOf(tr.getLoc().x));
		tmp.add(String.valueOf(tr.getLoc().y));
		tmp.add(String.valueOf(tr.getBounds().width));
		tmp.add(String.valueOf(tr.getBounds().height));
		
		// color attributes
		ColorAttributes ca = (ColorAttributes) tr.getAttributes(ColorAttributes.ID);
		tmp.add(String.valueOf(ca.filled));
		tmp.add(String.valueOf(ca.stroked));
		tmp.add(String.valueOf(ca.filledColor.getRGB()));
		tmp.add(String.valueOf(ca.strokedColor.getRGB()));
		
		// selection attributes
		SelectionAttributes sa = (SelectionAttributes) tr.getAttributes(SelectionAttributes.ID);
		tmp.add(String.valueOf(sa.isSelected()));
		
		// rotation attributes
		RotationAttributes ra = (RotationAttributes) tr.getAttributes(RotationAttributes.ID);
		tmp.add(String.valueOf(ra.getAngle()));
		
		// layer attributes
		LayerAttributes la = (LayerAttributes) tr.getAttributes(LayerAttributes.ID);
		tmp.add(String.valueOf(la.getLayer()));
		
		return tmp.toString();
	}

}
