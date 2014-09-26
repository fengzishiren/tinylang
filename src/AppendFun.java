import java.util.List;

public class AppendFun extends BuiltinFun {

	public AppendFun() {
		super("append", 2);
	}

	/**
	 * append(ls, x) : ls.append(x)
	 */
	@Override
	public Value apply(List<Value> args, Position pos) {
		Value value = args.get(0);
		if (value instanceof ListValue) {
			((ListValue) value).append(args.get(1), pos);
			return value;  
		}
		S.error(pos, "只有list类型支持append方法");
		return Value.VOID;
	}

}
