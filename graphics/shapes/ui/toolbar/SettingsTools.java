package graphics.shapes.ui.toolbar;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import graphics.shapes.ui.MainController;
import graphics.shapes.ui.SettingsController;
import graphics.shapes.ui.ShapesView;

public class SettingsTools extends ToolContainer implements ActionListener {

	private ToolBar tb;
	
	private JButton paintBucketButton;//clic G = peindre background clic D = peindre contour
	private JButton chooseColorButton;//changer la couleur du paintbucket
	private JButton shapesSettingsButton;//popup de reglage

	private SettingsController controller;
	private MainController mainController;
	
	public SettingsTools(ShapesView sview, ToolBar tb) {
		super(sview);
		this.tb = tb;
		this.controller = new SettingsController(sview.getModel());
		this.controller.setView(sview);
		this.mainController = (MainController) this.sview.getController();
		this.mainController.addController(this.controller);
	}
	
	@Override
	protected void buildButtons() {
		this.paintBucketButton = new JButton(imageSize("src/pictures/paintbucket.png"));
		
		this.chooseColorButton = new JButton(imageSize("src/pictures/chromatic.png"));
		
		this.shapesSettingsButton = new JButton(imageSize("src/pictures/settings.png"));
	}
	
	@Override
	protected void addAllButtons() {
		this.addButton(this.paintBucketButton);
		this.addButton(this.chooseColorButton);
		this.addButton(this.shapesSettingsButton);
	}	

	@Override
	protected void addActionListener() {
		for(JButton b : this.getButtons())
			b.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.mainController.setController(this.controller);
		if(e.getSource().equals(this.shapesSettingsButton)) this.doSettings();
	}

	private void doSettings() {
		this.tb.highlightButton(this.shapesSettingsButton);
		this.controller.setActionMode(SettingsActions.SETTINGS);
		
		Image cursorImage = cursorSize("src/pictures/settings.png");
		if(cursorImage != null) {
			Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "customCursor");
			this.getView().setCursor(customCursor);
		}
	}

}
