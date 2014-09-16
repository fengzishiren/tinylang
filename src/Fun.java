

import java.util.List;

public class Fun extends Node {
	public String name;
	public List<Name> params;
	public Node body;

	public Fun(String name, List<Name> params, Node body ) {
		super();
		this.name = name;
		this.params = params;
		this.body = body;
	}

	@Override
	public Value interp(Scope s) {
		return new Closure(this, s);
	}

	@Override
	public Value typecheck(Scope s) {
		return null;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
