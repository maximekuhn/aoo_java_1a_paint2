package graphics.shapes.ui.menu;

import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JMenuBar;

import graphics.shapes.ui.Editor;
import graphics.shapes.ui.LayersView;
import graphics.shapes.ui.ShapesView;
import graphics.shapes.ui.toolbar.ToolBar;

public class MenuBar extends JMenuBar {
	
	public FileMenu fileMenu;
	public SelectionMenu selectionMenu;
	public ShapeMenu shapeMenu;
	public WindowMenu windowMenu;
	public LayerMenu layerMenu;
	public DeveloperMenu developerMenu;
	
	private ShapesView sview;
	private LayersView lview;
	private ToolBar toolbar;
	private Editor editor;
	private Color bgColor = Color.WHITE;
	
	public MenuBar(ShapesView sview, LayersView lview, ToolBar toolbar, Editor editor) {
		super();
		this.sview = sview;
		this.lview = lview;
		this.toolbar = toolbar;
		this.editor = editor;
		
		this.fileMenu = new FileMenu(this.sview);
		this.add(this.fileMenu);
		
		this.selectionMenu = new SelectionMenu(this.sview);
		this.add(this.selectionMenu);
		
		this.shapeMenu = new ShapeMenu(this.sview);
		this.add(this.shapeMenu);
		
		this.windowMenu = new WindowMenu(this.editor, this, this.sview, this.lview, this.toolbar);
		this.add(this.windowMenu);
		
		this.layerMenu = new LayerMenu(this.sview);
		this.add(this.layerMenu);
		
		this.developerMenu = new DeveloperMenu(this.sview);
		this.add(this.developerMenu);
	}
	
	public static ImageIcon iconSize(String filename) {
		return new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
	}

	public void setColor(Color color) {
		this.bgColor = color;
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

    }

}
