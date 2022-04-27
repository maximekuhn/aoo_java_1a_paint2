package graphics.shapes.ui.toolbar;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import graphics.shapes.ui.SelectionController;
import graphics.shapes.ui.ShapesView;

public class SelectionTools extends ToolContainer implements ActionListener {

	private JButton selectionButton;
	private JButton translateButton;
	private JButton rotateButton;
	private JButton eraseButton;
	private JButton groupButton;
	
	private SelectionController controller;
	
	public SelectionTools(ShapesView sview) {
		super(sview);
		this.controller = new SelectionController(this.sview);
		//this.controller = (SelectionController) sview.getController();
	}
	
	@Override
	protected void buildButtons() {
		this.selectionButton = new JButton(imageSize("src/pictures/cursor.png"));
		this.translateButton = new JButton(imageSize("src/pictures/move.png"));
		this.rotateButton = new JButton(imageSize("src/pictures/rotate.png"));
		this.eraseButton = new JButton(imageSize("src/pictures/eraser.png"));
		this.groupButton = new JButton(imageSize("src/pictures/group.png"));
	}
	
	@Override
	protected void addAllButtons() {
		this.addButton(this.selectionButton);
		this.addButton(this.translateButton);
		this.addButton(this.rotateButton);
		this.addButton(this.eraseButton);
		this.addButton(this.groupButton);
	}
	
	@Override
	protected void addActionListener() {
		for(JButton b : this.getButtons())
			b.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.selectionButton)) this.doSelect();
		else if(e.getSource().equals(this.translateButton)) this.doTranslate();
		else if(e.getSource().equals(this.rotateButton)) this.doRotate();
		else if(e.getSource().equals(this.eraseButton)) this.doErase();
		else if(e.getSource().equals(this.groupButton)) this.doGroup();
	}

	private void doGroup() {
		this.controller.setActionMode(SelectionActions.GROUP);
	}

	private void doErase() {
		this.controller.setActionMode(SelectionActions.ERASE);
		
		Image cursorImage = this.cursorSize("src/pictures/cursors/eraser_cursor.png");
		if(cursorImage != null) {
			Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "customCursor");
			this.getView().setCursor(customCursor);
		}
	}

	private void doRotate() {
		this.controller.setActionMode(SelectionActions.ROTATE);
		this.controller.doRotation();
	}

	private void doTranslate() {
		this.controller.setActionMode(SelectionActions.TRANSLATE);
		
		Image cursorImage = this.cursorSize("src/pictures/cursors/move_cursor.png");
		if(cursorImage != null) {
			Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "customCursor");
			this.getView().setCursor(customCursor);
		}
	}

	private void doSelect() {
		this.controller.setActionMode(SelectionActions.SELECT);
		
		this.getView().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

}
