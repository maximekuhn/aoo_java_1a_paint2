package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SPentagon extends Shape {

    private Polygon pentagon;
    private Point loc;
	private int nPoints;
	private int[] xPentagon;
	private int[] yPentagon;
	
	private int width;
	private int height;
	
	public SPentagon(Point loc, int width, int height) {
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.nPoints = 5;
		this.buildPolygon();
	}
	
	private void buildPolygon() {
		this.nPoints = 5;
		this.xPentagon = new int[this.nPoints];
		this.yPentagon = new int[this.nPoints];

		this.xPentagon[0] = this.loc.x + this.width / 2;
		this.yPentagon[0] = this.loc.y;
		this.xPentagon[1] = this.loc.x + this.width;
		this.yPentagon[1] = this.loc.y + this.height * 2/5;
		this.xPentagon[2] = this.loc.x + this.width * 5/6;
		this.yPentagon[2] = this.loc.y + this.height;
		this.xPentagon[3] = this.loc.x + this.width * 1/6 ;
		this.yPentagon[3] = this.loc.y + this.height;
		this.xPentagon[4] = this.loc.x;
		this.yPentagon[4] = this.loc.y + this.height * 2/5;

		this.pentagon = new Polygon(this.xPentagon, this.yPentagon, this.nPoints);
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
		SPentagon spt = new SPentagon(new Point(this.getLoc()), this.getBounds().width, this.getBounds().height);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		spt.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		spt.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		spt.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		spt.addAttributes(new LayerAttributes(la.getLayer()));
		return spt;
	}

	@Override
	public void resize(int dx, int dy) {
		this.width += dx;
		this.height += dy;
		this.buildPolygon();
	}
	
    public Polygon getPolygon(){
        return this.pentagon;

    }
    
    @Override
    public void accept(ShapeVisitor v){
        v.visitSPentagon(this);
    }



	@Override
	public Rectangle getBounds() {

		return this.pentagon.getBounds();
	}
	

	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		SPentagon spt = (SPentagon) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");
		
		// basic
		tmp.add(spt.getClass().getName());
		tmp.add(String.valueOf(spt.getLoc().x));
		tmp.add(String.valueOf(spt.getLoc().y));
		tmp.add(String.valueOf(spt.getBounds().width));
		tmp.add(String.valueOf(spt.getBounds().height));
		
		// color attributes
		ColorAttributes ca = (ColorAttributes) spt.getAttributes(ColorAttributes.ID);
		tmp.add(String.valueOf(ca.filled));
		tmp.add(String.valueOf(ca.stroked));
		tmp.add(String.valueOf(ca.filledColor.getRGB()));
		tmp.add(String.valueOf(ca.strokedColor.getRGB()));
		
		// selection attributes
		SelectionAttributes sa = (SelectionAttributes) spt.getAttributes(SelectionAttributes.ID);
		tmp.add(String.valueOf(sa.isSelected()));
		
		// rotation attributes
		RotationAttributes ra = (RotationAttributes) spt.getAttributes(RotationAttributes.ID);
		tmp.add(String.valueOf(ra.getAngle()));
		
		// layer attributes
		LayerAttributes la = (LayerAttributes) spt.getAttributes(LayerAttributes.ID);
		tmp.add(String.valueOf(la.getLayer()));
		
		return tmp.toString();
	}

}

