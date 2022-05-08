package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.ui.toolbar.SettingsActions;
import graphics.ui.Controller;

public class SettingsController extends Controller {
	
	
	private ShapePopUpman popUpman;
	private Point lastClick;
	
	private SettingsActions actionMode = SettingsActions.PAINT;
	
	public Color c;

	public SettingsController(Object newModel) {
		super(newModel);
		this.lastClick = new Point();
	}
	
	public void setActionMode(SettingsActions action) {
		this.actionMode = action;
	}
	
	public SettingsActions getActionMode() {
		return this.actionMode;
	}
	
	@Override
	public void mouseClicked(MouseEvent e){
		
		this.lastClick.setLocation(e.getPoint());
		
		if (this.actionMode.equals(SettingsActions.SETTINGS)) this.doSettings();
		else if(this.actionMode.equals(SettingsActions.PAINT)) this.doPaint(e);
		
	}


	private void doPaint(MouseEvent e) {
		Shape s = this.getTarget();
		if(s==null) return;
		
		ColorAttributes ca = (ColorAttributes) s.getAttributes(ColorAttributes.ID);
		if(ca == null) {
			ca = new ColorAttributes();
			s.addAttributes(ca);
		}
		
		if(SwingUtilities.isLeftMouseButton(e)) {
			ca.filled = true;
			ca.filledColor = c;
		}
		else if(SwingUtilities.isRightMouseButton(e)) {
			ca.stroked = true;
			ca.strokedColor = c;
		}
		
		this.getView().repaint();
	}

	private void doSettings() {
		this.popUpman = new ShapePopUpman((ShapesView) this.getView());
		Shape s = this.getTarget();
		if(s==null) return ;
		s.accept(this.popUpman);
	}
	
	private Shape getTarget() {
		SCollection model = (SCollection) this.getModel();
		Iterator<Shape> it = model.iterator();
		
		while(it.hasNext()) {
			Shape s = it.next();
			if(s.getBounds().contains(lastClick))
				return s;
		}
		
		return null;
	}
	
	public void chooseColor() {
	    //this.color = JColorChooser.showDialog(null, "Pick your color", new ColorAttributes().filledColor);
		
		JColorChooser colorChooser = new JColorChooser();
	    
	    //remove preview panel
	    colorChooser.setPreviewPanel(new JPanel());
	    
	    //remove chooser panel 
	    colorChooser.removeChooserPanel(colorChooser.getChooserPanels()[4]); //CMYK
	    //colorChooser.removeChooserPanel(colorChooser.getChooserPanels()[3]); //RGB
	    colorChooser.removeChooserPanel(colorChooser.getChooserPanels()[2]); //HSL
	    colorChooser.removeChooserPanel(colorChooser.getChooserPanels()[1]); //HSV
	    colorChooser.removeChooserPanel(colorChooser.getChooserPanels()[0]); //Swatch
	    
	    ActionListener okActionListener = new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
		       c = colorChooser.getColor();
			}
	    };

	    JDialog d = colorChooser.createDialog(null, "Pick your color", true, colorChooser, okActionListener, null);
	    
	    d.setVisible(true);
	}
	
 

}
