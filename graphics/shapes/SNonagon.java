package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SNonagon extends Shape {

    private Polygon nonagon;
    private Point loc;
	private int nPoints;
	private int[] xNonagon;
	private int[] yNonagon;
	
	private int width;
	private int height;
	
	public SNonagon(Point loc, int width, int height) {
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.nPoints = 9;
		this.buildPolygon();
	}
	
	private void buildPolygon() {
		this.nPoints = 9;
		this.xNonagon = new int[this.nPoints];
		this.yNonagon = new int[this.nPoints];

        this.xNonagon[0] = this.loc.x + this.width / 2;
        this.yNonagon[0] = this.loc.y;
        this.xNonagon[1] = this.loc.x + this.width * 4/5;
        this.yNonagon[1] = this.loc.y + this.height * 1/6;
        this.xNonagon[2] = this.loc.x + this.width;
        this.yNonagon[2] = this.loc.y + this.height * 1/3;
        this.xNonagon[3] = this.loc.x + this.width * 9/10;
        this.yNonagon[3] = this.loc.y + this.height * 2/3;
        this.xNonagon[4] = this.loc.x + this.width * 2/3;
        this.yNonagon[4] = this.loc.y + this.height;
        this.xNonagon[5] = this.loc.x + this.width * 1/3;
        this.yNonagon[5] = this.loc.y + this.height;
        this.xNonagon[6] = this.loc.x + this.width * 1/10;
        this.yNonagon[6] = this.loc.y + this.height * 2/3;
        this.xNonagon[7] = this.loc.x;
        this.yNonagon[7] = this.loc.y + this.height * 1/3;
        this.xNonagon[8] = this.loc.x + this.width * 1/5;
        this.yNonagon[8] = this.loc.y + this.height * 1/6;

		this.nonagon = new Polygon(this.xNonagon, this.yNonagon, this.nPoints);
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
		SNonagon soc = new SNonagon(new Point(this.getLoc()), this.getBounds().width, this.getBounds().height);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		soc.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		soc.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		soc.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		soc.addAttributes(new LayerAttributes(la.getLayer()));
		return soc;
	}

	@Override
	public void resize(int dx, int dy) {
		this.width += dx;
		this.height += dy;
		this.buildPolygon();
	}
	
    public Polygon getPolygon(){
        return this.nonagon;

    }
    
    @Override
    public void accept(ShapeVisitor v){
        v.visitSNonagon(this);
    }



	@Override
	public Rectangle getBounds() {

		return this.nonagon.getBounds();
	}
	

	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		SNonagon soc = (SNonagon) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");
		
		// basic
		tmp.add(soc.getClass().getName());
		tmp.add(String.valueOf(soc.getLoc().x));
		tmp.add(String.valueOf(soc.getLoc().y));
		tmp.add(String.valueOf(soc.getBounds().width));
		tmp.add(String.valueOf(soc.getBounds().height));
		
		// color attributes
		ColorAttributes ca = (ColorAttributes) soc.getAttributes(ColorAttributes.ID);
		tmp.add(String.valueOf(ca.filled));
		tmp.add(String.valueOf(ca.stroked));
		tmp.add(String.valueOf(ca.filledColor.getRGB()));
		tmp.add(String.valueOf(ca.strokedColor.getRGB()));
		
		// selection attributes
		SelectionAttributes sa = (SelectionAttributes) soc.getAttributes(SelectionAttributes.ID);
		tmp.add(String.valueOf(sa.isSelected()));
		
		// rotation attributes
		RotationAttributes ra = (RotationAttributes) soc.getAttributes(RotationAttributes.ID);
		tmp.add(String.valueOf(ra.getAngle()));
		
		// layer attributes
		LayerAttributes la = (LayerAttributes) soc.getAttributes(LayerAttributes.ID);
		tmp.add(String.valueOf(la.getLayer()));
		
		return tmp.toString();
	}

}

