import java.util.List;

public class Append extends BuiltinFun {

	public Append() {
		super("append", 2);
	}

	/**
	 * append(ls, x) : ls.append(x)
	 */
	@Override
	public Value apply(List<Value> args) {
		Value value = args.get(0);
		if (value instanceof ListValue) {
			((ListValue) value).append(args.get(1));
			return value; // support: ls.append().append()
		}
		S.error("只有list类型支持append方法");
		return Value.VOID;
	}

	@Override
	public Type type() {
		return Type.BuiltinFuntion;
	}

}
