package graphics.shapes.ui.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JMenuBar;

import graphics.shapes.ui.Editor;
import graphics.shapes.ui.ShapesView;

public class MenuBar extends JMenuBar {
	
	private FileMenu fileMenu;
	private SelectionMenu selectionMenu;
	private WindowMenu windowMenu;
	private DeveloperMenu developerMenu;
	
	private ShapesView sview;
	private Editor editor;
	
	private Color color;
	
	public MenuBar(ShapesView sview, Editor editor) {
		super();
		this.sview = sview;
		this.editor = editor;
		
		this.fileMenu = new FileMenu(this.sview);
		this.add(this.fileMenu);
		
		this.selectionMenu = new SelectionMenu(this.sview);
		this.add(this.selectionMenu);
		
		this.windowMenu = new WindowMenu(this.editor, this, this.sview);
		this.add(this.windowMenu);
		
		this.developerMenu = new DeveloperMenu(this.sview);
		this.add(this.developerMenu);
	}

}
