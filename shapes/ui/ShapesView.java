package graphics.shapes.ui;

import java.awt.Graphics;

import graphics.shapes.SCollection;
import graphics.ui.Controller;
import graphics.ui.View;

public class ShapesView extends View {

	private ShapeDraftman draftman;
	
	public ShapesView(Object model) {
		super(model);
		this.draftman = new ShapeDraftman();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.draftman.setGraphics(g);
		SCollection model = (SCollection) this.getModel();
		if(model == null) return;
		model.accept(draftman);
	}
	
	@Override
	public Controller defaultController(Object model) {
		return new ShapesController(model);
	}
	
	@Override
	public boolean isFocusTraversable() {
		return true;
	}
}
