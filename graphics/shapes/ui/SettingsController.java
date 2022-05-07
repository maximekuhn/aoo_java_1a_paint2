package graphics.shapes.ui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.ui.toolbar.SettingsActions;
import graphics.ui.Controller;

public class SettingsController extends Controller {
	
	
	private ShapePopUpman popUpman;
	private Point lastClick;
	
	private SettingsActions actionMode = SettingsActions.PAINT;

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
		
		if (this.actionMode.equals(SettingsActions.SETTINGS)) this.doSettings(e);
	}

	private void doSettings(MouseEvent e) {
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

}
