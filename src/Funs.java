import java.util.ArrayList;
import java.util.List;

public class Funs extends Node {

	private List<Fun> funcs = new ArrayList<>();

	public void addFun(Fun fun) {
		funcs.add(fun);
	}

	@Override
	public Value interp(Scope s) {
		int idx = 0;
		Name main = null;
		for (Fun fun : funcs) {
			Binder.define(fun.name, new Closure(fun, s), s);
			if ("main".equals(fun.name.id)) {
				idx++;
				main = fun.name;
			}
		}
		if (idx == 0) {
			S.error("找不到main函数");
		} else if (idx > 1) {
			S.error("main函数重复定义在多处");
		}
		Call call = new Call(main, Argument.noArgs());
		return call.interp(s);
	}

	@Override
	public Value typecheck(Scope s) {
		return Value.ANY;
	}

	@Override
	public String toString() {
		return U.join("\n", funcs);
	}

}
