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
//		Closure closure = new Closure(this, s);
//		Binder.define(name, closure, s);
//		return closure;
		return body.interp(s);
	}

	@Override
	public String toString() {
		return name + "(" + U.join(", ", params) + ") " + body;
	}

}
