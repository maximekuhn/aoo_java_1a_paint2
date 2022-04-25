package graphics.shapes.attributes;

public class SelectionAttributes extends Attributes {

	public static final String ID = "selection";
	
	private boolean selected;

	public SelectionAttributes() {
		this(false);
	}
	
	public SelectionAttributes(boolean selected) {
		this.selected = selected;
	}
	
	@Override
	public String getId() {
		return ID;
	}
	
	public boolean isSelected() {
		return this.selected;
	}
	
	public void select() {
		this.selected = true;
	}
	
	public void unselect() {
		this.selected = false;
	}
	
	public void toggleSelection() {
		if(this.selected) this.unselect();
		else this.select();
	}
}
