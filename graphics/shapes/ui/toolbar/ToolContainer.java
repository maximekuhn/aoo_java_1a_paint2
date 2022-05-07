package graphics.shapes.ui.toolbar;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import graphics.shapes.ui.ShapesView;

public abstract class ToolContainer {
	
	protected ShapesView sview;
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
		return new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
	}
	
	public static Image cursorSize(String filename) {
		try {
			Image cursorImage = ImageIO.read(new File(filename));
			cursorImage = cursorImage.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
			return cursorImage;
		}
		catch(IOException e1) {
			e1.printStackTrace();
		}
		catch(Exception e2) {
			e2.printStackTrace();
		}
		return null;
	}
	
	protected abstract void buildButtons();
	
	protected abstract void addAllButtons();
	
	protected abstract void addActionListener();
}
