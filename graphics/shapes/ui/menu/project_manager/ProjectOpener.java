package graphics.shapes.ui.menu.project_manager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SImage;
import graphics.shapes.SKotlin;
import graphics.shapes.SRectangle;
import graphics.shapes.SSketch;
import graphics.shapes.SText;
import graphics.shapes.STextBox;
import graphics.shapes.STriangle;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesView;

public class ProjectOpener {
	
	// TODO : better errors handling

	private static final String SRECTANGLE = new SRectangle(new Point(0,0), 0, 0).getClass().getName();
	private static final String SCIRCLE = new SCircle(new Point(0,0), 0).getClass().getName();
	private static final String SCOLLECTION = new SCollection().getClass().getName();
	private static final String SIMAGE = new SImage().getClass().getName();
	private static final String SKOTLIN = new SKotlin(new Point(0,0), 0, 0).getClass().getName();
	private static final String SSKETCH = new SSketch(new Point(0,0)).getClass().getName();
	private static final String STEXT = new SText(new Point(0,0), "text").getClass().getName();
	private static final String STEXTBOX = new STextBox(new Point(0,0), "text").getClass().getName();
	private static final String STRIANGLE = new STriangle(new Point(0,0), 0, 0).getClass().getName();
	
	private ShapesView sview;
	private SCollection model;
	
	public ProjectOpener(ShapesView sview) {
		this.sview = sview;
		this.model = (SCollection) this.sview.getModel();
	}

