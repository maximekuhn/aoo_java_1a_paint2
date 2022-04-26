package graphics.shapes.ui.toolbar;


import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import graphics.shapes.SCircle;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesToolController;
import graphics.shapes.ui.ShapesView;

public class ShapesTools extends ToolContainer implements ActionListener {
	
	private JButton squareButton;
	private JButton circleButton;
	private JButton triangleButton;
	private JButton lineButton;
	private JButton arrowButton;	
	private JButton letterButton;
	private JButton pencilButton;
	
	private ShapesToolController controller;

	public ShapesTools(ShapesView sview) {
		super(sview);
		//this.controller = (ShapesToolController) sview.getController();
	}

	@Override
	protected void buildButtons() {
		this.squareButton = new JButton(imageSize("src/pictures/square.png"));
		this.circleButton = new JButton(imageSize("src/pictures/circle.png"));
		this.triangleButton = new JButton(imageSize("src/pictures/triangle.png"));
		this.lineButton = new JButton(imageSize("src/pictures/line.png"));
		this.arrowButton = new JButton(imageSize("src/pictures/arrow.png"));
		this.letterButton = new JButton(imageSize("src/pictures/letter.png"));
		this.pencilButton = new JButton(imageSize("src/pictures/pencil.png"));
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
		/*
		if(e.getSource().equals(this.squareButton)) this.doAddSquare();
		else if(e.getSource().equals(this.circleButton)) this.doAddCircle();
		else if(e.getSource().equals(this.letterButton)) this.doAddText();
		*/
	}

	private void doAddSquare() {
		SRectangle sr = new SRectangle(new Point(0,0), 50, 50);
		sr.addAttributes(new ColorAttributes(true, true, Color.BLUE, Color.BLACK));
		sr.addAttributes(new SelectionAttributes(false));
		
		this.controller.setShape(sr);
	}
	
	private void doAddCircle() {
		SCircle sc = new SCircle(new Point(0,0), 50);
		sc.addAttributes(new ColorAttributes(true, true, Color.ORANGE, Color.BLACK));
		sc.addAttributes(new SelectionAttributes(false));
		
		this.controller.setShape(sc);
	}
	
	private void doAddText() {
		SText st = new SText(new Point(0,0), "text");
		st.addAttributes(new ColorAttributes(false, true, Color.BLACK, Color.BLACK));
		st.addAttributes(new FontAttributes());
		st.addAttributes(new SelectionAttributes());
		
		this.controller.setShape(st);
	}
	
}
