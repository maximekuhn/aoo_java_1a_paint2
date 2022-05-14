package graphics.shapes.ui.menu;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import graphics.shapes.SArrow;
import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SCross;
import graphics.shapes.SEllipse;
import graphics.shapes.SHexagon;
import graphics.shapes.SKotlin;
import graphics.shapes.SLine;
import graphics.shapes.SOctagon;
import graphics.shapes.SParallelogram;
import graphics.shapes.SPentagon;
import graphics.shapes.SRectangle;
import graphics.shapes.SStar;
import graphics.shapes.STextBox;
import graphics.shapes.STrapezium;
import graphics.shapes.STriangle;
import graphics.shapes.STriangleRec;
import graphics.shapes.STriangleScale;
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
	private static final String SHEXAGON = "Hexagon";
	private static final String STRIANGLEREC = "TriangleRec";
	private static final String SCROSS = "Cross";
	private static final String SELLIPSE = "Ellipse";
	private static final String SPENTAGON = "Pentagon";
	private static final String SARROW = "Arrow";
	private static final String SSTAR = "Star";
	private static final String SPARALLELOGRAM = "Parallelogram";
	private static final String SOCTAGON = "Octagon";
	private static final String STRAPEZIUM = "Trapezium";
	private static final String STRIANGLESCALE = "TriangleScale";
	
	private ShapesView sview;
	
	private JMenuItem scircle;
	private JMenuItem srectangle;
	private JMenuItem stextbox;
	private JMenuItem skotlin;
	private JMenuItem striangle;
	private JMenuItem sline;
	private JMenuItem shexagon;
	private JMenuItem strianglerec;
	private JMenuItem scross;
	private JMenuItem sellipse;
	private JMenuItem spentagon;
	private JMenuItem sarrow;
	private JMenuItem sstar;
	private JMenuItem sparallelogram;
	private JMenuItem soctagon;
	private JMenuItem strapezium;
	private JMenuItem strianglescale;
	
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
		this.shexagon = new JMenuItem(SHEXAGON);
		this.strianglerec = new JMenuItem(STRIANGLEREC);
		this.scross = new JMenuItem(SCROSS);
		this.sellipse = new JMenuItem(SELLIPSE);
		this.spentagon = new JMenuItem(SPENTAGON);
		this.sarrow = new JMenuItem(SARROW);
		this.sstar = new JMenuItem(SSTAR);
		this.sparallelogram = new JMenuItem(SPARALLELOGRAM);
		this.soctagon = new JMenuItem(SOCTAGON);
		this.strapezium = new JMenuItem(STRAPEZIUM);
		this.strianglescale = new JMenuItem(STRIANGLESCALE);
		
		
		// icons
		this.scircle.setIcon(MenuBar.iconSize("src/pictures/circle.png"));
		this.srectangle.setIcon(MenuBar.iconSize("src/pictures/square.png"));
		this.stextbox.setIcon(MenuBar.iconSize("src/pictures/letter.png"));
		this.skotlin.setIcon(MenuBar.iconSize("src/pictures/kotlin.png"));
		this.striangle.setIcon(MenuBar.iconSize("src/pictures/triangle.png"));
		this.sline.setIcon(MenuBar.iconSize("src/pictures/line.png"));
		this.shexagon.setIcon(MenuBar.iconSize("src/pictures/hexagon.png"));
		this.strianglerec.setIcon(MenuBar.iconSize("src/pictures/trianglerec.png"));
		this.scross.setIcon(MenuBar.iconSize("src/pictures/cross.png"));
		this.sellipse.setIcon(MenuBar.iconSize("src/pictures/ellipse.png"));
		this.spentagon.setIcon(MenuBar.iconSize("src/pictures/pentagon.png"));
		this.sarrow.setIcon(MenuBar.iconSize("src/pictures/arrow.png"));
		this.sstar.setIcon(MenuBar.iconSize("src/pictures/star.png"));
		this.sparallelogram.setIcon(MenuBar.iconSize("src/pictures/parallelogram.png"));
		this.soctagon.setIcon(MenuBar.iconSize("src/pictures/octagon.png"));
		this.strapezium.setIcon(MenuBar.iconSize("src/pictures/trapezium.png"));
		this.strianglescale.setIcon(MenuBar.iconSize("src/pictures/trianglescale.png"));
		
		this.scircle.addActionListener(this);
		this.srectangle.addActionListener(this);
		this.stextbox.addActionListener(this);
		this.skotlin.addActionListener(this);
		this.striangle.addActionListener(this);
		this.sline.addActionListener(this);
		this.shexagon.addActionListener(this);
		this.strianglerec.addActionListener(this);
		this.scross.addActionListener(this);
		this.sellipse.addActionListener(this);
		this.spentagon.addActionListener(this);
		this.sarrow.addActionListener(this);
		this.sstar.addActionListener(this);
		this.sparallelogram.addActionListener(this);
		this.soctagon.addActionListener(this);
		this.strapezium.addActionListener(this);
		this.strianglescale.addActionListener(this);
		
		this.add(this.scircle);
		this.add(this.srectangle);
		this.add(this.stextbox);
		this.add(this.skotlin);
		this.add(this.striangle);
		this.add(this.sline);
		this.add(this.shexagon);
		this.add(this.strianglerec);
		this.add(this.scross);
		this.add(this.sellipse);
		this.add(this.spentagon);
		this.add(this.sarrow);
		this.add(this.sstar);
		this.add(this.sparallelogram);
		this.add(this.soctagon);
		this.add(this.strapezium);
		this.add(this.strianglescale);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.scircle)) this.doCircle();
		else if(e.getSource().equals(this.srectangle)) this.doRectangle();
		else if(e.getSource().equals(this.stextbox)) this.doTextBox();
		else if(e.getSource().equals(this.skotlin)) this.doKotlin();
		else if(e.getSource().equals(this.striangle)) this.doTriangle();
		else if(e.getSource().equals(this.sline)) this.doLine();
		else if(e.getSource().equals(this.shexagon)) this.doHexagon();
		else if(e.getSource().equals(this.strianglerec)) this.doTriangleRec();
		else if(e.getSource().equals(this.scross)) this.doCross();
		else if(e.getSource().equals(this.sellipse)) this.doEllipse();
		else if(e.getSource().equals(this.spentagon)) this.doPentagon();
		else if(e.getSource().equals(this.sarrow)) this.doArrow();
		else if(e.getSource().equals(this.sstar)) this.doStar();
		else if(e.getSource().equals(this.sparallelogram)) this.doParallelogram();
		else if(e.getSource().equals(this.soctagon)) this.doOctagon();
		else if(e.getSource().equals(this.strapezium)) this.doTrapezium();
		else if(e.getSource().equals(this.strianglescale)) this.doTriangleScale();
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
		SLine sl = new SLine(new Point(this.sview.getWidth() / 2, this.sview.getHeight() / 2), 50, 50);
		sl.addAttributes(new SelectionAttributes());
		sl.addAttributes(new ColorAttributes(true, true, Color.BLACK, Color.BLACK));
		model.add(sl);
		this.sview.repaint();
	}
	
	private void doHexagon() {
		SCollection model = (SCollection) this.sview.getModel();
		SHexagon sh = new SHexagon(new Point(this.sview.getWidth() / 2, this.sview.getHeight() / 2), 50, 50);
		sh.addAttributes(new SelectionAttributes());
		sh.addAttributes(new ColorAttributes(true, true, Color.RED, Color.BLACK));
		model.add(sh);
		this.sview.repaint();
	}
	
	private void doTriangleRec() {
		SCollection model = (SCollection) this.sview.getModel();
		STriangleRec strec = new STriangleRec(new Point(this.sview.getWidth() / 2, this.sview.getHeight() / 2), 50, 50);
		strec.addAttributes(new SelectionAttributes());
		strec.addAttributes(new ColorAttributes(true, true, Color.ORANGE, Color.BLACK));
		model.add(strec);
		this.sview.repaint();
	}

	private void doCross() {
		SCollection model = (SCollection) this.sview.getModel();
		SCross scr = new SCross(new Point(this.sview.getWidth() / 2, this.sview.getHeight() / 2), 50, 50);
		scr.addAttributes(new SelectionAttributes());
		scr.addAttributes(new ColorAttributes(true, true, Color.RED, Color.BLACK));
		model.add(scr);
		this.sview.repaint();
	}
	
	private void doEllipse() {
		SCollection model = (SCollection) this.sview.getModel();
		SEllipse se = new SEllipse(new Point(this.sview.getWidth() / 2, this.sview.getHeight() / 2), 100, 50);
		se.addAttributes(new SelectionAttributes());
		se.addAttributes(new ColorAttributes(true, true, Color.MAGENTA, Color.BLACK));
		model.add(se);
		this.sview.repaint();
	}
	
	
	private void doPentagon() {
		SCollection model = (SCollection) this.sview.getModel();
		SPentagon spt = new SPentagon(new Point(this.sview.getWidth() / 2, this.sview.getHeight() / 2), 50, 50);
		spt.addAttributes(new SelectionAttributes());
		spt.addAttributes(new ColorAttributes(true, true, Color.DARK_GRAY, Color.BLACK));
		model.add(spt);
		this.sview.repaint();
	}
	
	private void doArrow() {
		SCollection model = (SCollection) this.sview.getModel();
		SArrow sar = new SArrow(new Point(this.sview.getWidth() / 2, this.sview.getHeight() / 2), 50, 50);
		sar.addAttributes(new SelectionAttributes());
		sar.addAttributes(new ColorAttributes(true, true, Color.CYAN, Color.BLACK));
		model.add(sar);
		this.sview.repaint();
	}
	
	private void doStar() {
		SCollection model = (SCollection) this.sview.getModel();
		SStar sst = new SStar(new Point(this.sview.getWidth() / 2, this.sview.getHeight() / 2), 50, 50);
		sst.addAttributes(new SelectionAttributes());
		sst.addAttributes(new ColorAttributes(true, true, Color.CYAN, Color.BLACK));
		model.add(sst);
		this.sview.repaint();
	}
	
	private void doParallelogram() {
		SCollection model = (SCollection) this.sview.getModel();
		SParallelogram spg = new SParallelogram(new Point(this.sview.getWidth() / 2, this.sview.getHeight() / 2), 50, 50);
		spg.addAttributes(new SelectionAttributes());
		spg.addAttributes(new ColorAttributes(true, true, Color.CYAN, Color.BLACK));
		model.add(spg);
		this.sview.repaint();
	}
	
	private void doOctagon() {
		SCollection model = (SCollection) this.sview.getModel();
		SOctagon soc = new SOctagon(new Point(this.sview.getWidth() / 2, this.sview.getHeight() / 2), 50, 50);
		soc.addAttributes(new SelectionAttributes());
		soc.addAttributes(new ColorAttributes(true, true, Color.CYAN, Color.BLACK));
		model.add(soc);
		this.sview.repaint();
	}
	
	private void doTrapezium() {
		SCollection model = (SCollection) this.sview.getModel();
		STrapezium stp = new STrapezium(new Point(this.sview.getWidth() / 2, this.sview.getHeight() / 2), 50, 50);
		stp.addAttributes(new SelectionAttributes());
		stp.addAttributes(new ColorAttributes(true, true, Color.CYAN, Color.BLACK));
		model.add(stp);
		this.sview.repaint();
	}
	
	private void doTriangleScale() {
		SCollection model = (SCollection) this.sview.getModel();
		STriangleScale stc = new STriangleScale(new Point(this.sview.getWidth() / 2, this.sview.getHeight() / 2), 50, 50);
		stc.addAttributes(new SelectionAttributes());
		stc.addAttributes(new ColorAttributes(true, true, Color.CYAN, Color.BLACK));
		model.add(stc);
		this.sview.repaint();
	}
}
