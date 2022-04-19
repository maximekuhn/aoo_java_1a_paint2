package graphics.shapes.ui.menu;

import javax.swing.JMenuBar;

import graphics.shapes.ui.ShapesView;

public class MenuBar extends JMenuBar {
	
	private FileMenu fileMenu;
	private ShapesView sview;
	
	public MenuBar(ShapesView sview) {
		super();
		this.sview = sview;
		this.fileMenu = new FileMenu(this.sview);
		this.add(this.fileMenu);
	}

}
