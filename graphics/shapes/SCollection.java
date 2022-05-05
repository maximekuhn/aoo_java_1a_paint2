package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SCollection extends Shape {

	private LinkedList<Shape> shapes;
	
	public SCollection() {
		super();
		this.shapes = new LinkedList<Shape>();
	}
	
	@Override
	public Point getLoc() {
		Point loc = new Point(this.shapes.get(0).getLoc());
		for(Shape s : this.shapes) {
			if(s.getLoc().x < loc.x && s.getLoc().y < loc.y) {
				loc.setLocation(s.getLoc());
			}
		}
		return loc;
	}

	@Override
	public void setLoc(Point p) {
		int dx = p.x - this.getLoc().x;
		int dy = p.y - this.getLoc().y;
		for(Shape s : this.shapes)
			s.translate(dx, dy);
	}

	@Override
	public void translate(int dx, int dy) {
		for(Shape s : this.shapes)
			s.translate(dx, dy);
	}

	@Override
	public Rectangle getBounds() {
		Rectangle bounds = new Rectangle(this.shapes.get(0).getBounds());
		for(Shape s : this.shapes)
			bounds.add(s.getBounds());
		return bounds;
	}

	@Override
	public void accept(ShapeVisitor v) {
		v.visitCollection(this);
	}
	
	public void add(Shape s) {
		this.shapes.add(s);
	}
	
	public void remove(Shape s) {
		this.shapes.remove(s);
	}
	
	public Iterator<Shape> iterator() {
		return this.shapes.iterator();
	}

	@Override
	public Shape copy() {
		SCollection sc = new SCollection();
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null) ca = new ColorAttributes();
		sc.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
		SelectionAttributes sa = (SelectionAttributes) this.getAttributes(SelectionAttributes.ID);
		if(sa == null) sa = new SelectionAttributes();
		sc.addAttributes(new SelectionAttributes(sa.isSelected()));
		RotationAttributes ra = (RotationAttributes) this.getAttributes(RotationAttributes.ID);
		if(ra == null) ra = new RotationAttributes();
		sc.addAttributes(new RotationAttributes(ra.getAngle()));
		LayerAttributes la = (LayerAttributes) this.getAttributes(LayerAttributes.ID);
		if(la == null) la = new LayerAttributes();
		sc.addAttributes(new LayerAttributes(la.getLayer()));
		
		Shape sCopy;
		for(Shape shape : this.shapes) {
			sCopy = shape.copy();
			
			/*
			 * all shapes in an SCollection are not selected / unselected,
			 * the SCollection itself is selected / unselected.
			 */
			SelectionAttributes saSCopy = (SelectionAttributes) sCopy.getAttributes(SelectionAttributes.ID);
			if(saSCopy == null) saSCopy = new SelectionAttributes();
			saSCopy.unselect();
			sc.add(sCopy);
		}
		return sc;
	}

	@Override
	public void resize(int dx, int dy) {
		for(Shape s : this.shapes) {
			s.resize((int)((float)s.getBounds().width/(float)this.getBounds().width)*dx, (int)((float)s.getBounds().height/(float)this.getBounds().height)*dy);
			/* System.out.println(s.getBounds().width/this.getBounds().width);
			System.out.println(dx);
			System.out.println("--"); */
			s.translate(dx, dy);
			// s.translate(((s.getLoc().x-this.getLoc().x)/this.getBounds().width)*dx, ((s.getLoc().y-this.getLoc().y)/this.getBounds().height)*dy);
		}
		

	}
	
	public void sortByLayers() {
		this.shapes.sort(new Comparator<Shape>() {

			@Override
			public int compare(Shape s1, Shape s2) {
				LayerAttributes la1 = (LayerAttributes) s1.getAttributes(LayerAttributes.ID);
				if(la1 == null) la1 = new LayerAttributes();
				
				LayerAttributes la2 = (LayerAttributes) s2.getAttributes(LayerAttributes.ID);
				if(la2 == null) la2 = new LayerAttributes();
				
				return la1.getLayer() - la2.getLayer();
			}
			
		});
	}
	
	public int getLayersCount() {
		int layerMax = this.getLayerMax();
		int layerMin = this.getLayerMin();
		
		/*
		 * layerMax
		 * + 1 for layer 0
		 * -1 * layerMin > 0
		 */
		
		return layerMax + 1 - 1 * layerMin;
	}
	
	private int getLayerMax() {
		int layerMax = 0;
		for(Shape s : this.shapes) {
			LayerAttributes la = (LayerAttributes) s.getAttributes(LayerAttributes.ID);
			if(la == null) la = new LayerAttributes();
			if(la.getLayer() > layerMax) layerMax = la.getLayer();
		}
		return layerMax;
	}
	
	private int getLayerMin() {
		int layerMin = 0;
		for(Shape s : this.shapes) {
			LayerAttributes la = (LayerAttributes) s.getAttributes(LayerAttributes.ID);
			if(la == null) la = new LayerAttributes();
			if(la.getLayer() < layerMin) layerMin = la.getLayer();
		}
		return layerMin;
	}
	
	public SCollection getShapesAtLayer(int layer) {
		SCollection shapesAtLayer = new SCollection();
		for(Shape s : this.shapes) {
			LayerAttributes la = (LayerAttributes) s.getAttributes(LayerAttributes.ID);
			if(la == null) la = new LayerAttributes();
			if(la.getLayer() == layer) shapesAtLayer.add(s.copy());
		}
		return shapesAtLayer;
	}

}
