public class Null extends Stmt {

	@Override
	public Value interp(Scope s) {
		return Value.NULL;
	}

	@Override
	public String toString() {
		return "null";
	}

}
