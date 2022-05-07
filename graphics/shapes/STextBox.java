package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class STextBox extends Shape {

	private String text;
	private Rectangle rect;

	public STextBox(Point loc, String text) {
		this.text = text;
		this.rect = new Rectangle(loc.x, loc.y, minBounds().width, minBounds().height);
	}

	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public Point getLoc() {
		return new Point(this.rect.x, this.rect.y);
	}

	@Override
	public void setLoc(Point p) {
		this.rect.setLocation(p);
	}

	public Point getTextLoc() {
		FontAttributes fa = (FontAttributes) this.getAttributes(FontAttributes.ID);
		if(fa == null) fa = new FontAttributes();
		int x = 0;
		int y = 0;
		if (fa.alignX == 0) {
			x = this.rect.x + 2;
		} else if (fa.alignX == 1) {
			x = this.rect.x + this.rect.width/2 - fa.getBounds(this.text).width/2;
		} else {
			x = this.rect.x + this.rect.width - fa.getBounds(this.text).width;
		}
		if (fa.alignY == 0) {
			y = this.rect.y - fa.getBounds(this.text).height;
		} else if (fa.alignY == 1) {
			y = this.rect.y + this.rect.height/2 + fa.getBounds(this.text).height/2;
		} else {
			y = this.rect.y + this.rect.height;
		}
		return new Point(x, y);
	}

	@Override
	public void translate(int dx, int dy) {
		this.rect.setLocation(this.rect.x + dx, this.rect.y + dy);
	}

	@Override
	public Rectangle getBounds() {
		return this.rect.getBounds();
	}

	public Rectangle minBounds() {
		FontAttributes fa = (FontAttributes) this.getAttributes(FontAttributes.ID);
		if(fa == null) fa = new FontAttributes();
		int width = fa.getBounds(this.text).width;
		int height = fa.getBounds(this.text).height;
		return new Rectangle(0, 0, width, height);
	}

	@Override
	public void accept(ShapeVisitor v) {
		v.visitTextBox(this);
	}

	@Override
	public Shape copy() {
		STextBox st = new STextBox(new Point(this.getLoc()), this.text);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		st.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		st.addAttributes(new SelectionAttributes(sa.isSelected()));
		FontAttributes fa = (FontAttributes) this.getAttributes(FontAttributes.ID);
		if(fa == null) fa = new FontAttributes();
		st.addAttributes(new FontAttributes(fa.font, fa.fontColor, fa.fontSize, fa.alignX, fa.alignY));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		st.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		st.addAttributes(new LayerAttributes(la.getLayer()));
		return st;
	}

	@Override
	public void resize(int dx, int dy) {
		FontAttributes fa = (FontAttributes) this.getAttributes(FontAttributes.ID);
		if(fa == null) fa = new FontAttributes();
		int width = fa.getBounds(this.text).width;
		int height = fa.getBounds(this.text).height;
		if ((int)this.rect.getWidth()+dx < width) {
			dx = 0;
		}
		if ((int)this.rect.getHeight()+dy < height) {
			dy = 0;
		}
		this.rect.setSize((int)this.rect.getWidth()+dx, (int)this.rect.getHeight()+dy);
	}
	
}
