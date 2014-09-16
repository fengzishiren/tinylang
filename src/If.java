public class If extends Stmt {
	public Node test;
	public Node stmt;

	public If(Node test, Node stmt) {
		super();
		this.test = test;
		this.stmt = stmt;
	}

	@Override
	public Value interp(Scope s) {
		Value vt = test.interp(s);
		if (vt != null || vt instanceof BoolValue && ((BoolValue) vt).value) {
			stmt.interp(s);
		}
		return Value.VOID;
	}

	@Override
	public Value typecheck(Scope s) {
		Value vt = test.typecheck(s);
		if (!(vt instanceof BoolType)) {
			S.error("必须是boolean " + test);
		}
		return vt;
	}

	@Override
	public String toString() {
		return String.format("if (%s) %s", test, stmt);
	}

}
