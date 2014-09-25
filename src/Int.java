
public class Int extends Node {
	public int value;

	public Int(Position pos, Object content) {
		super(pos);
		this.value = (int) content;
	}

	@Override
	public Value interp(Scope s) {
		return new IntValue(value);
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

}
