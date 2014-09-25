import java.util.ArrayList;
import java.util.List;

/**
 * 执行单元
 * 
 */
public class Unit extends Node {

	public Unit(Position pos) {
		super(pos);
	}

	private List<Fun> funcs = new ArrayList<>();

	public void addFun(Fun fun) {
		funcs.add(fun);
	}

	@Override
	public Value interp(Scope s) {
		int count = 0;
		Name main = null;
		// 每个closure共享初始Scope
		// 在此注册每个函数式是有意义的
		// 尤其对于函数的定义在调用之后
		// eg:
		// xxx echo();xxx
		// define echo(){}
		//
		for (Fun fun : funcs) {
			// reg closure
			Binder.define(fun.name, fun.interp(s), s);
			// Binder.define(fun, s);
			if ("main".equals(fun.name.id)) {
				count++;
				main = fun.name;
			}
		}
		if (count == 0) {
			S.error("找不到main函数");
		}
		Call call = new Call(main.pos, main, Argument.noArgs(Position.IGNORE));
		return call.interp(s);
	}

	@Override
	public String toString() {
		return U.join("\n", funcs);
	}

}
