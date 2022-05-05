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
import graphics.shapes.attributes.FontAttributes;
import graphics.ui.View;

public class LayersView extends View {

	private ShapesView sview;
	
	public LayersView(Object model) {
		super(model);
	}
	
	public void setShapesView(ShapesView sview) {
		this.sview = sview;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO : fix scaling
		
		super.paintComponent(g);
		
		SCollection model = (SCollection) this.sview.getModel();
		int layerCount = model.getLayersCount();
		ShapesView sv;
		BufferedImage bi;
		Graphics2D g2D = (Graphics2D) g;
		for(int i = 0; i < layerCount; i++) {
			sv = new ShapesView(model.getShapesAtLayer(i));
			sv.setSize(new Dimension(this.sview.getSize()));
			sv.setBackground(Color.WHITE);
			bi = new BufferedImage(sv.getWidth(), sv.getHeight(), BufferedImage.TYPE_INT_RGB);
			sv.paintComponent(bi.createGraphics());
			
			g2D.setColor(Color.ORANGE);
			g2D.setStroke(new BasicStroke(3));
			g2D.drawImage(new ImageIcon(bi).getImage().getScaledInstance(this.getWidth(), this.getHeight() / layerCount, Image.SCALE_SMOOTH), 0, i * 100, null);
			g2D.drawRect(0, i*100, this.getWidth(), this.getHeight() / layerCount);
		}
		
		
		g2D.setColor(new Color(123, 24, 223));
		g2D.setStroke(new BasicStroke(5));
		g2D.drawRect(0, 0, this.getWidth(), this.getHeight());
		
	}
	
	@Override
	public boolean isFocusTraversable() {
		return false;
	}

}
