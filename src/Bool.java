
public class Bool extends Node {

	public boolean value;

	public Bool(Position pos, Object content) {
		super(pos);
		value = "true".equals(content) ? true : false;
	}

	@Override
	public Value interp(Scope s) {
		return new BoolValue(value);
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
 
}
