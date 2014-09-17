public class Binder {

	public static void assign(Name name, Value value, Scope s) {
		Scope scope = s.findDefineScope(name.id);
		if (scope == null)
			// S.error("变量未定义！" + name.id);
			s.putValue(name.id, value);
		else
			scope.putValue(name.id, value);
	}

	public static void define(Name name, Value value, Scope s) {
		Value v = s.lookupLocal(name.id);
		if (v != null) 
			S.error("函数重定义： %s", name);
		s.putValue(name.id, value);
	}
}
