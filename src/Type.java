/**
 * 类型定义
 * @author lunatic
 * 2014年9月18日
 */
public class Type extends Value {

	public static final Type TYPE = new Type("type");
	public static final Type STRING = new Type("String");
	public static final Type INT = new Type("int");
	public static final Type BOOL = new Type("boolean");
	public static final Type Float = new Type("float");
	public static final Type Dict = new Type("dict");
	public static final Type List = new Type("list");
	public static final Type NULL = new Type("null");
	//public static final Type ANY = new Type("any");
	public static final Type VOID = new Type("void");
	public static final Type builtinFuntion = new Type("function");
	public static final Type Funtion = new Type("Built-in function");

	@Override
	public Type type() {
		return Type.TYPE;
	}

	public String type;

	public Type(String t) {
		this.type = t;
	}

	@Override
	public String toString() {
		return type + (this != TYPE ? " type" : "");
	}
}
