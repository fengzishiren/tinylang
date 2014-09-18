public class End extends Stmt {

	@Override
	public Value interp(Scope s) {
		return Value.VOID;
	}

	@Override
	public Value typecheck(Scope s) {
		return Type.VOID;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "";
	}

}
