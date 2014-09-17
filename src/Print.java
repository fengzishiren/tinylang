import java.util.List;

public class Print extends BuiltinFun {

	public Print() {
		super("print", 1);
	}

	@Override
	public Value apply(List<Value> args) {
		System.out.println(U.join(",", args));
		return Value.VOID;
	}

	@Override
	public Value typecheck(List<Value> args) {
		return Value.VOID;
	}

}
