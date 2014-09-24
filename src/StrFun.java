import java.util.List;

public class StrFun extends BuiltinFun {

	public StrFun() {
		super("str", 1);// ignore param's count
	}

	@Override
	public Value apply(List<Value> args) {
		return new StringValue(args.get(0).toString());
	}
}