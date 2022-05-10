package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;




public class SCross extends Shape{
    private Polygon cross;
    private Point loc;
	private int nPoints;
	private int[] xCross;
	private int[] yCross;
	
	private int width;
	private int height;
	
	public SCross(Point loc, int width, int height) {
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.nPoints = 6;
		this.buildPolygon();
	}
	
	private void buildPolygon() {
		this.nPoints = 6;
		this.xCross = new int[this.nPoints];
		this.yCross = new int[this.nPoints];

        this.xCross[0] = this.loc.x + this.width / 4;
        this.yCross[0] = this.loc.y;
        this.xCross[1] = this.loc.x + this.width / 2;
        this.yCross[1] = this.loc.y;
        this.xCross[2] = this.loc.x + this.width;
        this.yCross[2] = this.loc.y + this.height / 2;
        this.xCross[3] = this.loc.x + this.width / 2;
        this.yCross[3] = this.loc.y + this.height;
        this.xCross[4] = this.loc.x + this.width / 4;
        this.yCross[4] = this.loc.y + this.height;
        this.xCross[5] = this.loc.x + this.width;
        this.yCross[5] = this.loc.y + this.height / 2;

		this.cross = new Polygon(this.xCross, this.yCross, this.nPoints);
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
	
    public Polygon getPolygon(){
        return this.cross;

    }
    
    @Override
    public void accept(ShapeVisitor v){
        v.visitSCross(this);
    }



	@Override
	public Rectangle getBounds() {

		return this.cross.getBounds();
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

