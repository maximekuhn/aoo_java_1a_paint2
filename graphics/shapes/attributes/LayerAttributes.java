package graphics.shapes.attributes;

public class LayerAttributes extends Attributes {

	public static final String ID = "layer";
	
	private int layer;
	
	public LayerAttributes() {
		this(0);
	}
	
	public LayerAttributes(int layer) {
		this.layer = layer;
	}
	
	public int getLayer() {
		return this.layer;
	}
	
	public void incrementLayer() {
		this.layer++;
	}
	
	public void decrementLayer() {
		// no negative layers
		if(this.layer > 0) this.layer--;
	}
	
	public void setLayer(int layer) {
		this.layer = layer;
	}
	
	@Override
	public String getId() {
		return ID;
	}

}
