

public class Bool extends Node {

	public String content;
	public boolean value;

	public Bool(Object content) {
		this.content = (String)content;
		value = "true".equals(content) ? true : false;
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
