package graphics.shapes.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SImage;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class ShapeDraftman implements ShapeVisitor {

	private static final ColorAttributes DEFAULTCOLORATTRIBUTES = new ColorAttributes();
	private static final SelectionAttributes DEFAULTSELECTIONATTRIBUTES = new SelectionAttributes();
	private static final FontAttributes DEFAULTFONTATTRIBUTES = new FontAttributes();
	private static final int HANDLER_SIZE = 6;
	private Graphics2D g2D;
	
	@Override
	public void visitRectangle(SRectangle sr) {
		Rectangle r = sr.getBounds();
		
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
	}
	
	@Override
	public void visitCircle(SCircle sc) {
		Point loc = sc.getLoc();
		int radius = sc.getRadius();
		
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
	}

	@Override
	public void visitText(SText st) {
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
	}
	
	@Override
	public void visitCollection(SCollection sc) {
		Iterator<Shape> i = sc.iterator();
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
	}
	
	@Override
	public void visitImage(SImage si) {
		this.g2D.drawImage(si.getImage(), si.getBounds().x, si.getBounds().y, null);

		ColorAttributes ca = (ColorAttributes) si.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = DEFAULTCOLORATTRIBUTES;
		if(ca.filled) {
			this.g2D.setColor(ca.filledColor);
			this.g2D.fill(si.getRect());
		}
		if(ca.stroked) {
			this.g2D.setColor(ca.strokedColor);
			this.g2D.draw(si.getRect());
		}
		
		SelectionAttributes sa = (SelectionAttributes) si.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = DEFAULTSELECTIONATTRIBUTES;
		if(sa.isSelected()) {
			Rectangle bounds = si.getBounds();
			this.g2D.setColor(DEFAULTCOLORATTRIBUTES.strokedColor);
			this.g2D.drawRect(bounds.x - HANDLER_SIZE / 2, bounds.y - HANDLER_SIZE / 2, HANDLER_SIZE / 2, HANDLER_SIZE / 2);
			this.g2D.drawRect(bounds.x + bounds.width, bounds.y + bounds.height, HANDLER_SIZE / 2, HANDLER_SIZE / 2);
		}
	}
	
	public void setGraphics(Graphics g) {
		this.g2D = (Graphics2D) g;
	}
	
}
