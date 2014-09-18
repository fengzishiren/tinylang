import java.util.List;

public class Print extends BuiltinFun {

	public Print() {
		super("print", 1);
	}

	@Override
	public Value apply(List<Value> args) {
		System.out.println(U.join(",", args));
//		Value v = args.get(0);
//		if (v instanceof StringValue)
//			U.shell(v.toString(), "\"");
		return Value.VOID;
	}

	@Override
	public Value typecheck(List<Value> args) {
		return Value.VOID;
	}

}
