package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SOctagon extends Shape {

    private Polygon octagon;
    private Point loc;
	private int nPoints;
	private int[] xOctagon;
	private int[] yOctagon;
	
	private int width;
	private int height;
	
	public SOctagon(Point loc, int width, int height) {
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.nPoints = 5;
		this.buildPolygon();
	}
	
	private void buildPolygon() {
		this.nPoints = 5;
		this.xOctagon = new int[this.nPoints];
		this.yOctagon = new int[this.nPoints];

		this.xOctagon[0] = this.loc.x + this.width / 2;
		this.yOctagon[0] = this.loc.y;
		this.xOctagon[1] = this.loc.x + this.width;
		this.yOctagon[1] = this.loc.y + this.height * 2/5;
		this.xOctagon[2] = this.loc.x + this.width * 5/6;
		this.yOctagon[2] = this.loc.y + this.height;
		this.xOctagon[3] = this.loc.x + this.width * 1/6 ;
		this.yOctagon[3] = this.loc.y + this.height;
		this.xOctagon[4] = this.loc.x;
		this.yOctagon[4] = this.loc.y + this.height * 2/5;

		this.octagon = new Polygon(this.xOctagon, this.yOctagon, this.nPoints);
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
		SOctagon soc = new SOctagon(new Point(this.getLoc()), this.getBounds().width, this.getBounds().height);
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
        return this.octagon;

    }
    
    @Override
    public void accept(ShapeVisitor v){
        v.visitSOctagon(this);
    }



	@Override
	public Rectangle getBounds() {

		return this.octagon.getBounds();
	}
	

	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		SOctagon soc = (SOctagon) this.copy();
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

