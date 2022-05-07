package graphics.shapes.ui.toolbar;

import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;

import graphics.shapes.ui.ShapesView;

public class ToolBar extends JToolBar {
	
	private static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
	private static final Color HIGHLIGHT_BORDER_COLOR = Color.RED;
	
	private LinkedList<JButton> buttons;
	
	private ShapesView sview;
	
	private SelectionTools selectionTools;
	private ShapesTools shapesTools;
	private SettingsTools settingsTools;
	
	public ToolBar(ShapesView sview) {
		super("Tools");
		this.sview = sview;
		
		this.buttons = new LinkedList<>();
		
		this.selectionTools = new SelectionTools(this.sview, this);
		this.shapesTools = new ShapesTools(this.sview, this);
		this.settingsTools = new SettingsTools(this.sview, this);
		
		this.addContainer(this.selectionTools);
        this.addSeparator();
		this.addContainer(this.shapesTools);
		this.addSeparator();
		this.addContainer(this.settingsTools);
		
		this.highlightButton(null);
	}
	
	private void addContainer(ToolContainer tc) {
		LinkedList<JButton> buttons = tc.getButtons();
		for(JButton b : buttons) {
			this.add(b);
			this.buttons.add(b);
		}
	}
	
	public void highlightButton(JButton b) {
		for(JButton button : this.buttons) {
			if(button.equals(b))
				button.setBorder(BorderFactory.createLineBorder(HIGHLIGHT_BORDER_COLOR));
			else
				button.setBorder(BorderFactory.createLineBorder(DEFAULT_BORDER_COLOR));
		}
	}
}
