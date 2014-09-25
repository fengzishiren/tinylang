
public class Str extends Node {
	public String value;

	public Str(Position pos, Object content) {
		super(pos);
		this.value = (String) content;
	}

	@Override
	public Value interp(Scope s) {
		return new StringValue(value);
	}

	/**
	 * eg: "你好，\t世界！\n"
	 */
	@Override
	public String toString() {
		return "\"" + U.escape(value) + "\"";
	}

}
