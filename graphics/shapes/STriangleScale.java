package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;




public class STriangleScale extends Shape{
    private Polygon trianglescale;
    private Point loc;
	private int nPoints;
	private int[] xTriangleScale;
	private int[] yTriangleScale;
	
	private int width;
	private int height;
	
	public STriangleScale(Point loc, int width, int height) {
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.nPoints = 3;
		this.buildPolygon();
	}
	
	private void buildPolygon() {
		this.nPoints = 3;
		this.xTriangleScale = new int[this.nPoints];
		this.yTriangleScale = new int[this.nPoints];

		this.xTriangleScale[0] = this.loc.x + this.width;
		this.yTriangleScale[0] = this.loc.y;
		this.xTriangleScale[1] = this.loc.x + this.width;
		this.yTriangleScale[1] = this.loc.y + this.height;
		this.xTriangleScale[2] = this.loc.x;
		this.yTriangleScale[2] = this.loc.y + this.height;

		this.trianglescale = new Polygon(this.xTriangleScale, this.yTriangleScale, this.nPoints);
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
	
    public Polygon getPolygon(){
        return this.trianglescale;

    }
    
    @Override
    public void accept(ShapeVisitor v){
        v.visitSTriangleScale(this);
    }



	@Override
	public Rectangle getBounds() {

		return this.trianglescale.getBounds();
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

