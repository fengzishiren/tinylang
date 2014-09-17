public class Else extends Stmt {
	public Node test;
	public Node then;
	public Node other;

	public Else(Node test, Node then, Node other) {
		super();
		this.test = test;
		this.then = then;
		this.other = other;
	}

	@Override
	public Value interp(Scope s) {
		Value vt = test.interp(s);
		if (vt instanceof BoolValue && ((BoolValue) vt).value) {
			then.interp(s);
		} else {
			other.interp(s);
		}
		return Value.VOID;
	}

	@Override
	public Value typecheck(Scope s) {
		Value vt = test.typecheck(s);
		if (!(vt instanceof BoolType)) {
			S.error("必须是boolean " + test);
		}
		return vt;
	}
}
