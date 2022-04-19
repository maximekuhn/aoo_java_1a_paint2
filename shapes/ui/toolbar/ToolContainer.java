package graphics.shapes.ui.toolbar;

import java.awt.Image;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import graphics.shapes.ui.ShapesView;

public abstract class ToolContainer {
	
	private ShapesView sview;
	private LinkedList<JButton> buttons;
	
	public ToolContainer(ShapesView sview) {
		this.sview = sview;
		this.buttons = new LinkedList<JButton>();
		this.buildButtons();
		
		this.addAllButtons();
		this.addActionListener();
		
		/*
		 * remove focus on JButtons so ShapesView has the focus
		 */
		for(JButton b : this.buttons)
			b.setRequestFocusEnabled(false);
	}
	
	

	public LinkedList<JButton> getButtons(){
		return this.buttons;
	}

	public ShapesView getView() {
		return this.sview;
	}
	
	protected void addButton(JButton b) {
		this.buttons.add(b);
	}
	
	public ImageIcon imageSize(String filename) {
		return new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
	}
	
	protected abstract void buildButtons();
	
	protected abstract void addAllButtons();
	
	protected abstract void addActionListener();
}
