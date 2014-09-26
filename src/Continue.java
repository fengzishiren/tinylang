public class Continue extends Stmt {

	public Continue(Position pos) {
		super(pos);
	}

	@Override
	public Value interp(Scope s) {
		throw new Goon();
	}

	@Override
	public String toString() {
		return "continue";
	}
}
