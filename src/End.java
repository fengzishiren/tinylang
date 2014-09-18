public class End extends Stmt {

	@Override
	public Value interp(Scope s) {
		return Value.NULL;
	}

	@Override
	public Value typecheck(Scope s) {
		return Type.NULL;
	}

	@Override
	public String toString() {
		return "<End>";
	}

}
