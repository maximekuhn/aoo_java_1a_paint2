package graphics.shapes.ui;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import graphics.shapes.SArrow;
import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SCross;
import graphics.shapes.SEllipse;
import graphics.shapes.SHexagon;
import graphics.shapes.SImage;
import graphics.shapes.SKotlin;
import graphics.shapes.SLine;
import graphics.shapes.SOctagon;
import graphics.shapes.SParallelogram;
import graphics.shapes.SPentagon;
import graphics.shapes.SRectangle;
import graphics.shapes.SSketch;
import graphics.shapes.SStar;
import graphics.shapes.SText;
import graphics.shapes.STextBox;
import graphics.shapes.STrapezium;
import graphics.shapes.STriangle;
import graphics.shapes.STriangleRec;
import graphics.shapes.STriangleScale;
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
    
    private JScrollPane scrolltxt;
    
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
		
		addSizeSettings(10, 40, 60, 20);
		this.tWidth.setText(String.valueOf(r.width));		
		this.tHeight.setText(String.valueOf(r.height));		
		
		addLocSettings(140, 40, 20, 20);
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
		
		this.lRadius = new JLabel("Radius :");
		this.lRadius.setBounds(10, 40, 60, 20);
		this.tRadius = new JTextField(10);
		this.tRadius.setBounds(70, 42, 60, 20);
		this.tRadius.setText(String.valueOf(sc.getRadius()));
		this.popUp.add(this.lRadius);
		this.popUp.add(this.tRadius);
		
		addLocSettings(140, 40, 20, 20);
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
		this.title.setBounds(145, 10, 110, 20);
		
		addTextSettings(10, 40, 60, 20);
		this.tText.setText(String.valueOf(st.getText()));
		
		addLocSettings(280, 40, 20, 20);
		this.tx.setText(String.valueOf(st.getLoc().x));
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
		
		addSizeSettings(10, 40, 60, 20);
		this.tWidth.setText(String.valueOf(sc.getBounds().width));
		this.tHeight.setText(String.valueOf(sc.getBounds().height));
		
		addLocSettings(140, 40, 20, 20);
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

		addSizeSettings(10, 40, 60, 20);
		this.tWidth.setText(String.valueOf(si.getBounds().width));		
		this.tHeight.setText(String.valueOf(si.getBounds().height));
		
		addLocSettings(140, 40, 20, 20);
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
		
		addSizeSettings(10, 40, 60, 20);
		this.tWidth.setText(String.valueOf(sk.getBounds().width));
		this.tHeight.setText(String.valueOf(sk.getBounds().height));
		
		addLocSettings(140, 40, 20, 20);
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
		this.title.setBounds(145, 10, 110, 20);
		
		addTextSettings(10, 40, 60, 20);
		this.tText.setText(String.valueOf(stb.getText()));
		
		addLocSettings(280, 40, 20, 20);
		this.tx.setText(String.valueOf(stb.getLoc().x));
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

	@Override
	public void visitSKotlin(SKotlin sk) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSTriangle(STriangle st) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSLine(SLine sl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSHexagon(SHexagon sh) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSTriangleRec(STriangleRec strec) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSCross(SCross scr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitEllipse(SEllipse se) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSPentagon(SPentagon spt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSArrow(SArrow sar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSStar(SStar sst) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSParallelogram(SParallelogram spg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSOctagon(SOctagon soc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSTrapezium(STrapezium stp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSTriangleScale(STriangleScale stc) {
		// TODO Auto-generated method stub
		
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
		
        this.title = new JLabel(title);
        this.title.setBounds(65, 10, 110, 20);
		
		this.bSave = new JButton("Save");
		this.bSave.setBounds(45, 120, 65, 30);
		this.bExit = new JButton ("Exit");
		this.bExit.setBounds(114, 120, 65, 30);
		
		this.popUp.add(this.title);
		this.popUp.add(this.bSave);
		this.popUp.add(this.bExit);
		
		this.bExit.addActionListener(e -> {
			if(e.getSource().equals(this.bExit)) {
				this.popUp.dispose();
			}
		});   
    }
	
	public void addLocSettings(int dx, int dy, int w, int h) {
		this.lx = new JLabel("x :");
		this.lx.setBounds(dx, dy, w, h);
		this.ly = new JLabel("y :");
		this.ly.setBounds(dx, dy+30, w, h);
		
		this.tx = new JTextField(10);
		this.tx.setBounds(dx+20, dy+2, w+40, h);
		this.ty = new JTextField(10);
		this.ty.setBounds(dx+20, dy+32, w+40, h);
		
		this.popUp.add(this.lx);
		this.popUp.add(this.tx);
		this.popUp.add(this.ly);
		this.popUp.add(this.ty);
	}
	
	public void addSizeSettings(int dx, int dy, int w, int h) {
		this.lWidth = new JLabel("Width :");
		this.lWidth.setBounds(dx, dy, w, h);
		this.tWidth = new JTextField(10);
		this.tWidth.setBounds(dx+60, dy+2, w, h);
		
        this.lHeight = new JLabel("Height :");
		this.lHeight.setBounds(dx, dy+30, w, h);
		this.tHeight = new JTextField(10);
		this.tHeight.setBounds(dx+60, dy+32, w, h);
				
		this.popUp.add(this.lWidth);
		this.popUp.add(this.tWidth);
		this.popUp.add(this.lHeight);
		this.popUp.add(this.tHeight);
	}
	
	public void addTextSettings(int dx, int dy, int w, int h) {
		this.lText = new JLabel("Text :");
		this.lText.setBounds(dx, dy, w, h);
		
		this.tText = new JTextArea();
		this.scrolltxt = new JScrollPane(this.tText);
		this.scrolltxt.setBounds(dx+51, dy+2, w+140, h+80);
				
		this.popUp.add(this.lText);
		this.popUp.add(this.scrolltxt);
	}
	
}