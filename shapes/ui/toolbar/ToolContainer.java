package graphics.shapes.ui.toolbar;

import java.util.LinkedList;

import javax.swing.JButton;

import graphics.shapes.ui.ShapesView;

public abstract class ToolContainer {
	
	private ShapesView sview;
	private LinkedList<JButton> buttons;
	
	public ToolContainer(ShapesView sview) {
		this.sview = sview;
		this.buttons = new LinkedList<JButton>();
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
	
	protected abstract void buildButtons();
	
	protected abstract void addAllButtons();
	
	protected abstract void addActionListener();
}
