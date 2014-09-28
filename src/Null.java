public class Null extends Stmt {

	public Null() {
		super(Position.IGNORE);
	}

	public Null(Position pos) {
		super(pos);
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
