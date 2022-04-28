package graphics.shapes.attributes;

public class RotationAttributes extends Attributes {

	public static final String ID = "rotation";
	private int angle; // degrees
	
	public RotationAttributes() {
		this.angle = 0;
	}
	
	@Override
	public String getId() {
		return ID;
	}
	
	public void rotate90Right() {
		this.angle += 90;
		if(this.angle == 360) this.angle = 0;
	}
	
	public int getAngle() {
		return this.angle;
	}

}
