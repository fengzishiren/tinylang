import java.util.List;

public class CarFun extends BuiltinFun {

	public CarFun() {
		super("car", 1);
	}

	@Override
	public Value apply(List<Value> args, Position pos) {
		Value cons = args.get(0);
		if (!(cons instanceof ConsValue)) 
			S.error(pos, "car只能操作cons类型数据");
		return ((ConsValue)cons).first;
	}

}
