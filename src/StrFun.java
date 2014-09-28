import java.util.List;

public class StrFun extends BuiltinFun {

	public StrFun() {
		super("str", 1);
	}

	@Override
	public Value apply(List<Value> args, Position pos) {
		return new StringValue(args.get(0).toString());
	}
}
