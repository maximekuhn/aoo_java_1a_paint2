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
import graphics.shapes.ui.ShapesView;

public class WindowMenu extends JMenu implements ActionListener {

	private static final String WINDOW = "Window";
	private static final String DEFAULT_SIZE = "Default size";
	private static final String MAXIMIZE = "Maximize";
	private static final String CENTER = "Center";
	private static final String DARK_THEME = "Dark theme";
	private static final String LIGHT_THEME = "Light theme";
	
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 600;
	
	private Editor editor;
	private MenuBar menuBar;
	private ShapesView sview;
	
	private JMenuItem defaultSize;
	private JMenuItem maximize;
	private JMenuItem center;
	
	private JRadioButtonMenuItem darkTheme;
	private JRadioButtonMenuItem lightTheme;
	
	public WindowMenu(Editor editor, MenuBar menuBar, ShapesView sview) {
		super(WINDOW);
		this.editor = editor;
		this.menuBar = menuBar;
		this.sview = sview;
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
		// TODO : change color of other components
		this.sview.setBackground(Color.WHITE);
		this.sview.repaint();
	}

	private void doDarkTheme() {
		// TODO : change color of other components
		this.sview.setBackground(Color.GRAY);
	}

}
