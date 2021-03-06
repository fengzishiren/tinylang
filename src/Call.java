public class Call extends Stmt {
	public Name op;
	public Argument args;

	public Call(Position pos, Name op, Argument args) {
		super(pos);
		this.op = op;
		this.args = args;
	}

	@Override
	public Value interp(Scope s) {
		Value opv = op.interp(s);
		if (opv instanceof Closure) {
			Closure closure = (Closure) opv;
			// 闭包upvalue在lambda时有效
			// 其他情形只需要共享sharescope
			Scope funScope = new Scope(closure.env);
			if (args.size() != closure.fun.params.size()) {
				S.error(pos, "调用参数不匹配: " + this);
			}
			ListValue lv = args.interp(s);
			for (int i = 0; i < closure.fun.params.size(); i++) {
				Binder.bind(closure.fun.params.get(i), lv.get(i, args.pos), funScope);
			}
			try {
				return closure.fun.body.interp(funScope);// 所有return语句的地方都以异常ReturnJmp传递返回值
			} catch (ReturnJmp e) { // 返回值
									// 通过异常可以使return语句在某一个函数内的任意block中都能成功返回(包括结尾)
				return e.attatchment();// // 唉 实是无奈之举啊
			}
		} else if (opv instanceof BuiltinFun) {
			BuiltinFun fun = (BuiltinFun) opv;

			if (fun.arity != -1 && fun.arity != args.size()) {
				S.error(pos, "调用参数不匹配: " + this);
			}
			return fun.apply(args.interp(s).values, pos);
		} else {
			S.error(pos, "不支持的调用" + this + " " + opv);
			return Value.VOID; // never touch
		}
	}

	@Override
	public String toString() {
		return op + "(" + args + ")";
	}

}
