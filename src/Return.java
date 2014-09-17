public class Return extends Stmt {

	public Node opexpr;

	public Return(Node opexpr) {
		super();
		this.opexpr = opexpr;
	}

	@Override
	public Value interp(Scope s) {
		return opexpr == null ? Value.VOID : opexpr.interp(s);
	}

	@Override
	public Value typecheck(Scope s) {
		return opexpr == null ? Value.VOID : Value.ANY;
	}

	@Override
	public String toString() {
		return "return " + (opexpr == null ? "" : opexpr);
	}

}
