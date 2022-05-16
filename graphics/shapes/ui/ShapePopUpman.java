package graphics.shapes.ui;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import graphics.shapes.SArrow;
import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SCross;
import graphics.shapes.SDecagon;
import graphics.shapes.SEllipse;
import graphics.shapes.SFourPointedStar;
import graphics.shapes.SHeptagon;
import graphics.shapes.SHexagon;
import graphics.shapes.SImage;
import graphics.shapes.SKotlin;
import graphics.shapes.SLine;
import graphics.shapes.SNonagon;
import graphics.shapes.SOctagon;
import graphics.shapes.SParallelogram;
import graphics.shapes.SPentagon;
import graphics.shapes.SRectangle;
import graphics.shapes.SRhombus;
import graphics.shapes.SSixPointedStar;
import graphics.shapes.SSketch;
import graphics.shapes.SStar;
import graphics.shapes.SText;
import graphics.shapes.STextBox;
import graphics.shapes.STrapezium;
import graphics.shapes.STriangle;
import graphics.shapes.STriangleRec;
import graphics.shapes.STriangleScale;
import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.FontAttributes;

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
    
    private FontAttributes fa;
    private final String[] fontsName = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    private final Integer[] sizeFont = new Integer[95];
    private JComboBox<String> fontsBox;
    private JComboBox<Integer> sizeBox; 
    private Font font;
    
    private JScrollPane scrolltxt;

	private JToggleButton textLeft = new JToggleButton(this.imageSize("src/pictures/text/left.png"));
	private JToggleButton textCenter = new JToggleButton(this.imageSize("src/pictures/text/center.png"));
	private JToggleButton textRight = new JToggleButton(this.imageSize("src/pictures/text/right.png"));
	private ButtonGroup textAlignX = new ButtonGroup();

	private JToggleButton textTop = new JToggleButton(this.imageSize("src/pictures/text/top.png"));
	private JToggleButton textMiddle = new JToggleButton(this.imageSize("src/pictures/text/middle.png"));
	private JToggleButton textBottom = new JToggleButton(this.imageSize("src/pictures/text/bottom.png"));
	private ButtonGroup textAlignY = new ButtonGroup();

	private JToggleButton bold = new JToggleButton(this.imageSize("src/pictures/text/bold.png"));
	private JToggleButton italic = new JToggleButton(this.imageSize("src/pictures/text/italic.png"));
    
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
		creatShapePopUp("Rectangle Settings", sr);
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
				try {
		    		int radius = Integer.valueOf(this.tRadius.getText());
		    		this.x = Integer.valueOf(this.tx.getText());
		    		this.y = Integer.valueOf(this.ty.getText());
		    		
		    		loc.setLocation(this.x, this.y);
		    		sc.setLoc(loc);
		    		sc.setRadius(radius);
		    		
		    		this.popUp.dispose();
		    		this.sview.repaint();
				} catch (Exception e1) {
					creatErrorPopup("Error : integer expected");
				}
			}
		});
	}

	@Override
	public void visitText(SText st) {
		Point loc = st.getLoc();
		
		creatSettingsFrame("Text Settings", 600, 260);
		this.title.setBounds(250, 10, 110, 20);
		
		addTextSettings(10, 40, 60, 20);
		this.tText.setText(String.valueOf(st.getText()));
		
		addLocSettings(280, 40, 20, 20);
		this.tx.setText(String.valueOf(st.getLoc().x));
		this.ty.setText(String.valueOf(st.getLoc().y));
		
		this.fa = (FontAttributes) st.getAttributes(FontAttributes.ID);
		if(this.fa == null) {
			this.fa = new FontAttributes();
			st.addAttributes(this.fa);
		}
		
		this.fontsBox = new JComboBox<>(this.fontsName);
		this.fontsBox.setEditable(true);
		this.fontsBox.setSelectedItem(this.fa.font.getName());
		this.fontsBox.setBounds(390, 40, 190, 30);
		this.popUp.add(this.fontsBox);
		
		int smin = 6;
		for(int i = 0;  i < this.sizeFont.length; i++) {
			this.sizeFont[i] = smin;
			smin++;
		}
		
		this.sizeBox = new JComboBox<>(this.sizeFont);
		this.sizeBox.setEditable(true);
		this.sizeBox.setSelectedItem(this.fa.font.getSize());
		this.sizeBox.setBounds(390, 80, 190, 30);
		this.popUp.add(this.sizeBox);
		
		this.bSave.setBounds(233, 170, 65, 30);
		this.bExit.setBounds(301, 170, 65, 30);	
		
		this.bSave.addActionListener(e -> {
			if(e.getSource().equals(this.bSave)) {
				try {
					String text = this.tText.getText();
					String fontName = (String) this.fontsBox.getSelectedItem();
					int size = (int) this.sizeBox.getSelectedItem();
		    		this.x = Integer.valueOf(this.tx.getText());
		    		this.y = Integer.valueOf(this.ty.getText());
		    		
		    		loc.setLocation(this.x, this.y);
		    		st.setLoc(loc);
		    		st.setText(text);
		    		
		    		if(size <= 0) creatErrorPopup("The size must be between 0 and 1638");
		    		else if(size > 1638) creatErrorPopup("The size must be between 0 and 1638");
		    		else {
		    			this.font = new Font(fontName, Font.PLAIN, size);
			    		this.fa.font = this.font;
			    		this.popUp.dispose();
			    		this.sview.repaint();
		    		}
				} catch (Exception e1) {
					creatErrorPopup("Integer expected for x, y and size");
				}
			}
		});	
	}

	@Override
	public void visitCollection(SCollection sc) {
		creatShapePopUp("Collection Settings", sc);
	}

	@Override
	public void visitSImage(SImage si) {
		creatShapePopUp("Image Settings", si);
	}

	@Override
	public void visitSSketch(SSketch sk) {
		creatShapePopUp("Sketch Settings", sk);
	}
	
	@Override
	public void visitTextBox(STextBox stb) {
		Point loc = stb.getLoc();
		
		creatSettingsFrame("Text Box Settings", 600, 260);
		this.title.setBounds(250, 10, 110, 20);
		
		addTextSettings(10, 40, 50, 20);
		this.tText.setText(String.valueOf(stb.getText()));
		
		addLocSettings(280, 40, 20, 20);
		this.tx.setText(String.valueOf(stb.getLoc().x));
		this.ty.setText(String.valueOf(stb.getLoc().y));
		
		this.fa = (FontAttributes) stb.getAttributes(FontAttributes.ID);
		if(this.fa == null) {
			this.fa = new FontAttributes();
			stb.addAttributes(this.fa);
		}
		
		this.fontsBox = new JComboBox<>(this.fontsName);
		this.fontsBox.setEditable(true);
		this.fontsBox.setSelectedItem(this.fa.font.getName());
		this.fontsBox.setBounds(390, 40, 190, 30);
		this.popUp.add(this.fontsBox);
		
		int smin = 6;
		for(int i = 0;  i < this.sizeFont.length; i++) {
			this.sizeFont[i] = smin;
			smin++;
		}
		
		this.sizeBox = new JComboBox<>(this.sizeFont);
		this.sizeBox.setEditable(true);
		this.sizeBox.setSelectedItem(this.fa.font.getSize());
		this.sizeBox.setBounds(390, 80, 190, 30);
		this.popUp.add(this.sizeBox);
		
		addTextAdvancedSettings(this.popUp);

		this.bSave.setBounds(235, 180, 65, 30);
		this.bExit.setBounds(304, 180, 65, 30);	
		
		this.bSave.addActionListener(e -> {
			if(e.getSource().equals(this.bSave)) {
				try {
					String text = this.tText.getText();
					String fontName = (String) this.fontsBox.getSelectedItem();
					int size = (int) this.sizeBox.getSelectedItem();
		    		this.x = Integer.valueOf(this.tx.getText());
		    		this.y = Integer.valueOf(this.ty.getText());
					
					if (textLeft.isSelected()) this.fa.alignX = 0;
					else if (textCenter.isSelected()) this.fa.alignX = 1;
					else if (textRight.isSelected()) this.fa.alignX = 2;

					if (textTop.isSelected()) this.fa.alignY = 0;
					else if (textMiddle.isSelected()) this.fa.alignY = 1;
					else if (textBottom.isSelected()) this.fa.alignY = 2;

					this.fa.style = 0;
					if (bold.isSelected()) this.fa.style += 1;
					if (italic.isSelected()) this.fa.style += 2;
		    		
					loc.setLocation(this.x, this.y);
		    		stb.setLoc(loc);
		    		stb.setText(text);
		    		
					if(size <= 0) creatErrorPopup("The size must be between 0 and 1638");
		    		else if(size > 1638) creatErrorPopup("The size must be between 0 and 1638");
		    		else {
		    			this.font = new Font(fontName, this.fa.style, size);
			    		this.fa.font = this.font;
			    		this.popUp.dispose();
			    		this.sview.repaint();
		    		}
				} catch (Exception e1) {
					creatErrorPopup("Integer expected for x, y and size");
				}
			}
		});
	}

	@Override
	public void visitSKotlin(SKotlin sk) {
		creatShapePopUp("Kotlin Settings", sk);
	}

	@Override
	public void visitSTriangle(STriangle st) {
		creatShapePopUp("Triangle Settings", st);
	}

	@Override
	public void visitSLine(SLine sl) {
		creatShapePopUp("Line Settings", sl);
	}

	@Override
	public void visitSHexagon(SHexagon sh) {		
		creatShapePopUp("Hexagon Settings", sh);
	}

	@Override
	public void visitSTriangleRec(STriangleRec strec) {
		creatShapePopUp("Trianlge Rec Settings", strec);
		this.title.setBounds(50, 10, 135, 20);
	}

	@Override
	public void visitSCross(SCross scr) {
		creatShapePopUp("Cross Settings", scr);
	}

	@Override
	public void visitEllipse(SEllipse se) {
		creatShapePopUp("Ellipse Settings", se);		
	}

	@Override
	public void visitSPentagon(SPentagon spt) {
		creatShapePopUp("Pentagon Settings", spt);
	}

	@Override
	public void visitSArrow(SArrow sar) {
		creatShapePopUp("Arrow Settings", sar);
	}

	@Override
	public void visitSStar(SStar sst) {
		creatShapePopUp("Star Settings", sst);
	}

	@Override
	public void visitSParallelogram(SParallelogram spg) {
		creatShapePopUp("Parallelogram Settings", spg);
		this.title.setBounds(50, 10, 135, 20);
	}

	@Override
	public void visitSOctagon(SOctagon soc) {
		creatShapePopUp("Octagon Settings", soc);
	}

	@Override
	public void visitSTrapezium(STrapezium stp) {
		creatShapePopUp("Trapezium Settings", stp);
		this.title.setBounds(50, 10, 135, 20);
	}

	@Override
	public void visitSTriangleScale(STriangleScale stc) {
		creatShapePopUp("Triangle Scale Settings", stc);
		this.title.setBounds(50, 10, 135, 20);
	}
	
	@Override
	public void visitSRhombus(SRhombus srh) {
		creatShapePopUp("Rhombus Settings", srh);
	}
	
	@Override
	public void visitSNonagon(SNonagon sn) {
		creatShapePopUp("Nonagon Settings", sn);		
	}

	@Override
	public void visitSHeptagon(SHeptagon sh) {
		creatShapePopUp("Heptagon Settings", sh);		
	}

	@Override
	public void visitSDecagon(SDecagon sd) {
		creatShapePopUp("Decagon Settings", sd);		
	}
	
	public void creatSettingsFrame(String title, int w, int h) {
    	this.popUp = new JFrame(title);
        this.popUp.setSize(w, h);
        this.popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.popUp.setResizable(false);
        this.popUp.setLocationRelativeTo(null);
        
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.popUp.setContentPane(this.panel);
    	this.popUp.setVisible(true);
    	
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
		this.lText.setBounds(dx, dy+45, w, h);
		
		this.tText = new JTextArea();
		this.scrolltxt = new JScrollPane(this.tText);
		this.scrolltxt.setBounds(dx+51, dy+2, w+140, h+100);
				
		this.popUp.add(this.lText);
		this.popUp.add(this.scrolltxt);
	}

	public void addTextAdvancedSettings(JFrame popUp) {
		this.textLeft.setBounds(280, 130, 30, 30);
		popUp.add(this.textLeft);
		this.textAlignX.add(this.textLeft);
		if (this.fa.alignX == 0) this.textLeft.setSelected(true);
		this.textCenter.setBounds(310, 130, 30, 30);
		popUp.add(this.textCenter);
		this.textAlignX.add(this.textCenter);
		if (this.fa.alignX == 1) this.textCenter.setSelected(true);
		this.textRight.setBounds(340, 130, 30, 30);
		popUp.add(this.textRight);
		this.textAlignX.add(this.textRight);
		if (this.fa.alignX == 2) this.textRight.setSelected(true);

		this.textTop.setBounds(390, 130, 30, 30);
		popUp.add(this.textTop);
		this.textAlignY.add(this.textTop);
		if (this.fa.alignY == 0) this.textTop.setSelected(true);
		this.textMiddle.setBounds(420, 130, 30, 30);
		popUp.add(this.textMiddle);
		this.textAlignY.add(this.textMiddle);
		if (this.fa.alignY == 1) this.textMiddle.setSelected(true);
		this.textBottom.setBounds(450, 130, 30, 30);
		popUp.add(this.textBottom);
		this.textAlignY.add(this.textBottom);
		if (this.fa.alignY == 2) this.textBottom.setSelected(true);

		this.bold.setBounds(500, 130, 30, 30);
		popUp.add(this.bold);
		this.italic.setBounds(550, 130, 30, 30);
		popUp.add(this.italic);
		if (this.fa.style == 1) this.bold.setSelected(true);
		else if (this.fa.style == 2) this.italic.setSelected(true);
		else if (this.fa.style == 3) {
			this.bold.setSelected(true);
			this.italic.setSelected(true);
		}
	}
	
	public void creatShapePopUp(String title, Shape s) {
		Point loc = s.getLoc();
		
		creatSettingsFrame(title, 240, 200);
		
		addSizeSettings(10, 40, 60, 20);
		this.tWidth.setText(String.valueOf(s.getBounds().width));		
		this.tHeight.setText(String.valueOf(s.getBounds().height));		
		
		addLocSettings(140, 40, 20, 20);
		this.tx.setText(String.valueOf(s.getBounds().x));
		this.ty.setText(String.valueOf(s.getBounds().y));
		
		this.bSave.addActionListener(e -> {
			if(e.getSource().equals(this.bSave)) {
				try {
					this.width = Integer.valueOf(this.tWidth.getText());
		    		this.height = Integer.valueOf(this.tHeight.getText());
		    		this.x = Integer.valueOf(this.tx.getText());
		    		this.y = Integer.valueOf(this.ty.getText());
		    		
		    		loc.setLocation(this.x, this.y);
		    		s.setLoc(loc);
		    		s.resize(this.width-s.getBounds().width, this.height-s.getBounds().height);
		    		
		    		this.popUp.dispose();
		    		this.sview.repaint();
				} catch (Exception e1) {
					creatErrorPopup("Error : integer expected");
				}
			}
		});
	}
	
	public static void creatErrorPopup(String errorMessage){
        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

	public ImageIcon imageSize(String filename) {
		return new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
	}

	@Override
	public void visitSFourPointedStar(SFourPointedStar sfp) {
		creatShapePopUp("Four pointed star Settings", sfp);
	}

	@Override
	public void visitSSixPointedStar(SSixPointedStar ssp) {
		creatShapePopUp("Six pointed star Settings", ssp);
	}

	
	
}