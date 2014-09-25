public class Else extends Stmt {
	public Node test;
	public Node then;
	public Node other;

	public Else(Position pos, Node test, Node then, Node other) {
		super(pos);
		this.test = test;
		this.then = then;
		this.other = other;
	}

	@Override
	public Value interp(Scope s) {
		Value vt = test.interp(s);
		if (!(vt instanceof BoolValue))
			S.error(pos, "必须是boolean " + test);
		if (((BoolValue) vt).value) {
			//return then.interp(s);
			then.interp(s);
		} else {
			//return other.interp(s);
			other.interp(s);
		}
		return Value.VOID;
	}
	
	@Override
	public String toString() {
		return String.format("if (%s) %s else %s", test, then, other);
	}
}
