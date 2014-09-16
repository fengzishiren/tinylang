

public class Str extends Node {
	public Object content;
	public String value;

	public Str(Object content) {
		this.content = content;
		this.value = (String) content;
	}

	@Override
	public Value interp(Scope s) {
		return new StringValue(value);
	}

	@Override
	public Value typecheck(Scope s) {
		// TODO Auto-generated method stub
		//return Type.STRING;
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "\"" + value + "\"";
	}

}