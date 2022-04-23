package graphics.shapes.ui.menu;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesView;

public class SelectionMenu extends JMenu implements ActionListener {

	private static final String SELECTION = "Selection";
	private static final String SELECT_ALL = "Select all";
	private static final String UNSELECT_ALL = "Unselect all";
	private static final String INVERT = "Invert";
	private static final String DELETE = "Delete";
	private static final String DUPLICATE = "Duplicate";
	
	private JMenuItem selectAll;
	private JMenuItem unselectAll;
	private JMenuItem invert;
	private JMenuItem delete;
	private JMenuItem duplicate;
	
	private ShapesView sview;
	
	public SelectionMenu(ShapesView sview) {
		super(SELECTION);
		this.sview = sview;
		this.buildMenu();
	}
	
	private void buildMenu() {
		this.selectAll = new JMenuItem(SELECT_ALL);
		this.unselectAll = new JMenuItem(UNSELECT_ALL);
		this.invert = new JMenuItem(INVERT);
		this.delete = new JMenuItem(DELETE);
		this.duplicate = new JMenuItem(DUPLICATE);
		
		this.selectAll.addActionListener(this);
		this.unselectAll.addActionListener(this);
		this.invert.addActionListener(this);
		this.delete.addActionListener(this);
		this.duplicate.addActionListener(this);
		
		// keyboard shortcuts
		this.selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
		this.unselectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_DOWN_MASK));
		this.invert.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
		this.delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		this.duplicate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
		
		this.add(this.selectAll);
		this.add(this.unselectAll);
		this.add(this.invert);
		this.addSeparator();
		this.add(this.delete);
		this.add(this.duplicate);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.selectAll)) this.doSelectAll();
		else if(e.getSource().equals(this.unselectAll)) this.doUnselectAll();
		else if(e.getSource().equals(this.invert)) this.doInvert();
		else if(e.getSource().equals(this.delete)) this.doDelete();
		else if(e.getSource().equals(this.duplicate)) this.doDuplicate();
	}
	
	private void doSelectAll() {
		SCollection model = (SCollection) this.sview.getModel();
		Iterator<Shape> it = model.iterator();
		Shape s;
		while(it.hasNext()) {
			s = it.next();
			SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			sa.select();
		}
		this.sview.repaint();
	}
	
	private void doUnselectAll() {
		SCollection model = (SCollection) this.sview.getModel();
		Iterator<Shape> it = model.iterator();
		Shape s;
		while(it.hasNext()) {
			s = it.next();
			SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			sa.unselect();
		}
		this.sview.repaint();
	}
	
	private void doInvert() {
		SCollection model = (SCollection) this.sview.getModel();
		Iterator<Shape> it = model.iterator();
		Shape s;
		while(it.hasNext()) {
			s = it.next();
			SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			sa.toggleSelection();
		}
		this.sview.repaint();
	}
	
	private void doDelete() {
		SCollection model = (SCollection) this.sview.getModel();
		Iterator<Shape> it = model.iterator();
		
		/*
		 * removable shapes need to be stored somewhere,
		 * otherwise there is a confusion when calling it.next() if we remove
		 * the shape in the while loop !
		 */
		LinkedList<Shape> shapesToRemove = new LinkedList<>();
		Shape s;
		while(it.hasNext()) {
			s = it.next();
			SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			if(sa.isSelected()) shapesToRemove.add(s);
		}
		
		for(Shape s1 : shapesToRemove)
			model.remove(s1);
		
		this.sview.repaint();
	}
	
	private void doDuplicate() {
		SCollection model = (SCollection) this.sview.getModel();
		Iterator<Shape> it = model.iterator();
		
		/*
		 * duplicate shapes need to be stored somewhere,
		 * otherwise there is a confusion when calling it.next() if we add
		 * the shape to the model inside the while loop.
		 */
		LinkedList<Shape> shapesToDuplicate = new LinkedList<>();
		Shape s;
		while(it.hasNext()) {
			s = it.next();
			SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			if(sa.isSelected()) shapesToDuplicate.add(s.copy());
		}
		
		for(Shape std : shapesToDuplicate) {
			/*
			 * make std appears top left (x = 10, y = 10)
			 * unselect std
			 */
			SelectionAttributes sa = (SelectionAttributes) std.getAttributes(SelectionAttributes.ID);
			if(sa == null) sa = new SelectionAttributes();
			sa.unselect();
			std.setLoc(new Point(10,10));
			model.add(std);
		}
		
		this.sview.repaint();
	}

}
