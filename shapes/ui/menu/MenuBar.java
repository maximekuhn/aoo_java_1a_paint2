package graphics.shapes.ui.menu;

import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
	
	private FileMenu fileMenu;
	
	public MenuBar() {
		super();
		this.fileMenu = new FileMenu();
		this.add(this.fileMenu);
	}

}
