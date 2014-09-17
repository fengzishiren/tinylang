public class Break extends Stmt {

	public Break() {
	}

	@Override
	public Value interp(Scope s) {
		throw new Goto();
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
