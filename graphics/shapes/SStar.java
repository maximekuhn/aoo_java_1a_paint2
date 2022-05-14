package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SStar extends Shape {

    private Polygon star;
    private Point loc;
	private int nPoints;
	private int[] xStar;
	private int[] yStar;
	
	private int width;
	private int height;
	
	public SStar(Point loc, int width, int height) {
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.nPoints = 10;
		this.buildPolygon();
	}
	
	private void buildPolygon() {
		this.nPoints = 10;
		this.xStar = new int[this.nPoints];
		this.yStar = new int[this.nPoints];

		this.xStar[0] = this.loc.x + this.width * 1/2;
		this.yStar[0] = this.loc.y;
		this.xStar[1] = this.loc.x + this.width * 2/3;
		this.yStar[1] = this.loc.y + this.height * 1/3;
		this.xStar[2] = this.loc.x + this.width;
		this.yStar[2] = this.loc.y + this.height * 2/5;
		this.xStar[3] = this.loc.x + this.width * 3/4;
		this.yStar[3] = this.loc.y + this.height * 3/5;
		this.xStar[4] = this.loc.x + this.width * 4/5;
		this.yStar[4] = this.loc.y + this.height;
		this.xStar[5] = this.loc.x + this.width / 2;
		this.yStar[5] = this.loc.y + this.height * 5/6;
		this.xStar[6] = this.loc.x + this.width * 1/5;
		this.yStar[6] = this.loc.y + this.height;
		this.xStar[7] = this.loc.x + this.width * 1/4;
		this.yStar[7] = this.loc.y + this.height * 3/5;
		this.xStar[8] = this.loc.x;
		this.yStar[8] = this.loc.y + this.height * 2/5;
		this.xStar[9] = this.loc.x + this.width * 1/3;
		this.yStar[9] = this.loc.y + this.height * 1/3;

		this.star = new Polygon(this.xStar, this.yStar, this.nPoints);
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
		SStar sst = new SStar(new Point(this.getLoc()), this.getBounds().width, this.getBounds().height);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		sst.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		sst.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		sst.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		sst.addAttributes(new LayerAttributes(la.getLayer()));
		return sst;
	}

	@Override
	public void resize(int dx, int dy) {
		this.width += dx;
		this.height += dy;
		this.buildPolygon();
	}
	
    public Polygon getPolygon(){
        return this.star;

    }
    
    @Override
    public void accept(ShapeVisitor v){
        v.visitSStar(this);
    }



	@Override
	public Rectangle getBounds() {

		return this.star.getBounds();
	}
	

	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		SStar sst = (SStar) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");
		
		// basic
		tmp.add(sst.getClass().getName());
		tmp.add(String.valueOf(sst.getLoc().x));
		tmp.add(String.valueOf(sst.getLoc().y));
		tmp.add(String.valueOf(sst.getBounds().width));
		tmp.add(String.valueOf(sst.getBounds().height));
		
		// color attributes
		ColorAttributes ca = (ColorAttributes) sst.getAttributes(ColorAttributes.ID);
		tmp.add(String.valueOf(ca.filled));
		tmp.add(String.valueOf(ca.stroked));
		tmp.add(String.valueOf(ca.filledColor.getRGB()));
		tmp.add(String.valueOf(ca.strokedColor.getRGB()));
		
		// selection attributes
		SelectionAttributes sa = (SelectionAttributes) sst.getAttributes(SelectionAttributes.ID);
		tmp.add(String.valueOf(sa.isSelected()));
		
		// rotation attributes
		RotationAttributes ra = (RotationAttributes) sst.getAttributes(RotationAttributes.ID);
		tmp.add(String.valueOf(ra.getAngle()));
		
		// layer attributes
		LayerAttributes la = (LayerAttributes) sst.getAttributes(LayerAttributes.ID);
		tmp.add(String.valueOf(la.getLayer()));
		
		return tmp.toString();
	}

}

