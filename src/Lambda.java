public class Lambda extends Fun {

	public Lambda(Parameter params, Node body) {
		super(Name.Lambda, params, body);
	}
	@Override
	public Value interp(Scope s) {
		// NOTE: bind Scope implements Closure
		// 在闭包定义处绑定Scope以达到对upvalue的访问
		// 在非闭包情况下什么也不错！
		return new Closure(this, s);// NOTE: bind Scope implements Closure
	}
}
