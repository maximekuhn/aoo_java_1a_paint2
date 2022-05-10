package graphics.shapes;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SImage extends Shape {

	private Rectangle rect;
	private Image img;
	private File path;
	
	public SImage(Point point, Image img, File path) {
		this.rect = new Rectangle(point.x, point.y, img.getWidth(null), img.getHeight(null));
		this.img = img;
		this.path = path;
	}
	
	public SImage() {
		/*
		 * should not be used,
		 * usage is for opening a project file.
		 */
	}
	
	@Override
	public Point getLoc() {
		return new Point(this.rect.x, this.rect.y);
	}

	@Override
	public void setLoc(Point p) {
		this.rect.setLocation(p);
	}

	@Override
	public void translate(int dx, int dy) {
		this.rect.setLocation(this.rect.x + dx, this.rect.y + dy);
	}

	@Override
	public Rectangle getBounds() {
		return this.rect.getBounds();
	}

	@Override
	public void accept(ShapeVisitor v) {
		v.visitSImage(this);
	}
	
	public Image getImage() {
		return this.img;
	}
	
	public File getPath() {
		return this.path;
	}

	@Override
	public Shape copy() {
		SImage si = new SImage(this.getLoc(), this.img, this.path);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		si.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		si.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes  ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		si.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		si.addAttributes(new LayerAttributes(la.getLayer()));
		return si;
	}

	@Override
	public void resize(int dx, int dy) {
		// TODO Auto-generated method stub
		this.img = img.getScaledInstance(this.img.getWidth(null) + dx, img.getHeight(null) + dy, Image.SCALE_SMOOTH);
		this.rect = new Rectangle(this.getLoc().x, this.getLoc().y, img.getWidth(null), img.getHeight(null));
	}
	
	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		SImage si = (SImage) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");
		
		// basic
		tmp.add(si.getClass().getName());
		tmp.add(String.valueOf(si.getLoc().x));
		tmp.add(String.valueOf(si.getLoc().y));
		tmp.add(String.valueOf(si.getPath()));
		
		// color attributes
		ColorAttributes ca = (ColorAttributes) si.getAttributes(ColorAttributes.ID);
		tmp.add(String.valueOf(ca.filled));
		tmp.add(String.valueOf(ca.stroked));
		tmp.add(String.valueOf(ca.filledColor.getRGB()));
		tmp.add(String.valueOf(ca.strokedColor.getRGB()));
		
		// selection attributes
		SelectionAttributes sa = (SelectionAttributes) si.getAttributes(SelectionAttributes.ID);
		tmp.add(String.valueOf(sa.isSelected()));
		
		// rotation attributes
		RotationAttributes ra = (RotationAttributes) si.getAttributes(RotationAttributes.ID);
		tmp.add(String.valueOf(ra.getAngle()));
		
		// layer attributes
		LayerAttributes la = (LayerAttributes) si.getAttributes(LayerAttributes.ID);
		tmp.add(String.valueOf(la.getLayer()));
		
		return tmp.toString();
	}
	
}
