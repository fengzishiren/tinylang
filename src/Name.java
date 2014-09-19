
public class Name extends Node {
	public String id;

	public Name(String id) {
		this.id = id;
	}

	public Value interp(Scope s) {
		Value v = s.lookup(id);
		if (v != null) {
			return v;
		} else {
			S.error("找不到定义： " + id);
			return Value.NULL;
		}
	}

	public String toString() {
		return id;
	}
}
