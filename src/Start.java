import java.util.List;

public class Start extends BuiltinFun {

	public Start() {
		super("start", 0);
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
