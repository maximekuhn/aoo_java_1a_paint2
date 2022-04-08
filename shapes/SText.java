package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.FontAttributes;

public class SText extends Shape {

	private String text;
	private Point loc;
	
	public SText(Point loc, String text) {
		this.loc = loc;
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public Point getLoc() {
		return this.loc;
	}

	@Override
	public void setLoc(Point p) {
		this.loc.setLocation(p);
	}

	@Override
	public void translate(int dx, int dy) {	
		this.loc.setLocation(this.loc.x + dx, this.loc.y + dy);
	}

	@Override
	public Rectangle getBounds() {
		FontAttributes fa = (FontAttributes) this.getAttributes(FontAttributes.ID);
		if(fa == null) fa = new FontAttributes();
		int width = fa.getBounds(this.text).width;
		int height = fa.getBounds(this.text).height;
		return new Rectangle(this.loc.x, this.loc.y - height, width, height);
	}

	@Override
	public void accept(ShapeVisitor v) {
		v.visitText(this);
	}
}
