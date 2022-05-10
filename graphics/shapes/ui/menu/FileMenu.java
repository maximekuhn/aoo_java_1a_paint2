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
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import graphics.shapes.SCollection;
import graphics.shapes.SImage;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesView;
import graphics.shapes.ui.menu.project_manager.ProjectOpener;
import graphics.shapes.ui.menu.project_manager.ProjectSaver;

public class FileMenu extends JMenu implements ActionListener {
	
	private static final String FILE = "File";
	private static final String NEW_FILE = "New file";
	private static final String OPEN = "Import image";
	private static final String SAVE = "Export to png";
	private static final String SAVE_PROJECT = "Save project";
	private static final String OPEN_PROJECT = "Open project";
	private static final String QUIT = "quit";
	
	private static final String SAVE_EXTENSION = "png";
	private static final String SAVE_PROJECT_EXTENSION = "aoo";
	
	private ShapesView sview;
	
	private JMenuItem newFile;
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem openProject;
	private JMenuItem saveProject;
	private JMenuItem quit;
	
	public FileMenu(ShapesView sview) {
		super(FILE);
		this.sview = sview;
		this.buildMenu();
	}
	
	private void buildMenu() {
		this.newFile = new JMenuItem(NEW_FILE);
		this.open = new JMenuItem(OPEN);
		this.save = new JMenuItem(SAVE);
		this.openProject = new JMenuItem(OPEN_PROJECT);
		this.saveProject = new JMenuItem(SAVE_PROJECT);
		this.quit = new JMenuItem(QUIT);
		
		this.newFile.addActionListener(this);
		this.open.addActionListener(this);
		this.save.addActionListener(this);
		this.openProject.addActionListener(this);
		this.saveProject.addActionListener(this);
		this.quit.addActionListener(this);
		
		// keyboard shortcuts
		this.newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		this.open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		this.save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		this.openProject.setAccelerator(KeyStroke.getKeyStroke("control shift O"));
		this.saveProject.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
		
		this.add(this.newFile);
		this.addSeparator();
		this.add(this.open);
		this.add(this.save);
		this.addSeparator();
		this.add(this.openProject);
		this.add(this.saveProject);
		this.addSeparator();
		this.add(this.quit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.quit)) this.doExit();
		else if(e.getSource().equals(this.open)) this.doOpen();
		else if(e.getSource().equals(this.save)) this.doSave();
		else if(e.getSource().equals(this.openProject)) this.doOpenProject();
		else if(e.getSource().equals(this.saveProject)) this.doSaveProject();
		else if(e.getSource().equals(this.newFile)) this.doNewFile();
	}
	
	private void doNewFile() {
		SCollection model = (SCollection) this.sview.getModel();
		Iterator<Shape> iterator = model.iterator();
		
		LinkedList<Shape> shapesToRemove = new LinkedList<>();
		Shape shape;
		while(iterator.hasNext()) {
			shape = iterator.next();
			shapesToRemove.add(shape);
		}
		for(Shape s : shapesToRemove) model.remove(s);
		this.sview.repaint();
	}

	private void doSaveProject() {
		/*
		 * save as project (.aoo)
		 */
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle(SAVE_PROJECT);
		fc.showSaveDialog(null);
		File fileToSave = fc.getSelectedFile();
		
		if(fileToSave == null) return;
		
		
		if(! fileToSave.toString().endsWith("." + SAVE_PROJECT_EXTENSION)) fileToSave = new File(fileToSave + "." + SAVE_PROJECT_EXTENSION);
		ProjectSaver ps = new ProjectSaver(this.sview);
		ps.save(fileToSave);
	}

	private void doOpenProject() {
		/*
		 * open project file (.aoo)
		 */
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle(OPEN_PROJECT);
		FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter(
				SAVE_PROJECT_EXTENSION + " files (*." + SAVE_PROJECT_EXTENSION + ")", SAVE_PROJECT_EXTENSION);
		fc.setDialogTitle("Open project");
		fc.setFileFilter(extensionFilter);
		int result = fc.showOpenDialog(null);
		File fileToOpen = fc.getSelectedFile();
		
		if(result == JFileChooser.CANCEL_OPTION || fileToOpen == null) return;
		
		ProjectOpener po = new ProjectOpener(this.sview);
		po.open(fileToOpen);
	}

	private void doSave() {
		/*
		 * save as image (.png)
		 */
		JFileChooser fc = new JFileChooser();
		fc.showSaveDialog(null);
		File fileToSave = fc.getSelectedFile();
		
		if(fileToSave == null) return;
		
		/*
		 * SAVE
		 */
		ShapesView tmpSView = new ShapesView(this.sview.getModel(), this.sview.getLayersView());
		tmpSView.setSize(this.sview.getSize());
		tmpSView.setBackground(this.sview.getBackground());
		
		BufferedImage bi = new BufferedImage(tmpSView.getWidth(), tmpSView.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2D = bi.createGraphics();
		tmpSView.paint(g2D);
		
		if(! fileToSave.toString().endsWith("." + SAVE_EXTENSION)) fileToSave = new File(fileToSave + "." + SAVE_EXTENSION);
		
		try {
			ImageIO.write(bi, SAVE_EXTENSION, fileToSave);
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			tmpSView = null;
		}
	}

	private void doOpen() {
		/*
		 * open image
		 */
		JFileChooser fc = new JFileChooser();
		int result = fc.showOpenDialog(null);
		File fileToOpen = fc.getSelectedFile();
		
		if(fileToOpen == null || result == JFileChooser.CANCEL_OPTION) return;
		
		try {
			Image img = ImageIO.read(fileToOpen);
			SImage si = new SImage(new Point(0,0), img, fileToOpen);
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
