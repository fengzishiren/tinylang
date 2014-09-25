import java.util.List;

public class RemoveFun extends BuiltinFun {

	public RemoveFun() {
		super("remove", 2);
	}

	@Override
	public Value apply(List<Value> args, Position pos) {
		Value value = args.get(0);
		if (value instanceof ListValue) {
			Value val = args.get(1);
			if (val instanceof IntValue) {// idx access
				((ListValue) value).remove(((IntValue) val), pos);
			} else
				S.error(pos, "list访问只允许下标为int");
		} else if (value instanceof DictValue) {
			Value val = args.get(1);
			if (!(val instanceof PrimValue))
				S.error(pos, this + "dict的key必须是原始类型");
			((DictValue) value).remove((PrimValue) val, pos);
		} else
			S.error(pos, value.type() + " 不支持remove函数");
		return Value.VOID;
	}
}
