package graphics.shapes.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import graphics.shapes.ui.MainController;
import graphics.shapes.ui.ShapesController;
import graphics.shapes.ui.ShapesView;

public class DeveloperMenu extends JMenu implements ActionListener {

	private static final String DEVELOPER = "Developer";
	private static final String CONTROLLER_PROMPT = "ShapesController";
	
	private ShapesController shapesController;
	private MainController mainController;
	
	private JMenuItem controllerItem;
	
	public DeveloperMenu(ShapesView sview) {
		super(DEVELOPER);
		this.shapesController = new ShapesController(sview.getModel());
		this.shapesController.setView(sview);
		this.mainController = (MainController) sview.getController();
		this.mainController.addController(this.shapesController);
		this.buildMenu();
	}
	
	private void buildMenu() {
		this.controllerItem = new JMenuItem(CONTROLLER_PROMPT);
		
		this.controllerItem.addActionListener(this);
		
		this.add(this.controllerItem);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.controllerItem)) this.doControllerItem();
	}

	private void doControllerItem() {
		this.mainController.setController(this.shapesController);
	}
}
