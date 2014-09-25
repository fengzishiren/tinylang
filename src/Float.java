

public class Float extends Node {

	public float value;

	public Float(Position pos, Object content) {
		super(pos);
		this.value = (float) content;
	}

	@Override
	public Value interp(Scope s) {
		return new FloatValue(value);
	}
	
	@Override
	public String toString() {
		return Double.toString(value);
	}

}
