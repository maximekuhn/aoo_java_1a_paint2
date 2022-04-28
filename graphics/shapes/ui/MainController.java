package graphics.shapes.ui;

import java.util.LinkedList;

import graphics.ui.Controller;


/*
 * Composite Controller
 */
public class MainController extends Controller {

	private LinkedList<Controller> controllers;
	
	public MainController(Object newModel) {
		super(newModel);
		this.controllers = new LinkedList<>();
	}
	
	public void addController(Controller c) {
		this.controllers.add(c);
	}
	
	public void setController(Controller c) {
		if(!this.controllers.contains(c)) this.addController(c);
		
		for(Controller con : this.controllers) {
			this.getView().removeMouseListener(con);
			this.getView().removeMouseMotionListener(con);
			this.getView().removeKeyListener(con);
		}
		
		this.getView().addMouseListener(c);
		this.getView().addMouseMotionListener(c);
		this.getView().addKeyListener(c);
	}
}
