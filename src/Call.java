import java.util.ArrayList;
import java.util.List;

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
			Scope funScope = new Scope(closure.env);
			for (int i = 0; i < closure.fun.params.size(); i++) {
				Value v = args.elements.get(i).interp(s);
				funScope.putValue(closure.fun.params.get(i).id, v);
			}
			return closure.fun.body.interp(funScope);
		} else if (opv instanceof BuiltinFun) {
			BuiltinFun fun = (BuiltinFun) opv;
			List<Value> args = new ArrayList<>(this.args.elements.size());
			for (Node node : this.args.elements) {
				args.add(node.interp(s));
			}
			return fun.apply(args);
		} else {
			S.error("不支持的调用" + opv);
			return Value.VOID; // never touch
		}
	}

	@Override
	public Value typecheck(Scope s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return op + "(" + U.join(", ", args) + ")";
	}

}
