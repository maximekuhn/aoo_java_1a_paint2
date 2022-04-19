package graphics.shapes.ui.toolbar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import graphics.shapes.ui.ShapesView;

public class StrokeColorTools extends ToolContainer implements ActionListener {
	
	private JButton whiteButton;
	private JButton blackButton;
	private JButton grayButton;
	private JButton redButton;
	private JButton orangeButton;
	private JButton yellowButton;
	private JButton greenButton;
	private JButton cyanButton;
	private JButton blueButton;
	private JButton magentaButton;
	
	public StrokeColorTools(ShapesView sview) {
		super(sview);
	}

	@Override
	protected void buildButtons() {
		this.whiteButton = new JButton("");
		this.blackButton = new JButton("");
		this.grayButton = new JButton("");
		this.redButton = new JButton("");
		this.orangeButton = new JButton("");
		this.yellowButton = new JButton("");
		this.greenButton = new JButton("");
		this.cyanButton = new JButton("");
		this.blueButton = new JButton("");
		this.magentaButton = new JButton("");
		setColor();
	}
	
	public void setColor() {
		this.whiteButton.setBackground(Color.white);
		this.blackButton.setBackground(Color.black);
		this.grayButton.setBackground(Color.gray);
		this.redButton.setBackground(Color.red);
		this.orangeButton.setBackground(Color.orange);
		this.yellowButton.setBackground(Color.yellow);
		this.greenButton.setBackground(Color.green);
		this.cyanButton.setBackground(Color.cyan);
		this.blueButton.setBackground(Color.blue);
		this.magentaButton.setBackground(Color.magenta);	
	}

	@Override
	protected void addAllButtons() {
		this.addButton(this.whiteButton);
		this.addButton(this.blackButton);
		this.addButton(this.grayButton);
		this.addButton(this.redButton);
		this.addButton(this.orangeButton);
		this.addButton(this.yellowButton);
		this.addButton(this.greenButton);
		this.addButton(this.cyanButton);
		this.addButton(this.blueButton);
		this.addButton(this.magentaButton);
	}

	@Override
	protected void addActionListener() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
