package graphics.shapes.ui;

import java.awt.event.MouseEvent;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.ui.Controller;

public class ShapesToolController extends Controller {

	private Shape shapeToAdd;
	
	public ShapesToolController(Object newModel) {
		super(newModel);
	}
	
	public void setShape(Shape s) {
		this.shapeToAdd = s;
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

}
