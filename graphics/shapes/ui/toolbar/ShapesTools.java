package graphics.shapes.ui.toolbar;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SLine;
import graphics.shapes.SRectangle;
import graphics.shapes.STextBox;
import graphics.shapes.STriangle;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.MainController;
import graphics.shapes.ui.ShapesToolController;
import graphics.shapes.ui.ShapesView;

public class ShapesTools extends ToolContainer implements ActionListener {
	
	private ToolBar tb;
	
	private JButton squareButton;
	private JButton circleButton;
	private JButton triangleButton;
	private JButton lineButton;
	private JButton arrowButton;	
	private JButton letterButton;
	private JButton pencilButton;
	
	private ShapesToolController controller;
	private MainController mainController;

	public ShapesTools(ShapesView sview, ToolBar tb) {
		super(sview);
		this.tb = tb;
		this.controller = new ShapesToolController(sview.getModel());
		this.controller.setView(sview);
		this.mainController = (MainController) this.sview.getController();
		this.mainController.addController(this.controller);
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
		this.mainController.setController(this.controller);
		if(e.getSource().equals(this.squareButton)) this.doAddSquare();
		else if(e.getSource().equals(this.circleButton)) this.doAddCircle();
		else if(e.getSource().equals(this.letterButton)) this.doAddText();
		else if(e.getSource().equals(this.pencilButton)) this.doPencil();
		else if(e.getSource().equals(this.triangleButton)) this.doTriangle();
		else if(e.getSource().equals(this.lineButton)) this.doLine();
	}

	private void doLine() {
		this.tb.highlightButton(this.lineButton);
		
		SLine sh = new SLine(new Point(this.sview.getWidth() / 2, this.sview.getHeight() / 2), 50, 50);
		sh.addAttributes(new ColorAttributes(true, true, Color.BLACK, Color.BLACK));
		sh.addAttributes(new SelectionAttributes(false));
		
		this.controller.setShape(sh);
		this.controller.disallowSketch();
	
		
	}

	private void doTriangle() {
		this.tb.highlightButton(this.triangleButton);
		
		STriangle st = new STriangle(new Point(0,0), 50, 50);
		st.addAttributes(new ColorAttributes(true, true, Color.GREEN, Color.BLACK));
		st.addAttributes(new SelectionAttributes(false));
		
		this.controller.setShape(st);
		this.controller.disallowSketch();
		
		Image cursorImage = cursorSize("src/pictures/triangle.png");
		if(cursorImage != null) {
			Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "customCursor");
			this.getView().setCursor(customCursor);
		}
	}

	private void doPencil() {
		this.tb.highlightButton(this.pencilButton);
		
		this.controller.setShape(null);
		this.controller.allowSketch();
		
		Image cursorImage = cursorSize("src/pictures/cursors/pencil_cursor.png");
		if(cursorImage != null) {
			Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "customCursor");
			this.getView().setCursor(customCursor);
		}
	}

	private void doAddSquare() {
		this.tb.highlightButton(this.squareButton);
		
		SRectangle sr = new SRectangle(new Point(0,0), 50, 50);
		sr.addAttributes(new ColorAttributes(true, true, Color.BLUE, Color.BLACK));
		sr.addAttributes(new SelectionAttributes(false));
		
		this.controller.setShape(sr);
		this.controller.disallowSketch();
		
		Image cursorImage = cursorSize("src/pictures/square.png");
		if(cursorImage != null) {
			Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "customCursor");
			this.getView().setCursor(customCursor);
		}
	}
	
	private void doAddCircle() {
		this.tb.highlightButton(this.circleButton);
		
		SCircle sc = new SCircle(new Point(0,0), 50);
		sc.addAttributes(new ColorAttributes(true, true, Color.ORANGE, Color.BLACK));
		sc.addAttributes(new SelectionAttributes(false));
		
		this.controller.setShape(sc);
		this.controller.disallowSketch();
		
		Image cursorImage = cursorSize("src/pictures/circle.png");
		if(cursorImage != null) {
			Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "customCursor");
			this.getView().setCursor(customCursor);
		}
	}
	
	private void doAddText() {
		this.tb.highlightButton(this.letterButton);
		
		STextBox st = new STextBox(new Point(0,0), "text");
		st.addAttributes(new ColorAttributes(false, true, Color.BLACK, Color.BLACK));
		st.addAttributes(new FontAttributes());
		st.addAttributes(new SelectionAttributes());
		
		this.controller.setShape(st);
		this.controller.disallowSketch();
		
		Image cursorImage = cursorSize("src/pictures/letter.png");
		if(cursorImage != null) {
			Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "customCursor");
			this.getView().setCursor(customCursor);
		}
	}
	
}
