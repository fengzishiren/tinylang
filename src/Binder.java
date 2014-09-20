public class Binder {

//	public static void assign(Name name, Value value, Scope s) {
//		Scope scope = s.findDefineScope(name.id);
//		if (scope == null)
//			s.putValue(name.id, value);
//		else
//			scope.putValue(name.id, value);
//	}
//	
	public static void assign(Name name, Value value, Scope s) {
//		Scope scope = s.findDefineScope(name.id);
//		if (scope == null)
//			// S.error("变量未定义！" + name.id);
//			s.putValue(name.id, value);
//		else
			s.putValue(name.id, value);
	}

	public static void bind(Name name, Value value, Scope s) {
			s.putValue(name.id, value);
	}

	
	public static void define(Name name, Value value, Scope s) {
		Value v = s.lookup(name.id);
		if (v != null)
			S.error("函数重定义： %s", name);
		s.putValue(name.id, value);
	}

}
