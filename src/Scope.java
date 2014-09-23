import java.util.LinkedHashMap;
import java.util.Map;

public class Scope {

	public Map<String, Value> table = new LinkedHashMap<>();

	public Scope prev;

	/**
	 * global var share
	 */
	public static Scope share = new Scope();
 
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

	public static Scope initScope() {// init share
		share.putValue("print", new PrintFun());
		share.putValue("type", new TypeFun());
		share.putValue("version", new VersionFun());
		share.putValue("len", new LenFun());
		share.putValue("remove", new RemoveFun());
		share.putValue("append", new AppendFun());
		share.putValue("cons", new ConsFun());
		share.putValue("car", new CarFun());
		share.putValue("cdr", new CdrFun());
		return share;
	}
	public static void cleanScope() {
		share.table.clear();
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return (prev == null ? "" : (prev + ",")) + table;
	}
}
