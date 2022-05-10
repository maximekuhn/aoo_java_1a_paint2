package graphics.shapes;

public interface ShapeVisitor {

	public void visitRectangle(SRectangle sr);
	
	public void visitCircle(SCircle sc);
	
	public void visitText(SText st);

	public void visitTextBox(STextBox stb);
	
	public void visitCollection(SCollection sc);
	
	public void visitSImage(SImage si);
	
	public void visitSSketch(SSketch sk);
	
	public void visitSKotlin(SKotlin sk);

	public void visitSTriangle(STriangle st);

	public void visitSLine(SLine sl);
}
