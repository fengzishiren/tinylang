

public class Return extends Stmt {

	public Node expr;

	public Return(Node expr) {
		super();
		this.expr = expr;
	}

	@Override
	public Value interp(Scope s) {
		return expr.interp(s);
	}

}
