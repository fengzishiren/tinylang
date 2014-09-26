import java.util.List;

public class LengthFun extends BuiltinFun {

	public static final String LEN = "len";
	public static final String SIZE = "size";
	public LengthFun(String name) {
		super(name, 1);
	}

	@Override
	public Value apply(List<Value> args, Position pos) {
		Value value = args.get(0);
		if ( value instanceof ComplexValue) {
			ComplexValue cv = (ComplexValue) value;
			return cv.size();
			
		} 
		if (value instanceof StringValue) {
			StringValue cv = (StringValue) value;
			return new IntValue(cv.value.length());
		}
		S.error(pos,"%s不支持len函数", value.type());
		return Value.VOID;  
	}


}
