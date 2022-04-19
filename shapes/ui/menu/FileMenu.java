package graphics.shapes.ui.menu;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
		
		BufferedImage bi = new BufferedImage(this.sview.getWidth(), this.sview.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2D = bi.createGraphics();
		this.sview.paint(g2D);
		try {
			ImageIO.write(bi, "png", fileToSave);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void doOpen() {
		System.out.println("open");
	}

	private void doExit() {
		System.exit(0);
	}

}
