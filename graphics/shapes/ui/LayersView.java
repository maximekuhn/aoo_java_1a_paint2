package graphics.shapes.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import graphics.shapes.SCollection;
import graphics.ui.View;

public class LayersView extends View {

	private ShapesView sview;
	
	private Color bgColor;
	private Color borderColor;
	
	public LayersView(Object model) {
		super(model);
		this.setColor(Color.WHITE, new Color(245,245,245));
	}
	
	public void setShapesView(ShapesView sview) {
		this.sview = sview;
	}
	
	public void setColor(Color bgColor, Color borderColor) {
		this.bgColor = bgColor;
		this.borderColor = borderColor;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO : fix scaling (stop using literals)
		
		super.paintComponent(g);
		
		SCollection model = (SCollection) this.sview.getModel();
		int layerCount = model.getLayersCount();
		ShapesView sv;
		BufferedImage bi;
		Graphics2D g2D = (Graphics2D) g;
		for(int i = 0; i < layerCount; i++) {
			sv = new ShapesView(model.getShapesAtLayer(i));
			sv.setSize(new Dimension(this.sview.getSize()));
			sv.setBackground(this.bgColor);
			bi = new BufferedImage(sv.getWidth(), sv.getHeight(), BufferedImage.TYPE_INT_RGB);
			sv.paintComponent(bi.createGraphics());
			
			g2D.setColor(new Color(80,80,80));
			g2D.setStroke(new BasicStroke(2));
			g2D.drawImage(new ImageIcon(bi).getImage().getScaledInstance(140, 80, Image.SCALE_SMOOTH), 5, i * 90 + 5, null);
			g2D.drawRect(5, i * 90 + 5, 140, 80);
			g2D.setColor(borderColor);
			g2D.setStroke(new BasicStroke(2));
			g2D.drawRect(0, i*90, this.getWidth(), 90);
			g2D.setColor(new Color(0,0,0));
			g2D.drawString("Layer " + Integer.toString(i), this.getWidth() - 70, i * 90 + 50);
		}
	}
	
	@Override
	public boolean isFocusTraversable() {
		return false;
	}

}