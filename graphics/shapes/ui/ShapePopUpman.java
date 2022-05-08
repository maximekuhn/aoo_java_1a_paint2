package graphics.shapes.ui;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SImage;
import graphics.shapes.SKotlin;
import graphics.shapes.SRectangle;
import graphics.shapes.SSketch;
import graphics.shapes.SText;
import graphics.shapes.STextBox;
import graphics.shapes.ShapeVisitor;

public class ShapePopUpman implements ShapeVisitor {
	
	private ShapesView sview;
	
	private JFrame popUp;
    private JPanel panel;
    
    private JLabel title;
    private JLabel lWidth;
    private JLabel lHeight;
    private JLabel lRadius;
    private JLabel lText;
    private JLabel lx;
    private JLabel ly;
    
    private JTextField tWidth;
    private JTextField tHeight;
    private JTextField tRadius;
    private JTextArea tText;
    private JTextField tx;
    private JTextField ty;
    
    private JButton bSave;
    private JButton bExit;
    
    private int width;
    private int height;
    private int x;
    private int y;
        
	public ShapePopUpman(ShapesView sview) {
		this.sview = sview;
	}

	@Override
	public void visitRectangle(SRectangle sr) {
		Rectangle r = sr.getBounds();

		creatSettingsFrame("Rectangle Settings", 240, 200);
				
		this.title = new JLabel("Rectangle Settings");
		this.title.setBounds(65, 10, 110, 20);
		this.popUp.getContentPane().add(this.title);
		
		this.tWidth.setText(String.valueOf(r.width));
		this.popUp.getContentPane().add(this.lWidth);
		this.popUp.getContentPane().add(this.tWidth);
		
		this.tHeight.setText(String.valueOf(r.height));
		this.popUp.getContentPane().add(this.lHeight);
		this.popUp.getContentPane().add(this.tHeight);
		
		this.tx.setText(String.valueOf(r.x));
		this.ty.setText(String.valueOf(r.y));
		
		this.bSave.addActionListener(e -> {
			if(e.getSource().equals(this.bSave)) {
	    		this.width = Integer.valueOf(this.tWidth.getText());
	    		this.height = Integer.valueOf(this.tHeight.getText());
	    		this.x = Integer.valueOf(this.tx.getText());
	    		this.y = Integer.valueOf(this.ty.getText());
	    		
	    		r.setBounds(this.x, this.y, this.width, this.height);
	    		sr.setBounds(r);
	    		
	    		this.popUp.dispose();
	    		this.sview.repaint();
			}
		});

	}

	@Override
	public void visitCircle(SCircle sc) {
		Point loc = sc.getLoc();

		creatSettingsFrame("Circle Settings", 240, 200);
				
		this.title = new JLabel("Circle Settings");
		this.title.setBounds(65, 10, 110, 20);
		this.popUp.getContentPane().add(this.title);
		
		this.lRadius = new JLabel("Radius :");
		this.lRadius.setBounds(10, 40, 60, 20);
		this.tRadius = new JTextField(10);
		this.tRadius.setBounds(70, 42, 60, 20);
		this.tRadius.setText(String.valueOf(sc.getRadius()));
		this.popUp.getContentPane().add(this.lRadius);
		this.popUp.getContentPane().add(this.tRadius);
		
		this.tx.setText(String.valueOf(loc.x));
		this.ty.setText(String.valueOf(loc.y));
		
		this.bSave.addActionListener(e -> {
			if(e.getSource().equals(this.bSave)) {
	    		int radius = Integer.valueOf(this.tRadius.getText());
	    		this.x = Integer.valueOf(this.tx.getText());
	    		this.y = Integer.valueOf(this.ty.getText());
	    		
	    		loc.setLocation(this.x, this.y);
	    		sc.setLoc(loc);
	    		sc.setRadius(radius);
	    		
	    		this.popUp.dispose();
	    		this.sview.repaint();
			}
		});
		
	}

