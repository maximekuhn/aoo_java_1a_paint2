package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;




public class STriangleRec extends Shape{
    private Polygon trianglerec;
    private Point loc;
	private int nPoints;
	private int[] xTriangleRec;
	private int[] yTriangleRec;
	
	private int width;
	private int height;
	
	public STriangleRec(Point loc, int width, int height) {
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.nPoints = 3;
		this.buildPolygon();
	}
	
	private void buildPolygon() {
		this.nPoints = 3;
		this.xTriangleRec = new int[this.nPoints];
		this.yTriangleRec = new int[this.nPoints];

		this.xTriangleRec[0] = this.loc.x + this.width;
		this.yTriangleRec[0] = this.loc.y;
		this.xTriangleRec[1] = this.loc.x + this.width;
		this.yTriangleRec[1] = this.loc.y + this.height;
		this.xTriangleRec[2] = this.loc.x;
		this.yTriangleRec[2] = this.loc.y + this.height;

		this.trianglerec = new Polygon(this.xTriangleRec, this.yTriangleRec, this.nPoints);
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
		STriangleRec trec = new STriangleRec(new Point(this.getLoc()), this.getBounds().width, this.getBounds().height);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		trec.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		trec.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		trec.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		trec.addAttributes(new LayerAttributes(la.getLayer()));
		return trec;
	}

	@Override
	public void resize(int dx, int dy) {
		this.width += dx;
		this.height += dy;
		this.buildPolygon();
	}
	
    public Polygon getPolygon(){
        return this.trianglerec;

    }
    
    @Override
    public void accept(ShapeVisitor v){
        v.visitSTriangleRec(this);
    }



	@Override
	public Rectangle getBounds() {

		return this.trianglerec.getBounds();
	}
	

	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		STriangleRec trec = (STriangleRec) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");
		
		// basic
		tmp.add(trec.getClass().getName());
		tmp.add(String.valueOf(trec.getLoc().x));
		tmp.add(String.valueOf(trec.getLoc().y));
		tmp.add(String.valueOf(trec.getBounds().width));
		tmp.add(String.valueOf(trec.getBounds().height));
		
		// color attributes
		ColorAttributes ca = (ColorAttributes) trec.getAttributes(ColorAttributes.ID);
		tmp.add(String.valueOf(ca.filled));
		tmp.add(String.valueOf(ca.stroked));
		tmp.add(String.valueOf(ca.filledColor.getRGB()));
		tmp.add(String.valueOf(ca.strokedColor.getRGB()));
		
		// selection attributes
		SelectionAttributes sa = (SelectionAttributes) trec.getAttributes(SelectionAttributes.ID);
		tmp.add(String.valueOf(sa.isSelected()));
		
		// rotation attributes
		RotationAttributes ra = (RotationAttributes) trec.getAttributes(RotationAttributes.ID);
		tmp.add(String.valueOf(ra.getAngle()));
		
		// layer attributes
		LayerAttributes la = (LayerAttributes) trec.getAttributes(LayerAttributes.ID);
		tmp.add(String.valueOf(la.getLayer()));
		
		return tmp.toString();
	}

}

