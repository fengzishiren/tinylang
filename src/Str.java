
public class Str extends Node {
	public String value;

	public Str(Object content) {
		this.value = (String) content;
	}

	@Override
	public Value interp(Scope s) {
		return new StringValue(value);
	}

	@Override
	public String toString() {
		return "\"" + value + "\"";
	}

}
