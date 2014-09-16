

import java.util.LinkedHashMap;
import java.util.Map;

public class Scope {

	public Map<String, Object> table = new LinkedHashMap<>();

	public Scope prev;

	public Scope() {
	}

	public Scope(Scope s) {
		prev = s;
	}

	public void put(String name, Object value) {

		table.put(name, value);
	}

	public Value get(String name) {
		return lookup(name);
	}

	/**
	 * 
	 * value = "value" name ->map-> map.get(value)
	 * 
	 * @param name
	 * @return
	 */
	public Value lookup(String name) {
		Object v = lookupProperty(name, "value");
		if (v == null)
			return null;
		else if (v instanceof Value)
			return (Value) v;
		else {
			_.abort("value is not a Value, shouldn't happen: " + v);
			return null;
		}
	}

	public Value lookupLocal(String name) {
		Object v = lookupPropertyLocal(name, "value");
		if (v == null)
			return null;
		else if (v instanceof Value)
			return (Value) v;
		else {
			_.abort("value is not a Value, shouldn't happen: " + v);
			return null;
		}
	}

	public Value lookupType(String name) {
		Object v = lookupProperty(name, "type");
		if (v == null)
			return null;
		else if (v instanceof Value)
			return (Value) v;
		else {
			_.abort("value is not a Value, shouldn't happen: " + v);
			return null;
		}
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

	public Object lookupPropertyLocal(String name, String key) {
		return table.get(name);
	}
}
