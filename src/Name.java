public class Name extends Node {

	public static final Name Null = new Name(null);
	public static final Name Lambda = new Name("lambda");

	public String id;

	private Name(String id) {
		super(Position.IGNORE);
		this.id = id;
	}

	public Name(Position pos, String id) {
		super(pos);
		this.id = id;
	}

	public Value interp(Scope s) {
		Value v = s.lookup(id);
		if (v != null) {
			return v;
		} else {
			S.error(pos, "找不到定义： " + id);
			return Value.NULL;
		}
	}

	public String toString() {
		return id;
	}

}
