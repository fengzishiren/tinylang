import java.util.List;

public class Fun extends Node {
	public Name name;
	public List<Name> params;
	public Node body;

	public Fun(Name name, List<Name> params, Node body) {
		super();
		this.name = name;
		this.params = params;
		this.body = body;
	}

	@Override
	public Value interp(Scope s) {
		// Closure closure = new Closure(this, s);
		// Binder.define(name, closure, s);
		return s.lookup(name.id);
		// return closure;
	}

	@Override
	public Value typecheck(Scope s) {
		return new FunType(this, s);
	}

	@Override
	public String toString() {
		return name + "(" + U.join(", ", params) + ") " + body;
	}

}
