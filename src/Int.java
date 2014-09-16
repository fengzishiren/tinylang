
public class Int extends Node {
	public int value;

	public Int(Object content) {
		this.value = (int) content;
	}

	@Override
	public Value interp(Scope s) {
		return new IntValue(value);
	}

	@Override
	public Value typecheck(Scope s) {
		return Type.INT;
	}

}
