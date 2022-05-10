package graphics.shapes.ui.menu;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SKotlin;
import graphics.shapes.SLine;
import graphics.shapes.SRectangle;
import graphics.shapes.STextBox;
import graphics.shapes.STriangle;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesView;

public class ShapeMenu extends JMenu implements ActionListener {

	private static final String SHAPE = "Shape";
	private static final String SCIRCLE = "Circle";
	private static final String SRECTANGLE = "Rectangle";
	private static final String STEXTBOX = "Text box";
	private static final String SKOTLIN = "Kotlin logo";
	private static final String STRIANGLE = "Triangle";
	private static final String SLINE = "Line";
	
	private ShapesView sview;
	
	private JMenuItem scircle;
	private JMenuItem srectangle;
	private JMenuItem stextbox;
	private JMenuItem skotlin;
	private JMenuItem striangle;
	private JMenuItem sline;
	
	public ShapeMenu(ShapesView sview) {
		super(SHAPE);
		this.sview = sview;
		this.buildMenu();
	}
	
	private void buildMenu() {
		this.scircle = new JMenuItem(SCIRCLE);
		this.srectangle = new JMenuItem(SRECTANGLE);
		this.stextbox = new JMenuItem(STEXTBOX);
		this.skotlin = new JMenuItem(SKOTLIN);
		this.striangle = new JMenuItem(STRIANGLE);
		this.sline = new JMenuItem(SLINE);
		
		// icons
		this.scircle.setIcon(MenuBar.iconSize("src/pictures/circle.png"));
		this.srectangle.setIcon(MenuBar.iconSize("src/pictures/square.png"));
		this.stextbox.setIcon(MenuBar.iconSize("src/pictures/letter.png"));
		this.skotlin.setIcon(MenuBar.iconSize("src/pictures/kotlin.png"));
		this.striangle.setIcon(MenuBar.iconSize("src/pictures/triangle.png"));
		this.sline.setIcon(MenuBar.iconSize("src/pictures/line.png"));
		
		this.scircle.addActionListener(this);
		this.srectangle.addActionListener(this);
		this.stextbox.addActionListener(this);
		this.skotlin.addActionListener(this);
		this.striangle.addActionListener(this);
		this.sline.addActionListener(this);
		
		this.add(this.scircle);
		this.add(this.srectangle);
		this.add(this.stextbox);
		this.add(this.skotlin);
		this.add(this.striangle);
		this.add(this.sline);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.scircle)) this.doCircle();
		else if(e.getSource().equals(this.srectangle)) this.doRectangle();
		else if(e.getSource().equals(this.stextbox)) this.doTextBox();
		else if(e.getSource().equals(this.skotlin)) this.doKotlin();
		else if(e.getSource().equals(this.striangle)) this.doTriangle();
		else if(e.getSource().equals(this.sline)) this.doLine();
	}

	private void doCircle() {
		SCollection model = (SCollection) this.sview.getModel();
		SCircle sc = new SCircle(new Point(this.sview.getWidth() / 2, this.sview.getHeight() / 2), 50);
		sc.addAttributes(new SelectionAttributes());
		sc.addAttributes(new ColorAttributes(true, true, Color.ORANGE, Color.BLACK));
		model.add(sc);
		this.sview.repaint();
	}

	private void doRectangle() {
		SCollection model = (SCollection) this.sview.getModel();
		SRectangle sr = new SRectangle(new Point(this.sview.getWidth() / 2, this.sview.getHeight() / 2), 50, 50);
		sr.addAttributes(new SelectionAttributes());
		sr.addAttributes(new ColorAttributes(true, true, Color.BLUE, Color.BLACK));
		model.add(sr);
		this.sview.repaint();
	}

	private void doTextBox() {
		SCollection model = (SCollection) this.sview.getModel();
		STextBox stb = new STextBox(new Point(this.sview.getWidth() / 2, this.sview.getHeight() / 2), "text");
		stb.addAttributes(new FontAttributes());
		stb.addAttributes(new SelectionAttributes());
		model.add(stb);
		this.sview.repaint();
	}
	
	private void doKotlin() {
		SCollection model = (SCollection) this.sview.getModel();
		SKotlin sk = new SKotlin(new Point(this.sview.getWidth() / 2, this.sview.getHeight() / 2), 50, 70);
		sk.addAttributes(new SelectionAttributes());
		sk.addAttributes(new ColorAttributes(true, true, new Color(166, 0, 255), Color.BLACK));
		model.add(sk);
		this.sview.repaint();
	}
	
	private void doTriangle() {
		SCollection model = (SCollection) this.sview.getModel();
		STriangle st = new STriangle(new Point(this.sview.getWidth() / 2, this.sview.getHeight() / 2), 50, 50);
		st.addAttributes(new SelectionAttributes());
		st.addAttributes(new ColorAttributes(true, true, Color.GREEN, Color.BLACK));
		model.add(st);
		this.sview.repaint();
	}
	
	private void doLine() {
		SCollection model = (SCollection) this.sview.getModel();
		SLine sh = new SLine(new Point(this.sview.getWidth() / 2, this.sview.getHeight() / 2), 50, 50);
		sh.addAttributes(new SelectionAttributes());
		sh.addAttributes(new ColorAttributes(true, true, Color.BLACK, Color.BLACK));
		model.add(sh);
		this.sview.repaint();
	}

}
