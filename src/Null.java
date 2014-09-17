public class Null extends Stmt {

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
		return "";
	}

}
