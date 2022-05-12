package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SEllipse extends Shape {

	private Rectangle ellipse;
	private int width;
	private int height;
	
	public SEllipse(Point point, int width, int height) {
		this.width = width;
		this.height = height;
		this.ellipse = new Rectangle(point.x, point.y, width, height);
	}

	@Override
	public Point getLoc() {
		return new Point(this.ellipse.x, this.ellipse.y);
	}

	@Override
	public void setLoc(Point p) {
		this.ellipse.setLocation(p);
	}

	@Override
	public void translate(int dx, int dy) {
		this.ellipse.setLocation(this.ellipse.x + dx, this.ellipse.y + dy);
	}

	@Override
	public Rectangle getBounds() {
		return this.ellipse.getBounds();
	}

	@Override
	public void accept(ShapeVisitor v) {
		v.visitEllipse(this);
	}

	@Override
	public Shape copy() {
		SEllipse se = new SEllipse(this.getLoc(), this.getBounds().width, this.getBounds().height);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		se.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		se.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		se.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		se.addAttributes(new LayerAttributes(la.getLayer()));
		return se;
	}

	@Override
	public void resize(int dx, int dy) {
		this.ellipse.setSize((int)this.ellipse.getWidth()+dx, (int)this.ellipse.getHeight()+dy);
	}

	public void setBounds(Rectangle bounds) {
		this.ellipse.setBounds(bounds);
	}
	
	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		SEllipse se = (SEllipse) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");
		
		// basic
		tmp.add(se.getClass().getName());
		tmp.add(String.valueOf(se.getLoc().x));
		tmp.add(String.valueOf(se.getLoc().y));
		tmp.add(String.valueOf(se.getBounds().width));
		tmp.add(String.valueOf(se.getBounds().height));
		
		// color attributes
		ColorAttributes ca = (ColorAttributes) se.getAttributes(ColorAttributes.ID);
		tmp.add(String.valueOf(ca.filled));
		tmp.add(String.valueOf(ca.stroked));
		tmp.add(String.valueOf(ca.filledColor.getRGB()));
		tmp.add(String.valueOf(ca.strokedColor.getRGB()));
		
		// selection attributes
		SelectionAttributes sa = (SelectionAttributes) se.getAttributes(SelectionAttributes.ID);
		tmp.add(String.valueOf(sa.isSelected()));
		
		// rotation attributes
		RotationAttributes ra = (RotationAttributes) se.getAttributes(RotationAttributes.ID);
		tmp.add(String.valueOf(ra.getAngle()));
		
		// layer attributes
		LayerAttributes la = (LayerAttributes) se.getAttributes(LayerAttributes.ID);
		tmp.add(String.valueOf(la.getLayer()));
		
		return tmp.toString();
	}
	
}