	@Override
	public void visitText(SText st) {
		Point loc = st.getLoc();
		
		creatSettingsFrame("Text Settings", 400, 250);
				
		this.title = new JLabel("Text Settings");
		this.title.setBounds(145, 10, 110, 20);
		this.popUp.getContentPane().add(this.title);
		
		this.tText.setText(String.valueOf(st.getText()));
		this.popUp.getContentPane().add(this.lText);
		this.popUp.getContentPane().add(this.tText);
		
		this.lx.setBounds(280, 40, 20, 20);
		this.tx.setBounds(300, 42, 60, 20);
		this.tx.setText(String.valueOf(st.getLoc().x));
		
		this.ly.setBounds(280, 70, 20, 20);
		this.ty.setBounds(300, 72, 60, 20);
		this.ty.setText(String.valueOf(st.getLoc().y));

		this.bSave.setBounds(125, 170, 65, 30);
		this.bExit.setBounds(194, 170, 65, 30);
			
		
		this.bSave.addActionListener(e -> {
			if(e.getSource().equals(this.bSave)) {
				
	    		String text = this.tText.getText();
	    		this.x = Integer.valueOf(this.tx.getText());
	    		this.y = Integer.valueOf(this.ty.getText());
	    		
	    		loc.setLocation(this.x, this.y);
	    		st.setLoc(loc);
	    		st.setText(text);
	    		
	    		this.popUp.dispose();
	    		this.sview.repaint();
			}
		});
			
	}

	@Override
	public void visitCollection(SCollection sc) {
		Point loc = sc.getLoc();
		
		creatSettingsFrame("Collection Settings", 240, 200);
		
		this.title = new JLabel("Collection Settings");
		this.title.setBounds(65, 10, 110, 20);
		this.popUp.getContentPane().add(this.title);
		
		this.tWidth.setText(String.valueOf(sc.getBounds().width));
		this.popUp.getContentPane().add(this.lWidth);
		this.popUp.getContentPane().add(this.tWidth);
		
		this.tHeight.setText(String.valueOf(sc.getBounds().height));
		this.popUp.getContentPane().add(this.lHeight);
		this.popUp.getContentPane().add(this.tHeight);
		
		this.tx.setText(String.valueOf(loc.x));
		this.ty.setText(String.valueOf(loc.y));
		
		this.bSave.addActionListener(e -> {
			if(e.getSource().equals(this.bSave)) {
	    		this.width = Integer.valueOf(this.tWidth.getText());
	    		this.height = Integer.valueOf(this.tHeight.getText());
	    		this.x = Integer.valueOf(this.tx.getText());
	    		this.y = Integer.valueOf(this.ty.getText());
	    		
	    		loc.setLocation(this.x, this.y);
	    		sc.setLoc(loc);
	    		sc.resize(this.width-sc.getBounds().width, this.height-sc.getBounds().height);	    		
	    		
	    		this.popUp.dispose();
	    		this.sview.repaint();
			}
		});
		
	}

	@Override
	public void visitSImage(SImage si) {
		Point loc = si.getLoc();
		
		creatSettingsFrame("Image Settings", 240, 200);
		
		this.title = new JLabel("Image Settings");
		this.title.setBounds(65, 10, 110, 20);
		this.popUp.getContentPane().add(this.title);
		
		this.tWidth.setText(String.valueOf(si.getBounds().width));
		this.popUp.getContentPane().add(this.lWidth);
		this.popUp.getContentPane().add(this.tWidth);
		
		this.tHeight.setText(String.valueOf(si.getBounds().height));
		this.popUp.getContentPane().add(this.lHeight);
		this.popUp.getContentPane().add(this.tHeight);
		
		this.tx.setText(String.valueOf(loc.x));
		this.ty.setText(String.valueOf(loc.y));
		
		this.bSave.addActionListener(e -> {
			if(e.getSource().equals(this.bSave)) {
	    		this.width = Integer.valueOf(this.tWidth.getText());
	    		this.height = Integer.valueOf(this.tHeight.getText());
	    		this.x = Integer.valueOf(this.tx.getText());
	    		this.y = Integer.valueOf(this.ty.getText());
	    		
	    		loc.setLocation(this.x, this.y);
	    		si.setLoc(loc);
	    		si.resize(this.width-si.getBounds().width, this.height-si.getBounds().height);	    		
	    		
	    		this.popUp.dispose();
	    		this.sview.repaint();
			}
		});
	
	}

	@Override
	public void visitSSketch(SSketch sk) {
		Point loc = sk.getLoc();
		
		creatSettingsFrame("Sketch Settings", 240, 200);
		
		this.title = new JLabel("Sketch Settings");
		this.title.setBounds(65, 10, 110, 20);
		this.popUp.getContentPane().add(this.title);
		
		this.tWidth.setText(String.valueOf(sk.getBounds().width));
		this.popUp.getContentPane().add(this.lWidth);
		this.popUp.getContentPane().add(this.tWidth);
		
		this.tHeight.setText(String.valueOf(sk.getBounds().height));
		this.popUp.getContentPane().add(this.lHeight);
		this.popUp.getContentPane().add(this.tHeight);
		
		this.tx.setText(String.valueOf(loc.x));
		this.ty.setText(String.valueOf(loc.y));
		
		this.bSave.addActionListener(e -> {
			if(e.getSource().equals(this.bSave)) {
	    		this.width = Integer.valueOf(this.tWidth.getText());
	    		this.height = Integer.valueOf(this.tHeight.getText());
	    		this.x = Integer.valueOf(this.tx.getText());
	    		this.y = Integer.valueOf(this.ty.getText());
	    		
	    		loc.setLocation(this.x, this.y);
	    		sk.setLoc(loc);
	    		sk.resize(this.width-sk.getBounds().width, this.height-sk.getBounds().height);	
	    		
	    		this.popUp.dispose();
	    		this.sview.repaint();
			}
		});
		
	}
	
