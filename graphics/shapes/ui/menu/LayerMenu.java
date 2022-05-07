package graphics.shapes.ui.menu;

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
import graphics.shapes.attributes.LayerAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesView;

public class LayerMenu extends JMenu implements ActionListener {

	private static final String LAYER = "Layer";
	private static final String INCREASE_LAYER = "Increase layer";
	private static final String DECREASE_LAYER = "Decrease layer";
	private static final String RESET_LAYERS = "Reset layers";
	private static final String LOWEST_LAYER = "Lowest layer";
	private static final String HIGHEST_LAYER = "Highest layer";
	
	private JMenuItem increaseLayer;
	private JMenuItem decreaseLayer;
	private JMenuItem resetLayers;
	private JMenuItem lowestLayer;
	private JMenuItem highestLayer;
	
	private ShapesView sview;
	
	public LayerMenu(ShapesView sview) {
		super(LAYER);
		this.sview = sview;
		this.buildMenu();
	}
	
	private void buildMenu() {
		this.increaseLayer = new JMenuItem(INCREASE_LAYER);
		this.decreaseLayer = new JMenuItem(DECREASE_LAYER);
		this.resetLayers = new JMenuItem(RESET_LAYERS);
		this.lowestLayer = new JMenuItem(LOWEST_LAYER);
		this.highestLayer = new JMenuItem(HIGHEST_LAYER);
		
		this.increaseLayer.addActionListener(this);
		this.decreaseLayer.addActionListener(this);
		this.resetLayers.addActionListener(this);
		this.lowestLayer.addActionListener(this);
		this.highestLayer.addActionListener(this);
		
		// TODO : keyboard shortcuts (layers)
		this.increaseLayer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_UP, KeyEvent.SHIFT_DOWN_MASK));
		this.decreaseLayer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, KeyEvent.SHIFT_DOWN_MASK));
		
		this.add(this.increaseLayer);
		this.add(this.decreaseLayer);
		this.addSeparator();
		this.add(this.lowestLayer);
		this.add(this.highestLayer);
		this.addSeparator();
		this.add(this.resetLayers);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.increaseLayer)) this.doIncreaseLayer();
		else if(e.getSource().equals(this.decreaseLayer)) this.doDecraseLayer();
		else if(e.getSource().equals(this.resetLayers)) this.doResetLayers();
		else if(e.getSource().equals(this.lowestLayer)) this.doLowestLayer();
		else if(e.getSource().equals(this.highestLayer)) this.doHighestLayer();
	}

	private void doHighestLayer() {
		/*
		 * move selected shapes to highest layer
		 */
		SCollection model = (SCollection) this.sview.getModel();
		int highestLayer = model.getLayerMax();
		
		LinkedList<Shape> shapes = this.getSelectedShapes();
		for(Shape shape : shapes) {
			LayerAttributes la = (LayerAttributes) shape.getAttributes(LayerAttributes.ID);
			if(la == null) {
				la = new LayerAttributes();
				shape.addAttributes(la);
			}
			la.setLayer(highestLayer);
		}
		
		this.sview.repaint();
	}

	private void doLowestLayer() {
		/*
		 * move selected shapes to lowest layer
		 */
		SCollection model = (SCollection) this.sview.getModel();
		int lowestLayer = model.getLayerMin();
		
		LinkedList<Shape> shapes = this.getSelectedShapes();
		for(Shape shape : shapes) {
			LayerAttributes la = (LayerAttributes) shape.getAttributes(LayerAttributes.ID);
			if(la == null) {
				la = new LayerAttributes();
				shape.addAttributes(la);
			}
			la.setLayer(lowestLayer);
		}
		
		this.sview.repaint();
	}

	private void doResetLayers() {
		/*
		 * reset all layer (set layer to 0)
		 */
		SCollection model = (SCollection) this.sview.getModel();
		Iterator<Shape> iterator = model.iterator();
		
		Shape s;
		while(iterator.hasNext()) {
			s = iterator.next();
			LayerAttributes la = (LayerAttributes) s.getAttributes(LayerAttributes.ID);
			if(la == null) {
				la = new LayerAttributes();
				s.addAttributes(la);
			}
			la.setLayer(0);
		}
		
		this.sview.repaint();
	}

	private void doDecraseLayer() {
		/*
		 * layer-- for all selected shapes
		 */
		LinkedList<Shape> shapes = this.getSelectedShapes();
		if(shapes.size() == 0) return;
		for(Shape s : shapes) {
			LayerAttributes la = (LayerAttributes) s.getAttributes(LayerAttributes.ID);
			if(la == null) {
				la = new LayerAttributes();
				s.addAttributes(la);
			}
			la.decrementLayer();
		}
		this.sview.repaint();
		
	}

	private void doIncreaseLayer() {
		/*
		 * layer++ for all selected shapes
		 */
		LinkedList<Shape> shapes = this.getSelectedShapes();
		if(shapes.size() == 0) return;
		for(Shape s : shapes) {
			LayerAttributes la = (LayerAttributes) s.getAttributes(LayerAttributes.ID);
			if(la == null) {
				la = new LayerAttributes();
				s.addAttributes(la);
			}
			la.incrementLayer();
		}
		this.sview.repaint();
	}
	
	private LinkedList<Shape> getSelectedShapes() {
		LinkedList<Shape> shapes = new LinkedList<>();
		SCollection model =(SCollection) this.sview.getModel();
		Iterator<Shape> it = model.iterator();
		
		Shape s;
		while(it.hasNext()) {
			s = it.next();
			SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			if(sa == null) sa = new SelectionAttributes();
			
			if(sa.isSelected()) shapes.add(s);
		}
		return shapes;
	}

}
