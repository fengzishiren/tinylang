
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
		if (opv == null) {
			S.error("找不到函数的定义：" + op);
		}
		if (opv instanceof Closure) {
			Closure closure = (Closure) opv;
			// 注意继承每个函数共享的初始envScope
			Scope funScope = new Scope(closure.env);
			if (args.size() != closure.fun.params.size()) {
				S.error("调用参数不匹配: " + this);
			}
			ListValue lv = args.interp(funScope);

			for (int i = 0; i < closure.fun.params.size(); i++) {
				funScope.putValue(closure.fun.params.get(i).id, lv.get(i));
			}
			// 所有return语句的地方都以异常ReturnJmp传递返回值
			try {
				return closure.fun.body.interp(funScope);
			} catch (ReturnJmp e) { // 返回值 通过异常可以使return语句在某一个函数内的任意block中都能成功返回(包括结尾)
				return e.attatchment();//// 唉 实是无奈之举啊
			}
		} else if (opv instanceof BuiltinFun) {
			BuiltinFun fun = (BuiltinFun) opv;
			if (fun.arity != args.size()) {
				S.error("调用参数不匹配: " + this);
			}
			return fun.apply(args.interp(s).values);
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
