

public class Int extends Node {
	public Object content;
	public int value;

	public Int(Object content) {
		this.content = content;
		this.value = (int) content;
	}

	@Override
	public Value interp(Scope s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value typecheck(Scope s) {
		// TODO Auto-generated method stub
		return null;
	}

}
