
public class Call extends Stmt {
	public Node op;
	public Argument args;

	public Call(Node op, Argument args) {
		super();
		this.op = op;
		this.args = args;
	}

	@Override
	public Value interp(Scope s) {
		Value opv = op.interp(s);
		if (opv instanceof Closure) {
			Closure closure = (Closure) opv;
			Scope funScope = new Scope();

			for (int i = 0; i < closure.fun.params.size(); i++) {
				Value v = args.elements.get(i).interp(s);
				funScope.putValue(closure.fun.params.get(i).id, v);
			}
			return closure.fun.body.interp(funScope);
		} else {
			throw new UnsupportedOperationException(opv.toString());
		}
	}

	@Override
	public Value typecheck(Scope s) {
		// TODO Auto-generated method stub
		return null;
	}

}
