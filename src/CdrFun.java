import java.util.List;

public class CdrFun extends BuiltinFun {

	public CdrFun() {
		super("cdr", 1);
	}

	@Override
	public Value apply(List<Value> args) {
		Value cons = args.get(0);
		if (!(cons instanceof ConsValue)) 
			S.error("cdr只能操作cons类型数据");
		return ((ConsValue)cons).second;
	}
}
