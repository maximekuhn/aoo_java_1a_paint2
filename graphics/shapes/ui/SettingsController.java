package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.ui.toolbar.SettingsActions;
import graphics.ui.Controller;

public class SettingsController extends Controller implements ActionListener {
	
	
	private ShapePopUpman popUpman;
	private Point lastClick;
	
	private SettingsActions actionMode = SettingsActions.PAINT;
	
	private JColorChooser colorChooser;

	
	private Color color;

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
			ca.filledColor = this.color;
		}
		else if(SwingUtilities.isRightMouseButton(e)) {
			ca.stroked = true;
			ca.strokedColor = this.color;
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
		
		this.colorChooser = new JColorChooser();
	    
	    //remove preview panel
		this.colorChooser.setPreviewPanel(new JPanel());
	    
	    //remove chooser panel 
		this.colorChooser.removeChooserPanel(this.colorChooser.getChooserPanels()[4]); //CMYK
	    //this.colorChooser.removeChooserPanel(this.colorChooser.getChooserPanels()[3]); //RGB
		this.colorChooser.removeChooserPanel(this.colorChooser.getChooserPanels()[2]); //HSL
		this.colorChooser.removeChooserPanel(this.colorChooser.getChooserPanels()[1]); //HSV
		this.colorChooser.removeChooserPanel(this.colorChooser.getChooserPanels()[0]); //Swatch
		
	    JDialog d = JColorChooser.createDialog(null, "Pick your color", true, this.colorChooser, this, null);
	    
	    d.setVisible(true);
	}
	
	public Color getColorChooser() {
		return this.color;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 if(this.actionMode == SettingsActions.CHOOSECOLOR) this.color = this.colorChooser.getColor();
	}

}
