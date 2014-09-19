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
		if (!(vt instanceof BoolValue))
			S.error("必须是boolean " + test);
		if (((BoolValue) vt).value) {
			//return then.interp(s);
			then.interp(s);
		} else {
			//return other.interp(s);
			other.interp(s);
		}
		return Value.VOID;
	}
}
