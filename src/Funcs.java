

import java.util.ArrayList;
import java.util.List;

public class Funcs extends Node {

	private List<Fun> funcs = new ArrayList<>();

	public void addFun(Fun fun) {
		funcs.add(fun);
	}

	@Override
	public Value interp(Scope s) {
		int idx = 0;
		Fun main = null;
		for (Fun fun : funcs) {
			if ("main".equals(fun.name)) {
				idx++;
				main = fun;
			}
		}
		if (idx == 0) {
			S.error("找不到main函数");
		} else if (idx > 1) {
			S.error("main函数重复定义在多处");
		}
		Call call = new Call(main, null);
		return call.interp(s);
	}

	@Override
	public Value typecheck(Scope s) {
		// TODO Auto-generated method stub
		return null;
	}

}
