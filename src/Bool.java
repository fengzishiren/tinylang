
public class Bool extends Node {

	public boolean value;

	public Bool(Object content) {
		//assert content == "true" || content == "false"
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
