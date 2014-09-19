

public class Float extends Node {

	public float value;

	public Float(Object content) {
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
