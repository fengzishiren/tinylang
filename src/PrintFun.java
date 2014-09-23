import java.util.List;

public class PrintFun extends BuiltinFun {

	public PrintFun() {
		super("print", -1);//ignore param's count
	}

	@Override
	public Value apply(List<Value> args) {
		System.out.println(U.join(" ", args));
		return Value.VOID;
	}
}
