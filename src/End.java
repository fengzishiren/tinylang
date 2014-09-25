public class End extends Stmt {

	public End() {
		super(Position.IGNORE);
	}

	@Override
	public Value interp(Scope s) {
		return Value.VOID;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "";
	}

}
