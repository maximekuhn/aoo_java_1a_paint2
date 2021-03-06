package graphics.shapes.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Taskbar;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import graphics.shapes.SCollection;
import graphics.shapes.SEllipse;
import graphics.shapes.SRectangle;
import graphics.shapes.STextBox;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.menu.MenuBar;
import graphics.shapes.ui.toolbar.ToolBar;

public class Editor extends JFrame
{
	ShapesView sview;
	SCollection model;
	MenuBar menuBar;
	ToolBar toolbar;
	LayersView lview;
	
	public Editor()
	{	
		super("Paint Java");

		this.addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
				System.exit(0);
			}
		});
		
		this.buildModel();
		
		this.setMinimumSize(new Dimension(400, 400));


		File file = new File("src/pictures/appIcon.png");
		try {
			//set icon on JFrame menu bar, as in Windows system
			this.setIconImage(ImageIO.read(file));
			//set icon on system tray, as in Mac OS X system
			String os = System.getProperty("os.name").toLowerCase();
			if (os.startsWith("mac")) {
				final Taskbar taskbar = Taskbar.getTaskbar();
				taskbar.setIconImage(ImageIO.read(file));
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
		
		this.lview = new LayersView(this.model);
		this.lview.setPreferredSize(new Dimension(230,600));
		this.lview.setBackground(new Color(245, 245, 245));
		this.getContentPane().add(this.lview, BorderLayout.EAST);
		
		this.sview = new ShapesView(this.model, this.lview);
		this.sview.setPreferredSize(new Dimension(700,600));
		this.sview.setBackground(Color.WHITE);
		this.getContentPane().add(this.sview, java.awt.BorderLayout.CENTER);
		
		this.toolbar = new ToolBar(this.sview);
		this.menuBar = new MenuBar(this.sview, this.lview, this.toolbar, this);
		this.setJMenuBar(this.menuBar);
		
		this.sview.add(toolbar, BorderLayout.PAGE_START);
	}

	
	private void buildModel()
	{
		this.model = new SCollection();
		this.model.addAttributes(new SelectionAttributes());
		
		SRectangle r = new SRectangle(new Point(10,10),20,30);
		r.addAttributes(new ColorAttributes(true,false,Color.BLUE,Color.BLUE));
		r.addAttributes(new SelectionAttributes());
		this.model.add(r);
		
		SEllipse e = new SEllipse(new Point(100,100),10,10);
		e.addAttributes(new ColorAttributes(false,true,Color.BLUE,Color.BLUE));
		e.addAttributes(new SelectionAttributes());
		this.model.add(e);
		
		STextBox t= new STextBox(new Point(100,100),"hello");
		t.addAttributes(new ColorAttributes(true,true,Color.YELLOW,Color.BLUE));
		t.addAttributes(new FontAttributes());
		t.addAttributes(new SelectionAttributes());
		this.model.add(t);
		
		SCollection sc = new SCollection();
		sc.addAttributes(new SelectionAttributes());
		r= new SRectangle(new Point(20,30),30,30);
		r.addAttributes(new ColorAttributes(true,false,Color.MAGENTA,Color.BLUE));
		r.addAttributes(new SelectionAttributes());
		sc.add(r);
		e = new SEllipse(new Point(150,100),20,20);
		e.addAttributes(new ColorAttributes(false,true,Color.BLUE,Color.DARK_GRAY));
		e.addAttributes(new SelectionAttributes());
		sc.add(e);
		this.model.add(sc);
	}
	
	public static void main(String[] args)
	{
		Editor self = new Editor();
		self.pack();
		self.setVisible(true);
	}
}
