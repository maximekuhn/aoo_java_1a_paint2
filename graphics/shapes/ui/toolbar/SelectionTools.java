package graphics.shapes.ui.toolbar;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import graphics.shapes.ui.MainController;
import graphics.shapes.ui.SelectionController;
import graphics.shapes.ui.ShapesView;

public class SelectionTools extends ToolContainer implements ActionListener {
	
	private ToolBar tb;
	
	private JButton selectionButton;
	private JButton translateButton;
	private JButton rotateButton;
	private JButton eraseButton;
	private JButton groupButton;
	private JButton ungroupButton;
	
	private SelectionController controller;
	private MainController mainController;
	
	public SelectionTools(ShapesView sview, ToolBar tb) {
		super(sview);
		this.tb = tb;
		this.controller = new SelectionController(sview.getModel());
		this.controller.setView(sview);
		this.mainController = (MainController) this.sview.getController();
		this.mainController.addController(this.controller);
	}
	
	@Override
	protected void buildButtons() {
		this.selectionButton = new JButton(imageSize("src/pictures/cursor.png"));
		this.translateButton = new JButton(imageSize("src/pictures/move.png"));
		this.rotateButton = new JButton(imageSize("src/pictures/rotate.png"));
		this.eraseButton = new JButton(imageSize("src/pictures/eraser.png"));
		this.groupButton = new JButton(imageSize("src/pictures/group.png"));
		this.ungroupButton = new JButton(imageSize("src/pictures/ungroup.png"));
	}
	
	@Override
	protected void addAllButtons() {
		this.addButton(this.selectionButton);
		this.addButton(this.translateButton);
		this.addButton(this.rotateButton);
		this.addButton(this.eraseButton);
		this.addButton(this.groupButton);
		this.addButton(this.ungroupButton);
	}
	
	@Override
	protected void addActionListener() {
		for(JButton b : this.getButtons())
			b.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.mainController.setController(this.controller);
		if(e.getSource().equals(this.selectionButton)) this.doSelect();
		else if(e.getSource().equals(this.translateButton)) this.doTranslate();
		else if(e.getSource().equals(this.rotateButton)) this.doRotate();
		else if(e.getSource().equals(this.eraseButton)) this.doErase();
		else if(e.getSource().equals(this.groupButton)) this.doGroup();
		else if(e.getSource().equals(this.ungroupButton)) this.doUnGroup();
	}
	
	private void doUnGroup() {
		SelectionActions old = this.controller.getActionMode();
		this.controller.setActionMode(SelectionActions.UNGROUP);
		this.controller.doUngroup();
		this.controller.setActionMode(old);
	}

	private void doGroup() {
		SelectionActions oldAction = this.controller.getActionMode();
		this.controller.setActionMode(SelectionActions.GROUP);
		this.controller.doGroup();
		this.controller.setActionMode(oldAction);
	}

	private void doErase() {
		this.tb.highlightButton(this.eraseButton);
		
		this.controller.setActionMode(SelectionActions.ERASE);
		
		Image cursorImage = this.cursorSize("src/pictures/cursors/eraser_cursor.png");
		if(cursorImage != null) {
			Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "customCursor");
			this.getView().setCursor(customCursor);
		}
	}

	private void doRotate() {
		SelectionActions oldAction = this.controller.getActionMode();
		this.controller.setActionMode(SelectionActions.ROTATE);
		this.controller.doRotation();
		this.controller.setActionMode(oldAction);
	}

	private void doTranslate() {
		this.tb.highlightButton(this.translateButton);
		
		this.controller.setActionMode(SelectionActions.TRANSLATE);
		
		Image cursorImage = this.cursorSize("src/pictures/cursors/move_cursor.png");
		if(cursorImage != null) {
			Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "customCursor");
			this.getView().setCursor(customCursor);
		}
	}

	private void doSelect() {
		this.tb.highlightButton(this.selectionButton);
		
		this.controller.setActionMode(SelectionActions.SELECT);
		
		this.getView().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

}
