
public class Bool extends Node {

	public boolean value;

	public Bool(Object content) {
		value = "true".equals(content) ? true : false;
	}

	@Override
	public Value interp(Scope s) {
		return new BoolValue(value);
	}

	@Override
	public Value typecheck(Scope s) {
		return new BoolType();
	}

}
