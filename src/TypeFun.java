import java.util.List;

public class TypeFun extends BuiltinFun {

	public TypeFun() {
		super("type", 1);
	}

	@Override
	public Value apply(List<Value> args, Position pos) {
		return args.get(0).type();
	}

}
