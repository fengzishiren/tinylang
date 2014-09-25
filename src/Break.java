public class Break extends Stmt {

	public Break(Position pos) {
		super(pos);
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
