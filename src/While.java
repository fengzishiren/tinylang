public class While extends Stmt {

	public Node test;
	public Node stmt;

	public While(Position pos) {
		super(pos);
	}

	public void init(Node test, Node stmt) {
		this.test = test;
		this.stmt = stmt;
	}

	@Override
	public Value interp(Scope s) {
		try {
			do {
				try {
					Value vt = test.interp(s);
					if (!(vt instanceof BoolValue))
						S.error("必须是boolean " + test);
					if (((BoolValue) vt).value)
						stmt.interp(s);
					else
						break;
				} catch (Goon ignore) {
				}
			} while (true);
		} catch (Goto ignore) {
		}
		return Value.VOID;
	}


	@Override
	public String toString() {
		return String.format("while (%s) %s", test, stmt);
	}
}
