import java.util.List;

public abstract class BuiltinFun extends Value {

	public String name;
	public int arity;// ignore if -1

	public BuiltinFun(String name, int arity) {
		super();
		this.name = name;
		this.arity = arity;
	}

	public abstract Value apply(List<Value> args);

	public Type type() {
		return Type.BuiltinFuntion;
	}

	@Override
	public String toString() {
		return name;
	}

}
