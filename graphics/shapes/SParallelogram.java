package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SParallelogram extends Shape {

    private Polygon parallelogram;
    private Point loc;
	private int nPoints;
	private int[] xParallelogram;
	private int[] yParallelogram;
	
	private int width;
	private int height;
	
	public SParallelogram(Point loc, int width, int height) {
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.nPoints = 5;
		this.buildPolygon();
	}
	
	private void buildPolygon() {
		this.nPoints = 5;
		this.xParallelogram = new int[this.nPoints];
		this.yParallelogram = new int[this.nPoints];

		this.xParallelogram[0] = this.loc.x + this.width / 2;
		this.yParallelogram[0] = this.loc.y;
		this.xParallelogram[1] = this.loc.x + this.width;
		this.yParallelogram[1] = this.loc.y + this.height * 2/5;
		this.xParallelogram[2] = this.loc.x + this.width * 5/6;
		this.yParallelogram[2] = this.loc.y + this.height;
		this.xParallelogram[3] = this.loc.x + this.width * 1/6 ;
		this.yParallelogram[3] = this.loc.y + this.height;
		this.xParallelogram[4] = this.loc.x;
		this.yParallelogram[4] = this.loc.y + this.height * 2/5;

		this.parallelogram = new Polygon(this.xParallelogram, this.yParallelogram, this.nPoints);
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
		SParallelogram spg = new SParallelogram(new Point(this.getLoc()), this.getBounds().width, this.getBounds().height);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		spg.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		spg.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		spg.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		spg.addAttributes(new LayerAttributes(la.getLayer()));
		return spg;
	}

	@Override
	public void resize(int dx, int dy) {
		this.width += dx;
		this.height += dy;
		this.buildPolygon();
	}
	
    public Polygon getPolygon(){
        return this.parallelogram;

    }
    
    @Override
    public void accept(ShapeVisitor v){
        v.visitSParallelogram(this);
    }



	@Override
	public Rectangle getBounds() {

		return this.parallelogram.getBounds();
	}
	

	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		SParallelogram spg = (SParallelogram) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");
		
		// basic
		tmp.add(spg.getClass().getName());
		tmp.add(String.valueOf(spg.getLoc().x));
		tmp.add(String.valueOf(spg.getLoc().y));
		tmp.add(String.valueOf(spg.getBounds().width));
		tmp.add(String.valueOf(spg.getBounds().height));
		
		// color attributes
		ColorAttributes ca = (ColorAttributes) spg.getAttributes(ColorAttributes.ID);
		tmp.add(String.valueOf(ca.filled));
		tmp.add(String.valueOf(ca.stroked));
		tmp.add(String.valueOf(ca.filledColor.getRGB()));
		tmp.add(String.valueOf(ca.strokedColor.getRGB()));
		
		// selection attributes
		SelectionAttributes sa = (SelectionAttributes) spg.getAttributes(SelectionAttributes.ID);
		tmp.add(String.valueOf(sa.isSelected()));
		
		// rotation attributes
		RotationAttributes ra = (RotationAttributes) spg.getAttributes(RotationAttributes.ID);
		tmp.add(String.valueOf(ra.getAngle()));
		
		// layer attributes
		LayerAttributes la = (LayerAttributes) spg.getAttributes(LayerAttributes.ID);
		tmp.add(String.valueOf(la.getLayer()));
		
		return tmp.toString();
	}

}