	public void open(File fileToOpen) {
		// TODO Auto-generated method stub
		try {
			this.emptyModel();
			SCollection model = (SCollection) this.sview.getModel();
			List<String> lines = Files.readAllLines(fileToOpen.toPath(), StandardCharsets.UTF_8);
			
			// TODO : throw error (or pop-up) ?
			if(lines.size() == 0) return;
			
			// line 0 => view's dimension
			//this.decryptDimension(lines.get(0));
			
			// other lines = all shapes
			if(lines.size() < 2) return;
			
			Shape shape;
			for(int i = 1; i < lines.size(); i++) {
				String line = lines.get(i);
				shape = this.decode(line);
				if(shape != null) model.add(shape);
				this.sview.repaint();
			}
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void emptyModel() {
		SCollection model = (SCollection) this.sview.getModel();
		Iterator<Shape> iterator = model.iterator();
		
		LinkedList<Shape> shapesToRemove = new LinkedList<>();
		Shape shape;
		while(iterator.hasNext()) {
			shape = iterator.next();
			shapesToRemove.add(shape);
		}
		
		for(Shape s : shapesToRemove) model.remove(s);
	}
	
	private Shape decode(String line) {
		String className = line.split(" ")[1];
		Shape shape = null;
		if(className.equals(SRECTANGLE)) shape = this.decodeSRectangle(line);
		else if(className.equals(SCIRCLE)) shape = this.decodeSCircle(line);
		else if(className.equals(STEXTBOX)) shape = this.decodeSTextBox(line);
		else if(className.equals(STEXT)) shape = this.decodetSText(line);
		else if(className.equals(SKOTLIN)) shape = this.decodeSKotlin(line);
		else if(className.equals(SIMAGE)) shape = this.decodeSImage(line);
		else if(className.equals(SSKETCH)) shape = this.decodeSSketch(line);
		else if(className.equals(SCOLLECTION)) shape = this.decodeSCollection(line);
		else if(className.equals(STRIANGLE)) shape = this.decodeSTriangle(line);
		
		return shape;
	}

	private Shape decodeSTriangle(String line) {
		String[] settings = line.split(" ");
		if(settings.length != 14) return null;
		
		// point + width & height
		int locX = Integer.parseInt(settings[2]);
		int locY = Integer.parseInt(settings[3]);
		int width = Integer.parseInt(settings[4]);
		int height = Integer.parseInt(settings[5]);
		
		STriangle st = new STriangle(new Point(locX, locY), width, height);
		
		// color attributes
		boolean filled = Boolean.parseBoolean(settings[6]);
		boolean stroked = Boolean.parseBoolean(settings[7]);
		Color filledColor = this.decryptColor(settings[8]);
		Color strokedColor = this.decryptColor(settings[9]);
		ColorAttributes ca = new ColorAttributes(filled, stroked, filledColor, strokedColor);
		st.addAttributes(ca);
		
		// selection attributes
		boolean isSelected = Boolean.parseBoolean(settings[10]);
		SelectionAttributes sa = new SelectionAttributes(isSelected);
		st.addAttributes(sa);
		
		// rotation attributes
		int angle = Integer.parseInt(settings[11]);
		RotationAttributes ra = new RotationAttributes(angle);
		st.addAttributes(ra);
		
		// layer attributes
		int layer = Integer.parseInt(settings[12]);
		LayerAttributes la = new LayerAttributes(layer);
		st.addAttributes(la);
		
		return st;
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
	
	private SRectangle decodeSRectangle(String line) {
		String[] settings = line.split(" ");
		if(settings.length != 14) return null;
		
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
		
		return sr;
	}
	
	private SCircle decodeSCircle(String line) {
		String[] settings = line.split(" ");
		if(settings.length != 13) return null;
		
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
		
		return sc;
	}
	
	private STextBox decodeSTextBox(String line) {
		String[] settings = line.split(" ");
		if(settings.length != 15) return null;
		
		// point + text
		int locX = Integer.parseInt(settings[2]);
		int locY = Integer.parseInt(settings[3]);
		String text = settings[4];
		text = text.replace("\\w", " ");
		text = text.replace("\\n", System.lineSeparator());
		
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
		
		return stb;
	}
	
	private SText decodetSText(String line) {
		String[] settings = line.split(" ");
		if(settings.length != 15) return null;
		
		// point + text
		int locX = Integer.parseInt(settings[2]);
		int locY = Integer.parseInt(settings[3]);
		String text = settings[4];
		text = text.replace("\\w", " ");
		
		SText st = new SText(new Point(locX, locY), text);
		
		// color attributes
		boolean filled = Boolean.parseBoolean(settings[5]);
		boolean stroked = Boolean.parseBoolean(settings[6]);
		Color filledColor = this.decryptColor(settings[7]);
		Color strokedColor = this.decryptColor(settings[8]);
		ColorAttributes ca = new ColorAttributes(filled, stroked, filledColor, strokedColor);
		st.addAttributes(ca);
		
		// selection attributes
		boolean isSelected = Boolean.parseBoolean(settings[9]);
		SelectionAttributes sa = new SelectionAttributes(isSelected);
		st.addAttributes(sa);
		
		// rotation attributes
		int angle = Integer.parseInt(settings[10]);
		RotationAttributes ra = new RotationAttributes(angle);
		st.addAttributes(ra);
		
		// layer attributes
		int layer = Integer.parseInt(settings[11]);
		LayerAttributes la = new LayerAttributes(layer);
		st.addAttributes(la);
		
		// font attributes
		Font font = this.decryptFont(settings[12]);
		Color fontColor = this.decryptColor(settings[13]);
		FontAttributes fa = new FontAttributes(font, fontColor);
		st.addAttributes(fa);
		
		return st;
	}
	
	private SKotlin decodeSKotlin(String line) {
		String[] settings = line.split(" ");
		if(settings.length != 14) return null;
		
		// point + width & height
		int locX = Integer.parseInt(settings[2]);
		int locY = Integer.parseInt(settings[3]);
		int width = Integer.parseInt(settings[4]);
		int height = Integer.parseInt(settings[5]);
		
		SKotlin sk = new SKotlin(new Point(locX, locY), width, height);
		
		// color attributes
		boolean filled = Boolean.parseBoolean(settings[6]);
		boolean stroked = Boolean.parseBoolean(settings[7]);
		Color filledColor = this.decryptColor(settings[8]);
		Color strokedColor = this.decryptColor(settings[9]);
		ColorAttributes ca = new ColorAttributes(filled, stroked, filledColor, strokedColor);
		sk.addAttributes(ca);
		
		// selection attributes
		boolean isSelected = Boolean.parseBoolean(settings[10]);
		SelectionAttributes sa = new SelectionAttributes(isSelected);
		sk.addAttributes(sa);
		
		// rotation attributes
		int angle = Integer.parseInt(settings[11]);
		RotationAttributes ra = new RotationAttributes(angle);
		sk.addAttributes(ra);
		
		// layer attributes
		int layer = Integer.parseInt(settings[12]);
		LayerAttributes la = new LayerAttributes(layer);
		sk.addAttributes(la);
		
		return sk;
	}
	
	private SCollection decodeSCollection(String line) {
		// TODO Auto-generated method stub
		String[] settings = line.split(" ");
		// location
		int locX = Integer.parseInt(settings[2]);
		int locY = Integer.parseInt(settings[3]);
		
		SCollection sc = new SCollection();
		
		// color attributes
		boolean filled = Boolean.parseBoolean(settings[4]);
		boolean stroked = Boolean.parseBoolean(settings[5]);
		Color filledColor = this.decryptColor(settings[6]);
		Color strokedColor = this.decryptColor(settings[7]);
		ColorAttributes ca = new ColorAttributes(filled, stroked, filledColor, strokedColor);
		sc.addAttributes(ca);
		
		// selection attributes
		boolean isSelected = Boolean.parseBoolean(settings[8]);
		SelectionAttributes sa = new SelectionAttributes(isSelected);
		sc.addAttributes(sa);
		
		// rotation attributes
		int angle = Integer.parseInt(settings[9]);
		RotationAttributes ra = new RotationAttributes(angle);
		sc.addAttributes(ra);
		
		// layer attributes
		int layer = Integer.parseInt(settings[10]);
		LayerAttributes la = new LayerAttributes(layer);
		sc.addAttributes(la);
		
		// other shapes
		int shapesCount = 0;
		
		List<String> shapes = this.getShapesFromSCollection(line);
		
		if(shapes != null) {
			Shape shape;
			for(String shapeLine : shapes) {
				shape = this.decode(shapeLine);
				if(shape != null) {
					sc.add(shape);
					shapesCount++;
				}
			}
		}
		
		if(shapesCount > 0) {
			sc.setLoc(new Point(locX, locY));
			return sc;
		}
		
		return null;
	}

	private List<String> getShapesFromSCollection(String line) {
		LinkedList<String> shapes = new LinkedList<>();
		
		int beginIndex = 0;
		int endIndex = 0;
		for(int i = 1; i < line.length() - 1; i++) {
			if(line.charAt(i) == '[') beginIndex = i;
			else if(line.charAt(i) == ']') endIndex = i;
			
			if(endIndex > beginIndex) {
				String shapeLine = this.getSubString(line, beginIndex, endIndex);
				shapes.add(shapeLine);
				beginIndex = endIndex;
			}
			
		}
		return shapes;
	}
	
	private String getSubString(String line, int beginIndex, int endIndex) {
		StringBuilder sb = new StringBuilder();
		
		for(int i=beginIndex; i <= endIndex; i++) {
			sb.append(line.charAt(i));
		}
		return sb.toString();
	}

	private SSketch decodeSSketch(String line) {
		// TODO Auto-generated method stub
		return null;
	}

	private SImage decodeSImage(String line) {
		String[] settings = line.split(" ");
		if(settings.length != 13) return null;
		
		// point
		int locX = Integer.parseInt(settings[2]);
		int locY = Integer.parseInt(settings[3]);
		
		// image
		String imagePath = settings[4];
		try {
			Image img = ImageIO.read(new File(imagePath));
			SImage si = new SImage(new Point(locX, locY), img, new File(imagePath));
			
			// color attributes
			boolean filled = Boolean.parseBoolean(settings[5]);
			boolean stroked = Boolean.parseBoolean(settings[6]);
			Color filledColor = this.decryptColor(settings[7]);
			Color strokedColor = this.decryptColor(settings[8]);
			ColorAttributes ca = new ColorAttributes(filled, stroked, filledColor, strokedColor);
			si.addAttributes(ca);
			
			// selection attributes
			boolean isSelected = Boolean.parseBoolean(settings[9]);
			SelectionAttributes sa = new SelectionAttributes(isSelected);
			si.addAttributes(sa);
			
			// rotation attributes
			int angle = Integer.parseInt(settings[10]);
			RotationAttributes ra = new RotationAttributes(angle);
			si.addAttributes(ra);
			
			// layer attributes
			int layer = Integer.parseInt(settings[11]);
			LayerAttributes la = new LayerAttributes(layer);
			si.addAttributes(la);
			
			return si;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
