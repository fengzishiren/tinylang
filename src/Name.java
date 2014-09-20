
public class Name extends Node {
	
	public static final Name Null = new Name(null);
	public static final Name Lambda = new Name("lambda");
	
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
