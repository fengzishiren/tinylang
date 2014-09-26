public class For extends Stmt {
	public Node init;
	public Node test;
	public Node update;
	public Node stmt;

	public For(Position pos) {
		super(pos);
	}

	public void init(Node test, Node stmt) {
		this.test = test;
		this.stmt = stmt;
	}

	public void init(Stmt initStmt, Node x, Stmt incStmt, Stmt body) {
		init = initStmt;
		test = x;
		update = incStmt;
		stmt = body;
	}

	@Override
	public Value interp(Scope s) {
		init.interp(s);
		try {
			do {
				try {
					Value vt = test.interp(s);
					if (!(vt instanceof BoolValue))
						S.error(pos, "必须是boolean " + test);
					if (((BoolValue) vt).value) {
						stmt.interp(s);
					} else
						break;
				} catch (Goon ignore) {
				} finally {
					update.interp(s);
				}
			} while (true);
		} catch (Goto ignore) {
		}
		return Value.VOID;
	}

	@Override
	public String toString() {
		return String.format("for (%s; %s; %s;) %s", init, test, update, stmt);
	}

}
