package graphics.shapes.attributes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;

public class FontAttributes extends Attributes {

	public static final String ID = "font";
	public static final Graphics2D DEFAULT_GRAPHICS = (Graphics2D) new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics();
	public static final int DEFAULT_FONT_SIZE = 18;
	public static final int DEFAULT_ALIGN = 1;  //center
	public Font font;
	public Color fontColor;
	public int fontSize;
	public int align;
	
	@Override
	public String getId() {
		return ID;
	}
	
	public FontAttributes() {
		this.fontSize = DEFAULT_FONT_SIZE;
		this.font = new Font("Helvetica", Font.PLAIN, this.fontSize);
		this.fontColor = Color.BLACK;
		this.align = DEFAULT_ALIGN;
	}
	
	public FontAttributes(Font font, Color fontColor) {
		this.font = font;
		this.fontColor = fontColor;
		this.fontSize = font.getSize();
		this.align = DEFAULT_ALIGN;
	}

	public FontAttributes(Font font, Color fontColor, int size, int align) {
		this.font = font;
		this.fontColor = fontColor;
		this.fontSize = size;
		this.align = align;
	}

	public void setSize(int size) {
		this.font = new Font(this.font.getName(), this.font.getStyle(), size);
	}

	public void setAlign(int align) {
		// 0 = align left, 1 = align center, 2 = align right
		this.align = align;
	}
	
	public Rectangle getBounds(String s) {
		FontRenderContext frc = DEFAULT_GRAPHICS.getFontRenderContext();
		return font.getStringBounds(s, frc).getBounds();
	}

}
