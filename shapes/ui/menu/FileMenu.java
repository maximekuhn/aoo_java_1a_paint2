package graphics.shapes.ui.menu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import graphics.shapes.SCollection;
import graphics.shapes.SImage;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesView;

public class FileMenu extends JMenu implements ActionListener {
	
	private static final String FILE = "File";
	private static final String OPEN = "open";
	private static final String SAVE = "save";
	private static final String QUIT = "quit";
	
	private ShapesView sview;
	
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem quit;
	
	public FileMenu(ShapesView sview) {
		super(FILE);
		this.sview = sview;
		this.buildMenu();
	}
	
	private void buildMenu() {
		this.open = new JMenuItem(OPEN);
		this.save = new JMenuItem(SAVE);
		this.quit = new JMenuItem(QUIT);
		
		this.open.addActionListener(this);
		this.save.addActionListener(this);
		this.quit.addActionListener(this);
		
		// keyboard shortcuts
		this.open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		this.save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		
		this.add(this.open);
		this.add(this.save);
		this.addSeparator();
		this.add(this.quit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.quit)) this.doExit();
		else if(e.getSource().equals(this.open)) this.doOpen();
		else if(e.getSource().equals(this.save)) this.doSave();
	}
	
	private void doSave() {
		JFileChooser fc = new JFileChooser();
		fc.showSaveDialog(null);
		File fileToSave = fc.getSelectedFile();
		
		if(fileToSave == null) return;
		
		/*
		 * SAVE
		 */
		ShapesView tmpSView = new ShapesView(this.sview.getModel());
		tmpSView.setSize(this.sview.getSize());
		tmpSView.setBackground(this.sview.getBackground());
		
		BufferedImage bi = new BufferedImage(tmpSView.getWidth(), tmpSView.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2D = bi.createGraphics();
		tmpSView.paint(g2D);
		
		try {
			ImageIO.write(bi, "png", fileToSave);
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			tmpSView = null;
		}
	}

	private void doOpen() {
		JFileChooser fc = new JFileChooser();
		int result = fc.showOpenDialog(null);
		File fileToOpen = fc.getSelectedFile();
		
		if(fileToOpen == null || result == JFileChooser.CANCEL_OPTION) return;
		
		try {
			Image img = ImageIO.read(fileToOpen);
			SImage si = new SImage(new Point(0,0), img);
			si.addAttributes(new SelectionAttributes());
			si.addAttributes(new ColorAttributes(false, false, Color.BLACK, Color.BLACK));
			((SCollection)this.sview.getModel()).add(si);
			this.sview.repaint();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void doExit() {
		System.exit(0);
	}

}
