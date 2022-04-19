package graphics.shapes.ui.toolbar;

import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JToolBar;

import graphics.shapes.ui.ShapesView;

public class ToolBar extends JToolBar {
	
	private ShapesView sview;
	
	public ToolBar(ShapesView sview) {
		super("Tools");
		this.sview = sview;
		
		this.addContainer(new SelectionTools(this.sview));
        this.addSeparator();
<<<<<<< HEAD
		this.addContainer(new ShapesTool(this.sview));
		this.addSeparator();
		this.addContainer(new ColorTools(this.sview));
=======
		this.addContainer(new ShapesTool(this.sview));		
>>>>>>> e9085ea84babbd3294e220835c9a23536c6d6a6f
	}
	
	private void addContainer(ToolContainer tc) {
		LinkedList<JButton> buttons = tc.getButtons();
		for(JButton b : buttons)
			this.add(b);
	}
}
