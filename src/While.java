public class While extends Stmt {

	public Node test;
	public Node stmt;

	public While() {
	}

	public void init(Node test, Node stmt) {
		this.test = test;
		this.stmt = stmt;
	}

	@Override
	public Value interp(Scope s) {
		return test.interp(s);
	}

	@Override
	public Value typecheck(Scope s) {
		Value v = test.typecheck(s);
		if (!(v instanceof BoolType))
			S.error("必须是boolean " + test);
		return v;
	}

	@Override
	public String toString() {
		return String.format("while (%s) %s", test, stmt);
	}
}
