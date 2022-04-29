package graphics.shapes.ui;

import java.awt.event.MouseEvent;

import graphics.shapes.SCollection;
import graphics.shapes.SSketch;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

public class ShapesToolController extends Controller {
	
	private Shape shapeToAdd;
	private SSketch sketch;
	
	private boolean sketchForbidden;
	
	public ShapesToolController(Object newModel) {
		super(newModel);
		this.sketchForbidden = true;
	}
	
	public void setShape(Shape s) {
		this.shapeToAdd = s;
	}
	
	public void allowSketch() {
		this.sketchForbidden = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.shapeToAdd == null) return;
		
		this.shapeToAdd.setLoc(e.getPoint());
		SCollection model = (SCollection) this.getModel();
		model.add(this.shapeToAdd);
		this.getView().repaint();
		this.shapeToAdd = this.shapeToAdd.copy();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(!this.sketchForbidden) {
			this.sketch.addPoint(e.getPoint());
			this.getView().repaint();
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(!this.sketchForbidden) {
			this.sketch = null;
			this.getView().repaint();
			this.sketchForbidden = true;
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(!this.sketchForbidden) {
			SSketch sketch = new SSketch(e.getPoint());
			sketch.addAttributes(new SelectionAttributes());
			this.sketch = sketch;
			((SCollection) this.getModel()).add(this.sketch);
		}
	}

}
