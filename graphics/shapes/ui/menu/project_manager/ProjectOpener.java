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
import javax.swing.JOptionPane;

import graphics.shapes.SArrow;
import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SCross;
import graphics.shapes.SEllipse;
import graphics.shapes.SHexagon;
import graphics.shapes.SImage;
import graphics.shapes.SKotlin;
import graphics.shapes.SLine;
import graphics.shapes.SOctagon;
import graphics.shapes.SParallelogram;
import graphics.shapes.SPentagon;
import graphics.shapes.SRectangle;
import graphics.shapes.SRhombus;
import graphics.shapes.SSketch;
import graphics.shapes.SStar;
import graphics.shapes.SText;
import graphics.shapes.STextBox;
import graphics.shapes.STrapezium;
import graphics.shapes.STriangle;
import graphics.shapes.STriangleRec;
import graphics.shapes.STriangleScale;
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
	private static final String SHEXAGON = new SHexagon(new Point(0,0), 0, 0).getClass().getName();
	private static final String STRIANGLEREC = new STriangleRec(new Point(0,0), 0, 0).getClass().getName();
	private static final String SLINE = new SLine(new Point(0,0), 0, 0).getClass().getName();
	private static final String SARROW = new SArrow(new Point(0,0), 0, 0).getClass().getName();
	private static final String SCROSS = new SCross(new Point(0,0), 0, 0).getClass().getName();
	private static final String SPENTAGON = new SPentagon(new Point(0,0), 0, 0).getClass().getName();
	private static final String SELLIPSE = new SEllipse(new Point(0,0), 0, 0).getClass().getName();
	private static final String SSTAR = new SStar(new Point(0,0), 0, 0).getClass().getName();
	private static final String SPARALLELOGRAM = new SParallelogram(new Point(0,0), 0, 0).getClass().getName();
	private static final String SOCTAGON = new SOctagon(new Point(0,0), 0, 0).getClass().getName();
	private static final String STRAPEZIUM = new STrapezium(new Point(0,0), 0, 0).getClass().getName();
	private static final String STRIANGLESCALE = new STriangleScale(new Point(0,0), 0, 0).getClass().getName();
	private static final String SRHOMBUS = new SRhombus(new Point(0,0), 0, 0).getClass().getName();
	
	private ShapesView sview;
	
	public ProjectOpener(ShapesView sview) {
		this.sview = sview;
	}

	public void open(File fileToOpen) {
		try {
			this.emptyModel();
			SCollection model = (SCollection) this.sview.getModel();
			List<String> lines = Files.readAllLines(fileToOpen.toPath(), StandardCharsets.UTF_8);
			
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
			JOptionPane.showMessageDialog(null, "Unable to open specified file.", "IO Error", JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error : project file might be corrupted.", "Error", JOptionPane.ERROR_MESSAGE);
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
		else if(className.equals(SHEXAGON)) shape = this.decodeSHexagon(line);
		else if(className.equals(STRIANGLEREC)) shape = this.decodeSTriangleRec(line);
		else if(className.equals(SLINE)) shape = this.decodeSLine(line);
		else if(className.equals(SARROW)) shape = this.decodeSArrow(line);
		else if(className.equals(SCROSS)) shape = this.decodeSCross(line);
		else if(className.equals(SPENTAGON)) shape = this.decodeSPentagon(line);
		else if(className.equals(SELLIPSE)) shape = this.decodeSEllipse(line);
		else if(className.equals(SSTAR)) shape = this.decodeSStar(line);
		else if(className.equals(SPARALLELOGRAM)) shape = this.decodeSParallelogram(line);
		else if(className.equals(SOCTAGON)) shape = this.decodeSOctagon(line);
		else if(className.equals(STRAPEZIUM)) shape = this.decodeSTrapezium(line);
		else if(className.equals(STRIANGLESCALE)) shape = this.decodeSTriangleScale(line);
		else if(className.equals(SRHOMBUS)) shape = this.decodeSRhombus(line);
		
		return shape;
	}

	private Color decodeColor(String line) {
		return new Color(Integer.parseInt(line));
	}
	
	private Font decodeFont(String line) {
		String[] fontSettings = line.split(",");
		String name = fontSettings[0].replace("\\w", " ");
		int style = Integer.parseInt(fontSettings[1]);
		int size = Integer.parseInt(fontSettings[2]);
		
		return new Font(name, style, size);
	}

	private void decodeDimension(String line) {
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
	
	
	
	
	/* ---        --- */
	/* --- SHAPES --- */
	/* ---        --- */
	
	private Shape decodeSRectangle(String line) {
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
		Color filledColor = this.decodeColor(settings[8]);
		Color strokedColor = this.decodeColor(settings[9]);
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
	
	private Shape decodeSEllipse(String line) {
		String[] settings = line.split(" ");
		if(settings.length != 14) return null;
		
		// point + width & height
		int locX = Integer.parseInt(settings[2]);
		int locY = Integer.parseInt(settings[3]);
		int width = Integer.parseInt(settings[4]);
		int height = Integer.parseInt(settings[5]);
		
		SEllipse se = new SEllipse(new Point(locX, locY), width, height);
		
		// color attributes
		boolean filled = Boolean.parseBoolean(settings[6]);
		boolean stroked = Boolean.parseBoolean(settings[7]);
		Color filledColor = this.decodeColor(settings[8]);
		Color strokedColor = this.decodeColor(settings[9]);
		ColorAttributes ca = new ColorAttributes(filled, stroked, filledColor, strokedColor);
		se.addAttributes(ca);
		
		// selection attributes
		boolean isSelected = Boolean.parseBoolean(settings[10]);
		SelectionAttributes sa = new SelectionAttributes(isSelected);
		se.addAttributes(sa);
		
		// rotation attributes
		int angle = Integer.parseInt(settings[11]);
		RotationAttributes ra = new RotationAttributes(angle);
		se.addAttributes(ra);
		
		// layer attributes
		int layer = Integer.parseInt(settings[12]);
		LayerAttributes la = new LayerAttributes(layer);
		se.addAttributes(la);
		
		return se;
	}
	
	private Shape decodeSCircle(String line) {
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
		Color filledColor = this.decodeColor(settings[7]);
		Color strokedColor = this.decodeColor(settings[8]);
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
	
	
	private Shape decodeSTextBox(String line) {
		String[] settings = line.split(" ");
		if(settings.length != 17) return null;
		
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
		Color filledColor = this.decodeColor(settings[7]);
		Color strokedColor = this.decodeColor(settings[8]);
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
		Font font = this.decodeFont(settings[12]);
		Color fontColor = this.decodeColor(settings[13]);
		int fontAlignX = Integer.parseInt(settings[14]);
		int fontAlignY = Integer.parseInt(settings[15]);
		FontAttributes fa = new FontAttributes(font, fontColor);
		fa.setAlignX(fontAlignX);
		fa.setAlignY(fontAlignY);
		stb.addAttributes(fa);
		
		return stb;
	}
	
	private Shape decodetSText(String line) {
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
		Color filledColor = this.decodeColor(settings[7]);
		Color strokedColor = this.decodeColor(settings[8]);
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
		Font font = this.decodeFont(settings[12]);
		Color fontColor = this.decodeColor(settings[13]);
		FontAttributes fa = new FontAttributes(font, fontColor);
		st.addAttributes(fa);
		
		return st;
	}
	
	
	private Shape decodeSCollection(String line) {
		String[] settings = line.split(" ");
		// location
		int locX = Integer.parseInt(settings[2]);
		int locY = Integer.parseInt(settings[3]);
		
		SCollection sc = new SCollection();
		
		// color attributes
		boolean filled = Boolean.parseBoolean(settings[4]);
		boolean stroked = Boolean.parseBoolean(settings[5]);
		Color filledColor = this.decodeColor(settings[6]);
		Color strokedColor = this.decodeColor(settings[7]);
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

	private Shape decodeSSketch(String line) {
		String[] settings = line.split(" ");
		
		// point
		int locX = Integer.parseInt(settings[2]);
		int locY = Integer.parseInt(settings[3]);
		
		SSketch sk = new SSketch(new Point(locX, locY));
		
		// color attributes
		boolean filled = Boolean.parseBoolean(settings[4]);
		boolean stroked = Boolean.parseBoolean(settings[5]);
		Color filledColor = this.decodeColor(settings[6]);
		Color strokedColor = this.decodeColor(settings[7]);
		ColorAttributes ca = new ColorAttributes(filled, stroked, filledColor, strokedColor);
		sk.addAttributes(ca);
		
		// selection attributes
		boolean isSelected = Boolean.parseBoolean(settings[8]);
		SelectionAttributes sa = new SelectionAttributes(isSelected);
		sk.addAttributes(sa);
		
		// rotation attributes
		int angle = Integer.parseInt(settings[9]);
		RotationAttributes ra = new RotationAttributes(angle);
		sk.addAttributes(ra);
		
		// layer attributes
		int layer = Integer.parseInt(settings[10]);
		LayerAttributes la = new LayerAttributes(layer);
		sk.addAttributes(la);
		
		// add all points
		if(settings.length < 10 +1) return null; // no points
		int pointX;
		int pointY;
		for(int i = 11; i < settings.length - 2; i += 2) {
			pointX = Integer.parseInt(settings[i]);
			pointY = Integer.parseInt(settings[i+1]);
			sk.addPoint(new Point(pointX, pointY));
		}
		
		return sk;
	}

	private Shape decodeSImage(String line) {
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
			Color filledColor = this.decodeColor(settings[7]);
			Color strokedColor = this.decodeColor(settings[8]);
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
	
	
	
	
	/* ---          --- */
	/* --- POLYGONS --- */
	/* ---          --- */
	
	private Shape decodePolygon(String line) {
		String[] settings = line.split(" ");
		if(settings.length != 14) return null;
		
		String className = settings[1];
		
		// point + width & height
		int locX = Integer.parseInt(settings[2]);
		int locY = Integer.parseInt(settings[3]);
		int width = Integer.parseInt(settings[4]);
		int height = Integer.parseInt(settings[5]);
		
		Shape shape;
		if(className.equals(SKOTLIN)) shape = new SKotlin(new Point(locX, locY), width, height);
		else if(className.equals(STRIANGLE)) shape = new STriangle(new Point(locX, locY), width, height);
		else if(className.equals(SLINE)) shape = new SLine(new Point(locX, locY), width, height);
		else if(className.equals(SHEXAGON)) shape = new SHexagon(new Point(locX, locY), width, height);
		else if(className.equals(STRIANGLEREC)) shape = new STriangleRec(new Point(locX, locY), width, height);
		else if(className.equals(SCROSS)) shape = new SCross(new Point(locX, locY), width, height);
		else if(className.equals(SELLIPSE)) shape = new SEllipse(new Point(locX, locY), width, height);
		else if(className.equals(SPENTAGON)) shape = new SPentagon(new Point(locX, locY), width, height);
		else if(className.equals(SARROW)) shape = new SArrow(new Point(locX, locY), width, height);
		else if(className.equals(SSTAR)) shape = new SStar(new Point(locX, locY), width, height);
		else if(className.equals(SPARALLELOGRAM)) shape = new SParallelogram(new Point(locX, locY), width, height);
		else if(className.equals(SOCTAGON)) shape = new SOctagon(new Point(locX, locY), width, height);
		else if(className.equals(STRAPEZIUM)) shape = new STrapezium(new Point(locX, locY), width, height);
		else if(className.equals(STRIANGLESCALE)) shape = new STriangleScale(new Point(locX, locY), width, height);
		else if(className.equals(SRHOMBUS)) shape = new SRhombus(new Point(locX, locY), width, height);
		else shape = null;
		
		if(shape == null) return null;
		
		// color attributes
		boolean filled = Boolean.parseBoolean(settings[6]);
		boolean stroked = Boolean.parseBoolean(settings[7]);
		Color filledColor = this.decodeColor(settings[8]);
		Color strokedColor = this.decodeColor(settings[9]);
		ColorAttributes ca = new ColorAttributes(filled, stroked, filledColor, strokedColor);
		shape.addAttributes(ca);
		
		// selection attributes
		boolean isSelected = Boolean.parseBoolean(settings[10]);
		SelectionAttributes sa = new SelectionAttributes(isSelected);
		shape.addAttributes(sa);
		
		// rotation attributes
		int angle = Integer.parseInt(settings[11]);
		RotationAttributes ra = new RotationAttributes(angle);
		shape.addAttributes(ra);
		
		// layer attributes
		int layer = Integer.parseInt(settings[12]);
		LayerAttributes la = new LayerAttributes(layer);
		shape.addAttributes(la);
		
		return shape;
	}
	
	private Shape decodeSRhombus(String line) {
		return this.decodePolygon(line);
	}

	private Shape decodeSTriangleScale(String line) {
		return this.decodePolygon(line);
	}

	private Shape decodeSTrapezium(String line) {
		return this.decodePolygon(line);
	}

	private Shape decodeSOctagon(String line) {
		return this.decodePolygon(line);
	}

	private Shape decodeSParallelogram(String line) {
		return this.decodePolygon(line);
	}

	private Shape decodeSStar(String line) {
		return this.decodePolygon(line);
	}

	private Shape decodeSPentagon(String line) {
		return this.decodePolygon(line);
	}

	private Shape decodeSCross(String line) {
		return this.decodePolygon(line);
	}

	private Shape decodeSArrow(String line) {
		return this.decodePolygon(line);
	}

	private Shape decodeSLine(String line) {
		return this.decodePolygon(line);
	}

	private Shape decodeSTriangleRec(String line) {
		return this.decodePolygon(line);
	}

	private Shape decodeSHexagon(String line) {
		return this.decodePolygon(line);
	}

	private Shape decodeSTriangle(String line) {
		return this.decodePolygon(line);
	}
	
	private Shape decodeSKotlin(String line) {
		return this.decodePolygon(line);
	}
}
