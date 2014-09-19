import java.util.List;

public class LenFun extends BuiltinFun {

	public LenFun() {
		super("len", 1);
	}

	@Override
	public Value apply(List<Value> args) {
		Value value = args.get(0);
		if ( value instanceof ComplexValue) {
			ComplexValue cv = (ComplexValue) value;
			return cv.size();
			
		} 
		if (value instanceof StringValue) {
			StringValue cv = (StringValue) value;
			return new IntValue(cv.value.length());
		}
		S.error("%s不支持len函数", value.type());
		return Value.VOID;  
	}

	@Override
	public Type type() {
		return Type.BuiltinFuntion;
	}


}