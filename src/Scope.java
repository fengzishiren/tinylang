import java.util.LinkedHashMap;
import java.util.Map;

public class Scope {
	class Pair {
		public Value value;
		public Value type;

		public Pair() {
		}

		public Pair(Value value, Value type) {
			super();
			this.value = value;
			this.type = type;
		}

	}

	public Map<String, Pair> table = new LinkedHashMap<>();

	public Scope prev;

	public Scope() {
	}

	public Scope(Scope s) {
		prev = s;
	}

	// public void put(String name, Object value) {
	//
	// table.put(name, value);
	// }
	//
	// public Value get(String name) {
	// return lookup(name);
	// }

	public Scope findDefineScope(String name) {
		if (table.containsKey(name))
			return this;
		else if (prev != null)
			return prev.findDefineScope(name);
		else
			return null;

	}

	public void putType(String name, Value type) {
		Pair pair = table.get(name);
		if (pair == null) {
			table.put(name, new Pair(null, type));
		} else
			pair.type = type;
	}

	public void putValue(String name, Value value) {
		Pair pair = table.get(name);
		if (pair == null) {
			table.put(name, new Pair(value, null));
		} else
			pair.value = value;
	}

	/**
	 * 
	 * value = "value" name ->map-> map.get(value)
	 * 
	 * @param name
	 * @return
	 */
	// public Value lookup(String name) {
	// Object v = lookupProperty(name, "value");
	// if (v == null)
	// return null;
	// else if (v instanceof Value)
	// return (Value) v;
	// else {
	// _.abort("value is not a Value, shouldn't happen: " + v);
	// return null;
	// }
	// }

	// public Value lookupLocal(String name) {
	// Object v = lookupPropertyLocal(name, "value");
	// if (v == null)
	// return null;
	// else if (v instanceof Value)
	// return (Value) v;
	// else {
	// _.abort("value is not a Value, shouldn't happen: " + v);
	// return null;
	// }
	// }
	public Value lookup(String name) {
		return lookupValue(name);
	}

	public Value lookupLocal(String name) {
		return lookupValue(name);
	}

	//
	// public Value lookupType(String name) {
	// Object v = lookupProperty(name, "type");
	// if (v == null)
	// return null;
	// else if (v instanceof Value)
	// return (Value) v;
	// else {
	// _.abort("value is not a Value, shouldn't happen: " + v);
	// return null;
	// }
	// }

	public Value lookupValue(String name) {
		Pair pair = table.get(name);
		if (pair != null)
			return pair.value;
		else if (prev != null)
			return prev.lookupValue(name);
		return null;
	}

	public Value lookupType(String name) {
		Pair pair = table.get(name);
		if (pair != null)
			return pair.type;
		else if (prev != null)
			return prev.lookupValue(name);
		return null;
	}

	public Object lookupProperty(String name, String key) {
		Object v = lookupPropertyLocal(name, key);
		if (v != null)
			return v;
		else if (prev != null)
			return prev.lookupProperty(name, key);
		else
			return null;
	}

	public Pair lookupPropertyLocal(String name, String key) {
		return table.get(name);
	}

	public static Scope initScope() {
		Scope scope = new Scope();
		scope.putValue("print", new Print());
		scope.putValue("type", new TypeFun());
		scope.putValue("version", new Version());
		return scope;
	}
	
	public static void main(String[] args) {
		Scope scope = Scope.initScope();
		Value v = scope.lookup("print");
		System.out.println(v);
	}

}
