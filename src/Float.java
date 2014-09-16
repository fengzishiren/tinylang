

public class Float extends Node {

	public Object content;
	public float value;

	public Float(Object content) {
		this.content = content;
		this.value = (float) content;
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
