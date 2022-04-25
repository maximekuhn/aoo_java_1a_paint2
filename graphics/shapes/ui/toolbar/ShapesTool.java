package graphics.shapes.ui.toolbar;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


import graphics.shapes.ui.ShapesView;

public class ShapesTool extends ToolContainer implements ActionListener {
	
	private JButton squareButton;
	private JButton circleButton;
	private JButton triangleButton;
	private JButton lineButton;
	private JButton arrowButton;	
	private JButton letterButton;
	private JButton pencilButton;

	public ShapesTool(ShapesView sview) {
		super(sview);
	}

	@Override
	protected void buildButtons() {
		this.squareButton = new JButton(imageSize("src/pictures/square.png"));
		this.circleButton = new JButton(imageSize("src/pictures/circle.png"));
		this.triangleButton = new JButton(imageSize("src/pictures/triangle.png"));
		this.lineButton = new JButton(imageSize("src/pictures/line.png"));
		this.arrowButton = new JButton(imageSize("src/pictures/arrow.png"));
		this.letterButton = new JButton(imageSize("src/pictures/letter.png"));
		this.pencilButton = new JButton(imageSize("src/pictures/pencil.png"));
	}

	@Override
	protected void addAllButtons() {
		this.addButton(this.squareButton);
		this.addButton(this.circleButton);
		this.addButton(this.triangleButton);
		this.addButton(this.lineButton);
		this.addButton(this.arrowButton);
		this.addButton(this.letterButton);
		this.addButton(this.pencilButton);		
	}

	@Override
	protected void addActionListener() {
		for(JButton b : this.getButtons())
			b.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.squareButton))
			System.out.println("square button pressed");
	}
	
}
