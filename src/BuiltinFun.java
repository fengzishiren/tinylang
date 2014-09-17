import java.util.List;


public abstract class BuiltinFun extends Value {

	public String name;
	public int arity;

	public BuiltinFun(String name, int arity) {
		super();
		this.name = name;
		this.arity = arity;
	}

	public abstract Value apply(List<Value> args);

	public abstract Value typecheck(List<Value> args);

	@Override
	public String toString() {
		return name;
	}

}
