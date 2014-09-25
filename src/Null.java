public class Null extends Stmt {

	public Null() {
		super(Position.IGNORE);
	}

	@Override
	public Value interp(Scope s) {
		return Value.NULL;
	}

	@Override
	public String toString() {
		return "null";
	}

}
