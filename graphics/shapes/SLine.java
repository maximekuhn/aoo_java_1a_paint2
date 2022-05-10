package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SLine extends Shape {
	private Polygon line;
	private Point loc;
	private int nPoints;
	private int[] xLine;
	private int[] yLine;
	private int width;
	private int height;
	
	public SLine (Point loc, int width, int height) {
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.nPoints = 2;
		this.buildPolygon();
	}
	
	private void buildPolygon() {
		this.nPoints = 2;
		this.xLine = new int[this.nPoints];
		this.yLine = new int[this.nPoints];

		this.xLine[0] = this.loc.x;
		this.yLine[0] = this.loc.y + height;
		this.xLine[1] = this.loc.x + width;
		this.yLine[1] = this.loc.y;


		this.line = new Polygon(this.xLine, this.yLine, this.nPoints);
		}
	
	@Override
	public Point getLoc() {
		return this.loc;
	}

	@Override
	public void setLoc(Point p) {
		this.loc.setLocation(p);
		this.buildPolygon();
		
	}

	@Override
	public void translate(int dx, int dy) {
		this.setLoc(new Point(this.loc.x + dx, this.loc.y + dy));
		this.buildPolygon();
		
	}

	@Override
	public Rectangle getBounds() {
		return this.line.getBounds();
	}
	
    public Polygon getPolygon(){
        return this.line;

    }

	@Override
	public void accept(ShapeVisitor v) {
		 v.visitSLine(this);
		
	}

	@Override
	public Shape copy() {
			SLine ln = new SLine(new Point(this.getLoc()), this.getBounds().width, this.getBounds().height);
			ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
			if(ca == null) ca = new ColorAttributes();
			ln.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
			SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
			if(sa == null) sa = new SelectionAttributes();
			ln.addAttributes(new SelectionAttributes(sa.isSelected()));
			RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
			if(ra == null) ra = new RotationAttributes();
			ln.addAttributes(new RotationAttributes(ra.getAngle()));
			LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
			if(la == null) la = new LayerAttributes();
			ln.addAttributes(new LayerAttributes(la.getLayer()));
			return ln;
	}

	@Override
	public void resize(int dx, int dy) {
		this.width += dx;
		this.height += dy;
		this.buildPolygon();
		
	}
	
	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		SLine ln = (SLine) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");
		
		// basic
		tmp.add(ln.getClass().getName());
		tmp.add(String.valueOf(ln.getLoc().x));
		tmp.add(String.valueOf(ln.getLoc().y));
		tmp.add(String.valueOf(ln.getBounds().width));
		tmp.add(String.valueOf(ln.getBounds().height));
		
		// color attributes
		ColorAttributes ca = (ColorAttributes) ln.getAttributes(ColorAttributes.ID);
		tmp.add(String.valueOf(ca.filled));
		tmp.add(String.valueOf(ca.stroked));
		tmp.add(String.valueOf(ca.filledColor.getRGB()));
		tmp.add(String.valueOf(ca.strokedColor.getRGB()));
		
		// selection attributes
		SelectionAttributes sa = (SelectionAttributes) ln.getAttributes(SelectionAttributes.ID);
		tmp.add(String.valueOf(sa.isSelected()));
		
		// rotation attributes
		RotationAttributes ra = (RotationAttributes) ln.getAttributes(RotationAttributes.ID);
		tmp.add(String.valueOf(ra.getAngle()));
		
		// layer attributes
		LayerAttributes la = (LayerAttributes) ln.getAttributes(LayerAttributes.ID);
		tmp.add(String.valueOf(la.getLayer()));
		
		return tmp.toString();
	}

}
