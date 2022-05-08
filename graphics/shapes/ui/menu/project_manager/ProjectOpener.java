package graphics.shapes.ui.menu.project_manager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SImage;
import graphics.shapes.SKotlin;
import graphics.shapes.SRectangle;
import graphics.shapes.SSketch;
import graphics.shapes.SText;
import graphics.shapes.STextBox;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesView;

public class ProjectOpener {
	
	// TODO : better errors handling
	// TODO : empty collection before adding shapes
	// TODO : use ShapeVisitor interface ???

	private static final String SRECTANGLE = new SRectangle(new Point(0,0), 0, 0).getClass().getName();
	private static final String SCIRCLE = new SCircle(new Point(0,0), 0).getClass().getName();
	private static final String SCOLLECTION = new SCollection().getClass().getName();
	private static final String SIMAGE = new SImage().getClass().getName();
	private static final String SKOTLIN = new SKotlin(new Point(0,0), 0, 0).getClass().getName();
	private static final String SSKETCH = new SSketch(new Point(0,0)).getClass().getName();
	private static final String STEXT = new SText(new Point(0,0), "text").getClass().getName();
	private static final String STEXTBOX = new STextBox(new Point(0,0), "text").getClass().getName();
	
	private ShapesView sview;
	private SCollection model;
	
	public ProjectOpener(ShapesView sview) {
		this.sview = sview;
		this.model = (SCollection) this.sview.getModel();
	}

	public void open(File fileToOpen) {
		// TODO Auto-generated method stub
		try {
			List<String> lines = Files.readAllLines(fileToOpen.toPath(), StandardCharsets.UTF_8);
			
			// TODO : throw error (or pop-up) ?
			if(lines.size() == 0) return;
			
			// line 0 => view's dimension
			//this.decryptDimension(lines.get(0));
			
			// other lines = all shapes
			if(lines.size() < 2) return;
			for(int i = 1; i < lines.size(); i++) {
				String line = lines.get(i);
				String className = line.split(" ")[1];
				
				if(className.equals(SRECTANGLE)) this.decryptSRectangle(line);
				else if(className.equals(SCIRCLE)) this.decryptSCircle(line);
				else if(className.equals(STEXTBOX)) this.decryptSTextBox(line);
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private Color decryptColor(String line) {
		// TODO : decryptColor
		return new Color(Integer.parseInt(line));
	}
	
	private Font decryptFont(String line) {
		// TODO : decryptFont
		return new FontAttributes().font;
	}

	private void decryptDimension(String line) {
		// TODO : fix this
		try {
			int width = Integer.parseInt(line.split(" ")[0]);
			int height = Integer.parseInt(line.split(" ")[1]);
			this.sview.setSize(new Dimension(width, height));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void decryptSRectangle(String line) {
		String[] settings = line.split(" ");
		if(settings.length != 14) return;
		
		// point + width & height
		int locX = Integer.parseInt(settings[2]);
		int locY = Integer.parseInt(settings[3]);
		int width = Integer.parseInt(settings[4]);
		int height = Integer.parseInt(settings[5]);
		
		SRectangle sr = new SRectangle(new Point(locX, locY), width, height);
		
		// color attributes
		boolean filled = Boolean.parseBoolean(settings[6]);
		boolean stroked = Boolean.parseBoolean(settings[7]);
		Color filledColor = this.decryptColor(settings[8]);
		Color strokedColor = this.decryptColor(settings[9]);
		ColorAttributes ca = new ColorAttributes(filled, stroked, filledColor, strokedColor);
		sr.addAttributes(ca);
		
		// selection attributes
		boolean isSelected = Boolean.parseBoolean(settings[10]);
		SelectionAttributes sa = new SelectionAttributes(isSelected);
		sr.addAttributes(sa);
		
		// rotation attributes
		int angle = Integer.parseInt(settings[11]);
		RotationAttributes ra = new RotationAttributes(angle);
		sr.addAttributes(ra);
		
		// layer attributes
		int layer = Integer.parseInt(settings[12]);
		LayerAttributes la = new LayerAttributes(layer);
		sr.addAttributes(la);
		
		this.model.add(sr);
		this.sview.repaint();
	}
	
	private void decryptSCircle(String line) {
		String[] settings = line.split(" ");
		if(settings.length != 13) return;
		
		// point + radius
		int locX = Integer.parseInt(settings[2]);
		int locY = Integer.parseInt(settings[3]);
		int radius = Integer.parseInt(settings[4]);
		
		SCircle sc = new SCircle(new Point(locX, locY), radius);
		
		// color attributes
		boolean filled = Boolean.parseBoolean(settings[5]);
		boolean stroked = Boolean.parseBoolean(settings[6]);
		Color filledColor = this.decryptColor(settings[7]);
		Color strokedColor = this.decryptColor(settings[8]);
		ColorAttributes ca = new ColorAttributes(filled, stroked, filledColor, strokedColor);
		sc.addAttributes(ca);
		
		// selection attributes
		boolean isSelected = Boolean.parseBoolean(settings[9]);
		SelectionAttributes sa = new SelectionAttributes(isSelected);
		sc.addAttributes(sa);
		
		// rotation attributes
		int angle = Integer.parseInt(settings[10]);
		RotationAttributes ra = new RotationAttributes(angle);
		sc.addAttributes(ra);
		
		//layer attributes
		int layer = Integer.parseInt(settings[11]);
		LayerAttributes la = new LayerAttributes(layer);
		sc.addAttributes(la);
		
		this.model.add(sc);
		this.sview.repaint();
	}
	
	private void decryptSTextBox(String line) {
		String[] settings = line.split(" ");
		if(settings.length != 15) return;
		
		// point + text
		int locX = Integer.parseInt(settings[2]);
		int locY = Integer.parseInt(settings[3]);
		String text = settings[4];
		
		STextBox stb = new STextBox(new Point(locX, locY), text);
		
		// color attributes
		boolean filled = Boolean.parseBoolean(settings[5]);
		boolean stroked = Boolean.parseBoolean(settings[6]);
		Color filledColor = this.decryptColor(settings[7]);
		Color strokedColor = this.decryptColor(settings[8]);
		ColorAttributes ca = new ColorAttributes(filled, stroked, filledColor, strokedColor);
		stb.addAttributes(ca);
		
		// selection attributes
		boolean isSelected = Boolean.parseBoolean(settings[9]);
		SelectionAttributes sa = new SelectionAttributes(isSelected);
		stb.addAttributes(sa);
		
		// rotation attributes
		int angle = Integer.parseInt(settings[10]);
		RotationAttributes ra = new RotationAttributes(angle);
		stb.addAttributes(ra);
		
		// layer attributes
		int layer = Integer.parseInt(settings[11]);
		LayerAttributes la = new LayerAttributes(layer);
		stb.addAttributes(la);
		
		// font attributes
		Font font = this.decryptFont(settings[12]);
		Color fontColor = this.decryptColor(settings[13]);
		FontAttributes fa = new FontAttributes(font, fontColor);
		stb.addAttributes(fa);
		
		this.model.add(stb);
		this.sview.repaint();
	}
}
