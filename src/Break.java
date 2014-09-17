public class Break extends Stmt {
	Stmt stmt;

	public Break() {
		stmt = Stmt.Enclosing;
	}

	@Override
	public Value interp(Scope s) {
		return Value.VOID;
	}

	@Override
	public Value typecheck(Scope s) {
		return Value.VOID;
	}

	@Override
	public String toString() {
		return "break";
	}
}
