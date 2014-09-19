import java.util.List;

public class Remove extends BuiltinFun {

	public Remove() {
		super("remove", 2);
	}

	@Override
	public Value apply(List<Value> args) {
		Value value = args.get(0);
		if (value instanceof ListValue) {
			Value val = args.get(1);
			if (val instanceof IntValue) {// idx access
				((ListValue) value).remove(((IntValue) val));
			} else
				// val access
				((ListValue) value).remove((val));
		} else if (value instanceof DictValue) {
			Value val = args.get(1);
			if (!(val instanceof PrimValue))
				S.error("key必须是原始类型");
			((DictValue) value).remove((PrimValue) val);
		} else
			S.error("不支持remove函数");
		return Value.VOID;
	}

	@Override
	public Type type() {
		return Type.BuiltinFuntion;
	}

}