public class Return extends Stmt {

	public Node opexpr;

	public Return(Node opexpr) {
		super();
		this.opexpr = opexpr;
	}

	@Override
	public Value interp(Scope s) {
		if (opexpr == null)
			throw new ReturnJmp();
		else
			throw new ReturnJmp(opexpr.interp(s));
	}

	@Override
	public Value typecheck(Scope s) {
		return opexpr == null ? Value.VOID : opexpr.typecheck(s);
	}

	@Override
	public String toString() {
		return "return " + (opexpr == null ? "" : opexpr);
	}

}
