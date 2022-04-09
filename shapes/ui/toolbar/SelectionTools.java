package graphics.shapes.ui.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
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
		this.buildButtons();
		this.addAllButtons();
		this.addActionListener();
	}
	
	@Override
	protected void buildButtons() {
		this.selectionButton = new JButton("selection");
		this.translateButton = new JButton("translate");
		this.rotateButton = new JButton("rotate");
		this.ereaseButton = new JButton("erase");
		this.groupButton = new JButton("group");
		
		this.selectionButton.setIcon(new ImageIcon("/res/icons/cursor.png"));
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
		if(e.getSource().equals(this.selectionButton))
			System.out.println("selection button pressed");
	}
}
