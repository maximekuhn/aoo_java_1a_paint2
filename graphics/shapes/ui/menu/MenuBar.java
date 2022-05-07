package graphics.shapes.ui.menu;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JMenuBar;

import graphics.shapes.ui.Editor;
import graphics.shapes.ui.ShapesView;

public class MenuBar extends JMenuBar {
	
	private FileMenu fileMenu;
	private SelectionMenu selectionMenu;
	private ShapeMenu shapeMenu;
	private WindowMenu windowMenu;
	private LayerMenu layerMenu;
	private DeveloperMenu developerMenu;
	
	private ShapesView sview;
	private Editor editor;
	
	public MenuBar(ShapesView sview, Editor editor) {
		super();
		this.sview = sview;
		this.editor = editor;
		
		this.fileMenu = new FileMenu(this.sview);
		this.add(this.fileMenu);
		
		this.selectionMenu = new SelectionMenu(this.sview);
		this.add(this.selectionMenu);
		
		this.shapeMenu = new ShapeMenu(this.sview);
		this.add(this.shapeMenu);
		
		this.windowMenu = new WindowMenu(this.editor, this, this.sview);
		this.add(this.windowMenu);
		
		this.layerMenu = new LayerMenu(this.sview);
		this.add(this.layerMenu);
		
		this.developerMenu = new DeveloperMenu(this.sview);
		this.add(this.developerMenu);
	}
	
	public static ImageIcon iconSize(String filename) {
		return new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
	}

}
