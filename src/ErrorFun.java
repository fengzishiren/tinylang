import java.util.List;

public class ErrorFun extends BuiltinFun {

	public ErrorFun() {
		super("error", -1);//ignore param's count
	}

	@Override
	public Value apply(List<Value> args, Position pos) {
		System.err.println(pos + " " + U.join(" ", args));
		System.exit(1);
		return Value.VOID;//never touch
	}
}
