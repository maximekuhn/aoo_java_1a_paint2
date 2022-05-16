package graphics.shapes.ui.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import graphics.shapes.ui.Editor;
import graphics.shapes.ui.LayersView;
import graphics.shapes.ui.ShapesView;
import graphics.shapes.ui.toolbar.ToolBar;

public class WindowMenu extends JMenu implements ActionListener {

	private static final String WINDOW = "Window";
	private static final String DEFAULT_SIZE = "Default size";
	private static final String MAXIMIZE = "Maximize";
	private static final String CENTER = "Center";
	private static final String DARK_THEME = "Dark theme";
	private static final String LIGHT_THEME = "Light theme";
	
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 600;
	
	private static final Color DARK_THEME_MAIN_COLOR = new Color(60, 60, 60);
	private static final Color DARK_THEME_ACCENT_COLOR = Color.GRAY;
	private static final Color LIGHT_THEME_MAIN_COLOR = Color.WHITE;
	private static final Color LIGHT_THEME_ACCENT_COLOR = new Color(245, 245, 245);
	
	private Editor editor;
	private MenuBar menuBar;
	private ShapesView sview;
	private LayersView lview;
	private ToolBar toolbar;
	
	private JMenuItem defaultSize;
	private JMenuItem maximize;
	private JMenuItem center;
	
	private JRadioButtonMenuItem darkTheme;
	private JRadioButtonMenuItem lightTheme;

	public boolean darkThemeEnabled;
	
	public WindowMenu(Editor editor, MenuBar menuBar, ShapesView sview, LayersView lview, ToolBar toolbar) {
		super(WINDOW);
		this.editor = editor;
		this.menuBar = menuBar;
		this.sview = sview;
		this.lview = lview;
		this.toolbar = toolbar;
		this.buildMenu();
	}
	
	private void buildMenu() {
		this.defaultSize = new JMenuItem(DEFAULT_SIZE);
		this.maximize = new JMenuItem(MAXIMIZE);
		this.center = new JMenuItem(CENTER);
		
		ButtonGroup themesButtons = new ButtonGroup();
		this.darkTheme = new JRadioButtonMenuItem(DARK_THEME);
		this.lightTheme = new JRadioButtonMenuItem(LIGHT_THEME);
		this.lightTheme.setSelected(true);
		themesButtons.add(this.lightTheme);
		themesButtons.add(this.darkTheme);
		
		this.defaultSize.addActionListener(this);
		this.maximize.addActionListener(this);
		this.center.addActionListener(this);
		this.darkTheme.addActionListener(this);
		this.lightTheme.addActionListener(this);
		
		// keyboard shortcuts
		this.defaultSize.setAccelerator(KeyStroke.getKeyStroke("control shift R"));
		this.maximize.setAccelerator(KeyStroke.getKeyStroke("control shift M"));
		this.center.setAccelerator(KeyStroke.getKeyStroke("control shift C"));
		this.lightTheme.setAccelerator(KeyStroke.getKeyStroke("control shift L"));
		this.darkTheme.setAccelerator(KeyStroke.getKeyStroke("control shift D"));
		
		this.add(this.defaultSize);
		this.add(this.maximize);
		this.addSeparator();
		this.add(this.center);
		this.addSeparator();
		this.add(this.lightTheme);
		this.add(this.darkTheme);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.defaultSize)) this.doDefaultSize();
		else if(e.getSource().equals(this.maximize)) this.doMaximize();
		else if(e.getSource().equals(this.center)) this.doCenter();
		else if(e.getSource().equals(this.lightTheme)) this.doLightTheme();
		else if(e.getSource().equals(this.darkTheme)) this.doDarkTheme();
	}
	
	private void doDefaultSize() {
		this.editor.setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
	}
	
	private void doMaximize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.editor.setSize(screenSize.width, screenSize.height);
		this.doCenter();
	}
	
	private void doCenter() {
		this.editor.setLocationRelativeTo(null);
	}
	
	private void doLightTheme() {
		this.sview.setBackground(LIGHT_THEME_MAIN_COLOR);
		this.lview.setBackground(LIGHT_THEME_ACCENT_COLOR);
		this.toolbar.setBackgroundColor(this.darkThemeEnabled, LIGHT_THEME_ACCENT_COLOR);
		this.lview.setColor(LIGHT_THEME_MAIN_COLOR, LIGHT_THEME_ACCENT_COLOR);
		this.menuBar.setColor(LIGHT_THEME_MAIN_COLOR);
		this.menuBar.fileMenu.setOpaque(true);
		this.menuBar.selectionMenu.setOpaque(true);
		this.menuBar.shapeMenu.setOpaque(true);
		this.menuBar.windowMenu.setOpaque(true);
		this.menuBar.layerMenu.setOpaque(true);
		this.menuBar.developerMenu.setOpaque(true);
		this.darkThemeEnabled = false;
		this.sview.repaint();
		this.lview.repaint();
		this.menuBar.repaint();
	}

	private void doDarkTheme() {
		this.sview.setBackground(DARK_THEME_MAIN_COLOR);
		this.lview.setBackground(DARK_THEME_ACCENT_COLOR);
		this.toolbar.setBackgroundColor(this.darkThemeEnabled, DARK_THEME_ACCENT_COLOR);
		this.lview.setColor(DARK_THEME_MAIN_COLOR, DARK_THEME_ACCENT_COLOR);
		this.menuBar.setColor(DARK_THEME_ACCENT_COLOR);
		this.menuBar.fileMenu.setOpaque(false);
		this.menuBar.selectionMenu.setOpaque(false);
		this.menuBar.shapeMenu.setOpaque(false);
		this.menuBar.windowMenu.setOpaque(false);
		this.menuBar.layerMenu.setOpaque(false);
		this.menuBar.developerMenu.setOpaque(false);
		this.darkThemeEnabled = true;
		this.sview.repaint();
		this.lview.repaint();
		this.menuBar.repaint();
	}

}
