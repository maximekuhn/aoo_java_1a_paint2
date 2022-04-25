package graphics.shapes;

public interface ShapeVisitor {

	public void visitRectangle(SRectangle sr);
	
	public void visitCircle(SCircle sc);
	
	public void visitText(SText st);
	
	public void visitCollection(SCollection sc);
	
	public void visitImage(SImage si);
}
