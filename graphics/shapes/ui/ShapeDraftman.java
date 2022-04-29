package graphics.shapes.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.Iterator;
import java.util.LinkedList;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SImage;
import graphics.shapes.SRectangle;
import graphics.shapes.SSketch;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class ShapeDraftman implements ShapeVisitor {

	private static final ColorAttributes DEFAULTCOLORATTRIBUTES = new ColorAttributes();
	private static final SelectionAttributes DEFAULTSELECTIONATTRIBUTES = new SelectionAttributes();
	private static final FontAttributes DEFAULTFONTATTRIBUTES = new FontAttributes();
	private static final RotationAttributes DEFAULTROTATIONATTRIBUTES = new RotationAttributes();
	private static final int HANDLER_SIZE = 6;
	private Graphics2D g2D;
	
	@Override
	public void visitRectangle(SRectangle sr) {
		Rectangle r = sr.getBounds();
		
		RotationAttributes ra = (RotationAttributes) sr.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = DEFAULTROTATIONATTRIBUTES;
		AffineTransform old = this.g2D.getTransform();
		this.g2D.rotate(Math.toRadians(ra.getAngle()), sr.getBounds().x + sr.getBounds().width / 2, sr.getBounds().y + sr.getBounds().height / 2);
		
		ColorAttributes ca = (ColorAttributes) sr.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = DEFAULTCOLORATTRIBUTES;
		if(ca.filled) {
			this.g2D.setColor(ca.filledColor);
			this.g2D.fill(r);
		}
		if(ca.stroked) {
			this.g2D.setColor(ca.strokedColor);
			this.g2D.draw(r);
		}
		
		SelectionAttributes sa = (SelectionAttributes) sr.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = DEFAULTSELECTIONATTRIBUTES;
		if(sa.isSelected()) {
			Rectangle bounds = sr.getBounds();
			this.g2D.setColor(DEFAULTCOLORATTRIBUTES.strokedColor);
			this.g2D.drawRect(bounds.x - HANDLER_SIZE / 2, bounds.y - HANDLER_SIZE / 2, HANDLER_SIZE / 2, HANDLER_SIZE / 2);
			this.g2D.drawRect(bounds.x + bounds.width, bounds.y + bounds.height, HANDLER_SIZE / 2, HANDLER_SIZE / 2);
		}
		
		this.g2D.setTransform(old);
	}
	
	@Override
	public void visitCircle(SCircle sc) {
		Point loc = sc.getLoc();
		int radius = sc.getRadius();
		
		RotationAttributes ra = (RotationAttributes) sc.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = DEFAULTROTATIONATTRIBUTES;
		AffineTransform old = this.g2D.getTransform();
		this.g2D.rotate(Math.toRadians(ra.getAngle()), sc.getBounds().x + sc.getBounds().width / 2, sc.getBounds().y + sc.getBounds().height / 2);
		
		ColorAttributes ca = (ColorAttributes) sc.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = DEFAULTCOLORATTRIBUTES;
		if(ca.filled) {
			this.g2D.setColor(ca.filledColor);
			this.g2D.fillOval(loc.x, loc.y, radius, radius);
		}
		if(ca.stroked) {
			this.g2D.setColor(ca.strokedColor);
			this.g2D.drawOval(loc.x, loc.y, radius, radius);
		}
		
		SelectionAttributes sa = (SelectionAttributes) sc.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = DEFAULTSELECTIONATTRIBUTES;
		if(sa.isSelected()) {
			Rectangle bounds = sc.getBounds();
			this.g2D.setColor(DEFAULTCOLORATTRIBUTES.strokedColor);
			this.g2D.drawRect(bounds.x - HANDLER_SIZE / 2, bounds.y - HANDLER_SIZE / 2, HANDLER_SIZE / 2, HANDLER_SIZE / 2);
			this.g2D.drawRect(bounds.x + bounds.width, bounds.y + bounds.height, HANDLER_SIZE / 2, HANDLER_SIZE / 2);
		}
		
		this.g2D.setTransform(old);
	}

	@Override
	public void visitText(SText st) {
		RotationAttributes ra = (RotationAttributes) st.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = DEFAULTROTATIONATTRIBUTES;
		AffineTransform old = this.g2D.getTransform();
		this.g2D.rotate(Math.toRadians(ra.getAngle()), st.getBounds().x + st.getBounds().width / 2, st.getBounds().y + st.getBounds().height / 2);
		
		
		ColorAttributes ca = (ColorAttributes) st.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = DEFAULTCOLORATTRIBUTES;
		if(ca.filled) {
			this.g2D.setColor(ca.filledColor);
			this.g2D.fill(st.getBounds());
		}
		if(ca.stroked) {
			this.g2D.setColor(ca.strokedColor);
			this.g2D.draw(st.getBounds());
		}
		
		SelectionAttributes sa = (SelectionAttributes) st.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = DEFAULTSELECTIONATTRIBUTES;
		if(sa.isSelected()) {
			Rectangle bounds = st.getBounds();
			this.g2D.setColor(DEFAULTCOLORATTRIBUTES.strokedColor);
			this.g2D.drawRect(bounds.x - HANDLER_SIZE / 2, bounds.y - HANDLER_SIZE / 2, HANDLER_SIZE / 2, HANDLER_SIZE / 2);
			this.g2D.drawRect(bounds.x + bounds.width, bounds.y + bounds.height, HANDLER_SIZE / 2, HANDLER_SIZE / 2);
		}
		
		FontAttributes fa = (FontAttributes) st.getAttributes(FontAttributes.ID);
		if(fa == null) fa = DEFAULTFONTATTRIBUTES;
		this.g2D.setFont(fa.font);
		this.g2D.setColor(fa.fontColor);
		this.g2D.drawString(st.getText(), st.getLoc().x, st.getLoc().y);
		
		this.g2D.setTransform(old);
	}
	
	@Override
	public void visitCollection(SCollection sc) {
		Iterator<Shape> i = sc.iterator();
		if(!i.hasNext()) return;
		
		RotationAttributes ra = (RotationAttributes) sc.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = DEFAULTROTATIONATTRIBUTES;
		AffineTransform old = this.g2D.getTransform();
		this.g2D.rotate(Math.toRadians(ra.getAngle()), sc.getBounds().x + sc.getBounds().width / 2, sc.getBounds().y + sc.getBounds().height / 2);
		
		while(i.hasNext())
			i.next().accept(this);
		
		SelectionAttributes sa = (SelectionAttributes) sc.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = DEFAULTSELECTIONATTRIBUTES;
		if(sa.isSelected()) {
			Rectangle bounds = sc.getBounds();
			this.g2D.setColor(DEFAULTCOLORATTRIBUTES.strokedColor);
			this.g2D.drawRect(bounds.x - HANDLER_SIZE / 2, bounds.y - HANDLER_SIZE / 2, HANDLER_SIZE / 2, HANDLER_SIZE / 2);
			this.g2D.drawRect(bounds.x + bounds.width, bounds.y + bounds.height, HANDLER_SIZE / 2, HANDLER_SIZE / 2);
		}
		
		this.g2D.setTransform(old);
	}
	
	@Override
	public void visitImage(SImage si) {
		RotationAttributes ra = (RotationAttributes) si.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = DEFAULTROTATIONATTRIBUTES;
		AffineTransform old = this.g2D.getTransform();
		this.g2D.rotate(Math.toRadians(ra.getAngle()), si.getBounds().x + si.getBounds().width / 2, si.getBounds().y + si.getBounds().height / 2);
		
		this.g2D.drawImage(si.getImage(), si.getBounds().x, si.getBounds().y, null);

		ColorAttributes ca = (ColorAttributes) si.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = DEFAULTCOLORATTRIBUTES;
		if(ca.filled) {
			this.g2D.setColor(ca.filledColor);
			this.g2D.fill(si.getBounds());
		}
		if(ca.stroked) {
			this.g2D.setColor(ca.strokedColor);
			this.g2D.draw(si.getBounds());
		}
		
		SelectionAttributes sa = (SelectionAttributes) si.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = DEFAULTSELECTIONATTRIBUTES;
		if(sa.isSelected()) {
			Rectangle bounds = si.getBounds();
			this.g2D.setColor(DEFAULTCOLORATTRIBUTES.strokedColor);
			this.g2D.drawRect(bounds.x - HANDLER_SIZE / 2, bounds.y - HANDLER_SIZE / 2, HANDLER_SIZE / 2, HANDLER_SIZE / 2);
			this.g2D.drawRect(bounds.x + bounds.width, bounds.y + bounds.height, HANDLER_SIZE / 2, HANDLER_SIZE / 2);
		}
		
		this.g2D.setTransform(old);
	}
	
	@Override
	public void visitSSketch(SSketch sk) {
		LinkedList<Point> points = sk.getPoints();
		
		ColorAttributes ca = (ColorAttributes) sk.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = DEFAULTCOLORATTRIBUTES;
		
		RotationAttributes ra = (RotationAttributes) sk.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = DEFAULTROTATIONATTRIBUTES;
		AffineTransform old = this.g2D.getTransform();
		this.g2D.rotate(Math.toRadians(ra.getAngle()), sk.getBounds().x + sk.getBounds().width / 2, sk.getBounds().y + sk.getBounds().height / 2);
		
		for(int i=0; i< points.size() - 1; i++) {
			Point current = points.get(i);
			Point next = points.get(i + 1);
			this.g2D.drawLine(current.x, current.y, next.x, next.y);
		}
		
		SelectionAttributes sa = (SelectionAttributes) sk.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = DEFAULTSELECTIONATTRIBUTES;
		if(sa.isSelected()) {
			Rectangle bounds = sk.getBounds();
			this.g2D.setColor(DEFAULTCOLORATTRIBUTES.strokedColor);
			this.g2D.drawRect(bounds.x - HANDLER_SIZE / 2, bounds.y - HANDLER_SIZE / 2, HANDLER_SIZE / 2, HANDLER_SIZE / 2);
			this.g2D.drawRect(bounds.x + bounds.width, bounds.y + bounds.height, HANDLER_SIZE / 2, HANDLER_SIZE / 2);
		}
		
		this.g2D.setTransform(old);
	}

	public void setGraphics(Graphics g) {
		this.g2D = (Graphics2D) g;
		
		// anti-aliasing
		this.g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
}
