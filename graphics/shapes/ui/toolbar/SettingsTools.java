package graphics.shapes.ui.toolbar;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.ui.MainController;
import graphics.shapes.ui.SettingsController;
import graphics.shapes.ui.ShapesView;

public class SettingsTools extends ToolContainer implements ActionListener {

	private ToolBar tb;
	
	private JButton paintBucketButton;
	private JButton chooseColorButton;
	private JButton shapesSettingsButton;

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
		this.paintBucketButton.setToolTipText("Left click to fill / Right click for strokes");
		
		this.chooseColorButton = new JButton();
		this.chooseColorButton.setToolTipText("Choose color");
		this.setChooseColorButtonIcon(new ColorAttributes().filledColor);
		
		this.shapesSettingsButton = new JButton(imageSize("src/pictures/settings.png"));
		this.shapesSettingsButton.setToolTipText("Click on a shape for more settings");
	}
	
	public void setChooseColorButtonIcon(Color color) {
		int width = this.paintBucketButton.getIcon().getIconWidth();
		int height = this.paintBucketButton.getIcon().getIconHeight();
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for(int x = 0; x < bi.getWidth(); x++) {
			for(int y = 0; y < bi.getHeight(); y++) {
				bi.setRGB(x, y, color.getRGB());
			}
		}
		
		this.chooseColorButton.setIcon((new ImageIcon(bi)));	
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
		else if(e.getSource().equals(this.chooseColorButton)) this.doChooseColor();
		else if(e.getSource().equals(this.paintBucketButton)) this.doPaint();
	}

	private void doPaint() {
		this.tb.highlightButton(this.paintBucketButton);
		this.controller.setActionMode(SettingsActions.PAINT);
		
		Image cursorImage = cursorSize("src/pictures/cursors/paintbucket_cursor.png");
		if(cursorImage != null) {
			Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "customCursor");
			this.getView().setCursor(customCursor);
		}
		
	}

	private void doChooseColor() {
		this.tb.highlightButton(this.chooseColorButton);
		this.controller.setActionMode(SettingsActions.CHOOSECOLOR);
		this.controller.chooseColor();
		
		this.setChooseColorButtonIcon(this.controller.getColor());
		this.doPaint();
	}

	private void doSettings() {
		this.tb.highlightButton(this.shapesSettingsButton);
		this.controller.setActionMode(SettingsActions.SETTINGS);
		
		Image cursorImage = cursorSize("src/pictures/cursors/settings_cursor.png");
		if(cursorImage != null) {
			Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "customCursor");
			this.getView().setCursor(customCursor);
		}
	}
	
}
