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
	public Font font;
	public Color fontColor;
	
	@Override
	public String getId() {
		return ID;
	}
	
	public FontAttributes() {
		this.font = new Font("Helvetica", Font.PLAIN, 18);
		this.fontColor = Color.BLACK;
	}
	
	public FontAttributes(Font font, Color fontColor) {
		this.font = font;
		this.fontColor = fontColor;
	}
	
	public Rectangle getBounds(String s) {
		FontRenderContext frc = DEFAULT_GRAPHICS.getFontRenderContext();
		return font.getStringBounds(s, frc).getBounds();
	}

}
