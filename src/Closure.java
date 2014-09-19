

public class Closure extends Value {
	public Fun fun;
	public Scope env;

	public Closure(Fun fun, Scope env) {
		this.fun = fun;
		this.env = env;
	}

	public String toString() {
		return fun.toString();
	}

	@Override
	public Type type() {
		return Type.Funtion;
	}

}
