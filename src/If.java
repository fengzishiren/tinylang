

public class If extends Stmt {
	public Node test;
	public Node stmt;

	public If(Node test, Node stmt) {
		super();
		this.test = test;
		this.stmt = stmt;
	}

	@Override
	public Value interp(Scope s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value typecheck(Scope s) {
		// TODO Auto-generated method stub
		return null;
	}

}
