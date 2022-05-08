package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

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
		this.loc = new Point(p);
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

	@Override
	public Shape copy() {
		SText st = new SText(new Point(this.getLoc()), this.text);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		st.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		st.addAttributes(new SelectionAttributes(sa.isSelected()));
		FontAttributes fa = (FontAttributes) this.getAttributes(FontAttributes.ID);
		if(fa == null) fa = new FontAttributes();
		st.addAttributes(new FontAttributes(fa.font, fa.fontColor));
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
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		SText st = (SText) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");
		
		// basic
		tmp.add(st.getClass().getName());
		tmp.add(String.valueOf(st.getLoc().x));
		tmp.add(String.valueOf(st.getLoc().y));
		tmp.add(st.getText());
		
		// color attributes
		ColorAttributes ca = (ColorAttributes) st.getAttributes(ColorAttributes.ID);
		tmp.add(String.valueOf(ca.filled));
		tmp.add(String.valueOf(ca.stroked));
		tmp.add(String.valueOf(ca.filledColor.getRGB()));
		tmp.add(String.valueOf(ca.strokedColor.getRGB()));
		
		// selection attributes
		SelectionAttributes sa = (SelectionAttributes) st.getAttributes(SelectionAttributes.ID);
		tmp.add(String.valueOf(sa.isSelected()));
		
		// rotation attributes
		RotationAttributes ra = (RotationAttributes) st.getAttributes(RotationAttributes.ID);
		tmp.add(String.valueOf(ra.getAngle()));
		
		// layer attributes
		LayerAttributes la = (LayerAttributes) st.getAttributes(LayerAttributes.ID);
		tmp.add(String.valueOf(la.getLayer()));
		
		// font attributes
		FontAttributes fa = (FontAttributes) st.getAttributes(FontAttributes.ID);
		tmp.add(String.valueOf(fa.font));
		tmp.add(String.valueOf(fa.fontColor.getRGB()));
		
		return tmp.toString();
	}
}
