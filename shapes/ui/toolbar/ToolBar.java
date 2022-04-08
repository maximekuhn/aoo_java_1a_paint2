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
		
		// TODO: delete
		System.out.println("fuck git");
	}
	
	public void addContainer(ToolContainer tc) {
		LinkedList<JButton> buttons = tc.getButtons();
		for(JButton b : buttons)
			this.add(b);
	}

}
