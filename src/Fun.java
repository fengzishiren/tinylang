public class Fun extends Node {
	public Name name;
	public Parameter params;
	public Node body;

	public Fun(Name name, Parameter params, Node body) {
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
	public String toString() {
		return name + "(" + params + ") " + body;
	}

}
