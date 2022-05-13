package graphics.shapes.ui;

import java.awt.Graphics;

import graphics.shapes.SCollection;
import graphics.ui.Controller;
import graphics.ui.View;

public class ShapesView extends View {

	private ShapeDraftman draftman;
	private LayersView layersView;
	
	public ShapesView(Object model, LayersView lview) {
		super(model);
		
		this.layersView = lview;
		if(this.layersView != null) this.layersView.setShapesView(this);
		
		this.draftman = new ShapeDraftman();
	}
	
	public ShapesView(Object model) {
		this(model, null);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.draftman.setGraphics(g);
		SCollection model = (SCollection) this.getModel();
		if(model == null) return;
		model.sortByLayers();
		model.accept(draftman);
		
		if(this.layersView != null) this.layersView.repaint();
	}
	
	@Override
	public Controller defaultController(Object model) {
		return new MainController(model);
	}
	
	@Override
	public boolean isFocusTraversable() {
		return true;
	}
	
	public LayersView getLayersView() {
		return this.layersView;
	}

}
