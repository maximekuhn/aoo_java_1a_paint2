package graphics.shapes.ui.toolbar;



import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesView;

public class ShapesTool extends ToolContainer implements ActionListener {
	
	private JButton squareButton;
	private JButton circleButton;
	private JButton triangleButton;
	private JButton lineButton;
	private JButton arrowButton;	
	private JButton letterButton;
	private JButton pencilButton;

	public ShapesTool(ShapesView sview) {
		super(sview);
	}

	@Override
	protected void buildButtons() {
		this.squareButton = new JButton(imageSize("pictures/square.png"));
		this.circleButton = new JButton(imageSize("pictures/circle.png"));
		this.triangleButton = new JButton(imageSize("pictures/triangle.png"));
		this.lineButton = new JButton(imageSize("pictures/line.png"));
		this.arrowButton = new JButton(imageSize("pictures/arrow.png"));
		this.letterButton = new JButton(imageSize("pictures/letter.png"));
		this.pencilButton = new JButton(imageSize("pictures/pencil.png"));
	}

	@Override
	protected void addAllButtons() {
		this.addButton(this.squareButton);
		this.addButton(this.circleButton);
		this.addButton(this.triangleButton);
		this.addButton(this.lineButton);
		this.addButton(this.arrowButton);
		this.addButton(this.letterButton);
		this.addButton(this.pencilButton);
	}

	@Override
	protected void addActionListener() {
		for(JButton b : this.getButtons())
			b.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.squareButton))
			System.out.println("square button pressed");
	}
	
}
