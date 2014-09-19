import java.util.LinkedHashMap;
import java.util.Map;

public class Scope {

	public Map<String, Value> table = new LinkedHashMap<>();

	public Scope prev;

	public Scope() {
	}

	public Scope(Scope s) {
		prev = s;
	}

	public Scope findDefineScope(String name) {
		if (table.containsKey(name))
			return this;
		else if (prev != null)
			return prev.findDefineScope(name);
		else
			return null;
	}

	public void putValue(String name, Value value) {
		table.put(name, value);
	}

	public Value lookup(String name) {
		Value v = table.get(name);
		if (v != null)
			return v;
		else if (prev != null)
			return prev.lookup(name);
		return null;
	}

	public static Scope initScope() {
		Scope scope = new Scope();
		scope.putValue("print", new Print());
		scope.putValue("type", new TypeFun());
		scope.putValue("version", new Version());
		scope.putValue("len", new LenFun());
		scope.putValue("remove", new Remove());
		scope.putValue("append", new Append());
		return scope;
	}
	
	@Override
	public String toString() {
		return table.toString();
	}

}
