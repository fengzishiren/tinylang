import java.util.List;

public class Append extends BuiltinFun {

	public Append() {
		super("append", 0);
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
