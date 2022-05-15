package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.StringJoiner;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class STextBox extends Shape {

	private String[] text;
	private Rectangle rect;

	public STextBox(Point loc, String text) {
		this.text = text.split("\\R", -1);
		this.rect = new Rectangle(loc.x, loc.y, minBounds().width, minBounds().height);
	}

	public STextBox(Point loc, String text, int width, int height) {
		this.text = text.split("\\R", -1);
		this.rect = new Rectangle(loc.x, loc.y, width, height);
	}

	public String getText() {
		return String.join(System.lineSeparator(), this.text);
	}
	
	public void setText(String text) {
		this.text = text.split("\\R", -1);
		int dx = 0;
		int dy = 0;
		if (this.rect.getBounds().width < this.minBounds().width) {
			dx = this.minBounds().width - this.rect.getBounds().width;
		}
		if (this.rect.getBounds().height < this.minBounds().height) {
			dy = this.minBounds().height - this.rect.getBounds().height;
		}
		this.resize(dx, dy);
	}
	
	@Override
	public Point getLoc() {
		return new Point(this.rect.x, this.rect.y);
	}

	@Override
	public void setLoc(Point p) {
		this.rect.setLocation(p);
	}

	public Point getTextLoc(int line) {
		FontAttributes fa = (FontAttributes) this.getAttributes(FontAttributes.ID);
		if(fa == null) fa = new FontAttributes();
		int x = 0;
		int y = 0;
		//if (this.text.isEmpty) this.text[0] = "";
		if (line > this.text.length-1) line = this.text.length-1;
		String str = this.text[line];
		if (fa.alignX == 0) {
			x = this.rect.x + 2;
		} else if (fa.alignX == 1) {
			x = this.rect.x + this.rect.width/2 - fa.getBounds(str).width/2;
		} else {
			x = this.rect.x + this.rect.width - fa.getBounds(str).width;
		}
		if (fa.alignY == 0) {
			y = this.rect.y + fa.getBounds(str).height*(line+1);
		} else if (fa.alignY == 1) {
			y = this.rect.y + this.rect.height/2 - (fa.getBounds(str).height*this.text.length)/2 + fa.getBounds(str).height*(line+1);
		} else {
			y = this.rect.y + this.rect.height - fa.getBounds(str).height*(this.text.length-line-1);
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
		int width = fa.getBoundsLines(this.text).width;
		int height = fa.getBoundsLines(this.text).height;
		return new Rectangle(0, 0, width, height);
	}

	@Override
	public void accept(ShapeVisitor v) {
		v.visitTextBox(this);
	}

	@Override
	public Shape copy() {
		STextBox st = new STextBox(new Point(this.getLoc()), String.join(System.lineSeparator(), this.text), this.rect.width, this.rect.height);
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		st.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		st.addAttributes(new SelectionAttributes(sa.isSelected()));
		FontAttributes fa = (FontAttributes) this.getAttributes(FontAttributes.ID);
		if(fa == null) fa = new FontAttributes();
		st.addAttributes(new FontAttributes(fa.font, fa.fontColor, fa.fontSize, fa.alignX, fa.alignY, fa.style));
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
		int width = fa.getBoundsLines(this.text).width;
		int height = fa.getBoundsLines(this.text).height;
		if ((int)this.rect.getWidth()+dx < width) {
			dx = 0;
		}
		if ((int)this.rect.getHeight()+dy < height) {
			dy = 0;
		}
		this.rect.setSize((int)this.rect.getWidth()+dx, (int)this.rect.getHeight()+dy);
	}
	
	@Override
	public String toString() {
		/*
		 * take copy, because copy always have all attributes
		 * (no need to check)
		 */
		STextBox st = (STextBox) this.copy();
		StringJoiner tmp = new StringJoiner(" ", "[ " , " ]");
		
		// basic
		tmp.add(st.getClass().getName());
		tmp.add(String.valueOf(st.getLoc().x));
		tmp.add(String.valueOf(st.getLoc().y));
		tmp.add(String.valueOf(st.getBounds().width));
		tmp.add(String.valueOf(st.getBounds().height));
		
		// text in one line (with separator)
		// replace new line with \n and blank with \w
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < this.text.length; i++) {
			sb.append(this.text[i].replace(" ", "\\w"));
			if(i != this.text.length - 1)
				sb.append("\\n");
		}
		tmp.add(sb.toString());
		
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
		StringJoiner sj = new StringJoiner(",");
		sj.add(fa.font.getName().replace(" ", "\\w"));
		sj.add(String.valueOf(fa.font.getStyle()));
		sj.add(String.valueOf(fa.font.getSize()));
		tmp.add(sj.toString());
		tmp.add(String.valueOf(fa.fontColor.getRGB()));
		tmp.add(String.valueOf(fa.alignX));
		tmp.add(String.valueOf(fa.alignY));
		
		return tmp.toString();
	}
	
}