	@Override
	public void visitTextBox(STextBox stb) {
		Point loc = stb.getLoc();
		
		creatSettingsFrame("Text Box Settings", 400, 250);
				
		this.title = new JLabel("Text Box Settings");
		this.title.setBounds(145, 10, 110, 20);
		this.popUp.getContentPane().add(this.title);
		
		this.tText.setText(String.valueOf(stb.getText()));
		this.popUp.getContentPane().add(this.lText);
		this.popUp.getContentPane().add(this.tText);
		
		this.lx.setBounds(280, 40, 20, 20);
		this.tx.setBounds(300, 42, 60, 20);
		this.tx.setText(String.valueOf(stb.getLoc().x));
		
		this.ly.setBounds(280, 70, 20, 20);
		this.ty.setBounds(300, 72, 60, 20);
		this.ty.setText(String.valueOf(stb.getLoc().y));

		this.bSave.setBounds(125, 170, 65, 30);
		this.bExit.setBounds(194, 170, 65, 30);
			
		
		this.bSave.addActionListener(e -> {
			if(e.getSource().equals(this.bSave)) {
				
	    		String text = this.tText.getText();
	    		this.x = Integer.valueOf(this.tx.getText());
	    		this.y = Integer.valueOf(this.ty.getText());
	    		
	    		loc.setLocation(this.x, this.y);
	    		stb.setLoc(loc);
	    		stb.setText(text);
	    		
	    		this.popUp.dispose();
	    		this.sview.repaint();
			}
		});
	}
	
	public void creatSettingsFrame(String title, int w, int h) {
        
        if(this.popUp == null) {
        	this.popUp = new JFrame(title);
            this.popUp.setSize(w, h);
            this.popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.popUp.setResizable(false);
            this.popUp.setLocationRelativeTo(null);
            
            this.panel = new JPanel();
            this.panel.setLayout(null);
            this.popUp.setContentPane(this.panel);
        	this.popUp.setVisible(true);
        }
        else System.out.println("ouvert");
		
        this.lWidth = new JLabel("Width :");
		this.lWidth.setBounds(10, 40, 60, 20);
		this.tWidth = new JTextField(10);
		this.tWidth.setBounds(70, 42, 60, 20);
		
        this.lHeight = new JLabel("Height :");
		this.lHeight.setBounds(10, 70, 60, 20);
		this.tHeight = new JTextField(10);
		this.tHeight.setBounds(70, 72, 60, 20);
		
		this.lText = new JLabel("Text :");
		this.lText.setBounds(10, 40, 60, 20);
		this.tText = new JTextArea();
		this.tText.setBounds(61, 42, 200, 100);
        
		this.lx = new JLabel("x :");
		this.lx.setBounds(140, 40, 20, 20);
		this.tx = new JTextField(10);
		this.tx.setBounds(160, 42, 60, 20);
		this.popUp.getContentPane().add(this.lx);
		this.popUp.getContentPane().add(this.tx);
		
		this.ly = new JLabel("y :");
		this.ly.setBounds(140, 70, 20, 20);
		this.ty = new JTextField(10);
		this.ty.setBounds(160, 72, 60, 20);
		this.popUp.getContentPane().add(this.ly);
		this.popUp.getContentPane().add(this.ty);
		
		this.bSave = new JButton("Save");
		this.bSave.setBounds(45, 120, 65, 30);
		this.bExit = new JButton ("Exit");
		this.bExit.setBounds(114, 120, 65, 30);
		this.popUp.getContentPane().add(this.bSave);
		this.popUp.getContentPane().add(this.bExit);
		
		this.bExit.addActionListener(e -> {
			if(e.getSource().equals(this.bExit)) {
				this.popUp.dispose();
			}
		});
          
    }

	@Override
	public void visitSKotlin(SKotlin sk) {
		// TODO Auto-generated method stub
		
	}

}
