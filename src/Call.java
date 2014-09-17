import java.util.ArrayList;
import java.util.List;

public class Call extends Stmt {
	public Name op;
	public Argument args;

	public Call(Name op, Argument args) {
		super();
		this.op = op;
		this.args = args;
	}

	@Override
	public Value interp(Scope s) {
		Value opv = op.interp(s);
		if (opv instanceof Closure) {
			Closure closure = (Closure) opv;
			// 注意继承每个函数共享的初始envScope
			Scope funScope = new Scope(closure.env);
			for (int i = 0; i < closure.fun.params.size(); i++) {
				Value v = args.elements.get(i).interp(s);
				funScope.putValue(closure.fun.params.get(i).id, v);
			}
			//所有return语句的地方都以异常ReturnJmp传递返回值 
			Value v = Value.VOID;
			try {
				closure.fun.body.interp(funScope);
			} catch (ReturnJmp e) { //返回值 通过异常可以使return语句在某一个函数内的任意block中都能成功返回 唉 实是无奈之举啊
				return e.attatchment();//  
			}
			return v;
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
		Value opv = op.interp(s);
		if (opv instanceof BuiltinFun || opv instanceof Closure)
			return opv;
		else
			S.error("不支持的调用类型：" + opv);
		return Value.VOID; // never touch
	}

	@Override
	public String toString() {
		return op + "(" + args + ")";
	}

}
