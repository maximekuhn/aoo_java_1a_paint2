package graphics.shapes.ui.toolbar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import graphics.shapes.ui.ShapesView;

public class SelectionTools extends ToolContainer implements ActionListener {

	private JButton selectionButton;
	private JButton translateButton;
	private JButton rotateButton;
	private JButton ereaseButton;
	private JButton groupButton;
	
	public SelectionTools(ShapesView sview) {
		super(sview);
	}
	
	@Override
	protected void buildButtons() {
		this.selectionButton = new JButton(imageSize("src/pictures/cursor.png"));
		this.translateButton = new JButton(imageSize("src/pictures/move.png"));
		this.rotateButton = new JButton(imageSize("src/pictures/rotate.png"));
		this.ereaseButton = new JButton(imageSize("src/pictures/eraser.png"));
		this.groupButton = new JButton(imageSize("src/pictures/group.png"));
	}
	
	@Override
	protected void addAllButtons() {
		this.addButton(this.selectionButton);
		this.addButton(this.translateButton);
		this.addButton(this.rotateButton);
		this.addButton(this.ereaseButton);
		this.addButton(this.groupButton);
	}
	
	@Override
	protected void addActionListener() {
		for(JButton b : this.getButtons())
			b.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
