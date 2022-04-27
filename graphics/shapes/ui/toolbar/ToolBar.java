package graphics.shapes.ui.toolbar;

import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JToolBar;

import graphics.shapes.ui.Editor;
import graphics.shapes.ui.ShapesView;

public class ToolBar extends JToolBar {
	
	private ShapesView sview;
	
	private SelectionTools selectionTools;
	private ShapesTools shapesTools;
	
	public ToolBar(ShapesView sview) {
		super("Tools");
		this.sview = sview;
		
		this.selectionTools = new SelectionTools(this.sview);
		this.shapesTools = new ShapesTools(this.sview);
		
		this.addContainer(this.selectionTools);
        this.addSeparator();
		this.addContainer(this.shapesTools);
	}
	
	private void addContainer(ToolContainer tc) {
		LinkedList<JButton> buttons = tc.getButtons();
		for(JButton b : buttons)
			this.add(b);
	}
}
