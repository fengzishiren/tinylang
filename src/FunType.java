public class FunType extends Value {
	public Fun fun;
	public Scope env;

	public FunType(Fun fun, Scope env) {
		super();
		this.fun = fun;
		this.env = env;
	}

	@Override
	public String toString() {
		return env.toString();
	}
}
