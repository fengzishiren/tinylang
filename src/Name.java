


public class Name extends Node {
	public String id;

	public Name(String id) {
		this.id = id;
	}

	/**
	 * Generate a name without location info
	 */
	public static Name genName(String id) {
		return new Name(id );
	}

	public Value interp(Scope s) {
		return s.lookup(id);
	}

	@Override
	public Value typecheck(Scope s) {
		Value v = s.lookup(id);
		if (v != null) {
			return v;
		} else {
			_.abort(this, "unbound variable: " + id);
			return Value.VOID;
		}
	}

	public String toString() {
		return id;
	}
}
