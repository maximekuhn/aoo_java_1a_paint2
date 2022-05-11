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
	private JButton resizeButton;
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
		this.selectionButton.setToolTipText("Select shape(s)");
		
		this.translateButton = new JButton(imageSize("src/pictures/move.png"));
		this.translateButton.setToolTipText("Translate selected shape(s)");

		this.resizeButton = new JButton(imageSize("src/pictures/resize.png"));
		this.resizeButton.setToolTipText("Resize selected shape(s)");
		
		this.rotateButton = new JButton(imageSize("src/pictures/rotate.png"));
		this.rotateButton.setToolTipText("Rotate selected shape(s) BETA");
		
		this.eraseButton = new JButton(imageSize("src/pictures/eraser.png"));
		this.eraseButton.setToolTipText("Erase shape");
		
		this.groupButton = new JButton(imageSize("src/pictures/group.png"));
		this.groupButton.setToolTipText("Group selected shapes");
		
		this.ungroupButton = new JButton(imageSize("src/pictures/ungroup.png"));
		this.ungroupButton.setToolTipText("Ungroup selected group");
	}
	
	@Override
	protected void addAllButtons() {
		this.addButton(this.selectionButton);
		this.addButton(this.translateButton);
		this.addButton(this.resizeButton);
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
		else if(e.getSource().equals(this.resizeButton)) this.doResize();
		else if(e.getSource().equals(this.rotateButton)) this.doRotate();
		else if(e.getSource().equals(this.eraseButton)) this.doErase();
		else if(e.getSource().equals(this.groupButton)) this.doGroup();
		else if(e.getSource().equals(this.ungroupButton)) this.doUnGroup();
	}
	
	private void doUnGroup() {
		this.tb.highlightButton(this.ungroupButton);
		
		this.controller.setActionMode(SelectionActions.UNGROUP);
		this.controller.doUngroup();
		
		this.getView().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	private void doGroup() {
		this.tb.highlightButton(this.groupButton);
		
		this.controller.setActionMode(SelectionActions.GROUP);
		this.controller.doGroup();
		
		this.getView().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	private void doErase() {
		this.tb.highlightButton(this.eraseButton);
		
		this.controller.setActionMode(SelectionActions.ERASE);
		
		Image cursorImage = ToolContainer.cursorSize("src/pictures/cursors/eraser_cursor.png");
		if(cursorImage != null) {
			Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "customCursor");
			this.getView().setCursor(customCursor);
		}
	}

	private void doRotate() {
		this.tb.highlightButton(this.rotateButton);
		
		this.controller.setActionMode(SelectionActions.ROTATE);
		this.controller.doRotation();
		
		this.getView().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	private void doResize() {
		this.tb.highlightButton(this.resizeButton);

		this.controller.setActionMode(SelectionActions.RESIZE);

		this.getView().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	private void doTranslate() {
		this.tb.highlightButton(this.translateButton);
		
		this.controller.setActionMode(SelectionActions.TRANSLATE);
		
		Image cursorImage = ToolContainer.cursorSize("src/pictures/cursors/move_cursor.png");
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
