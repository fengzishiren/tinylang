public class Break extends Stmt {

	public Break() {
	}

	@Override
	public Value interp(Scope s) {
		throw new Goto();
	}

	@Override
	public String toString() {
		return "break";
	}
}
