import java.util.List;

public class ConsFun extends BuiltinFun {

	public ConsFun() {
		super("cons", 2);
	}

	@Override
	public Value apply(List<Value> args, Position pos) {
		Value first = args.get(0);
		Value second = args.get(1);
		return new ConsValue(first, second);
	}

}
