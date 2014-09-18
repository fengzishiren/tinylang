public abstract class Node {

	/**
	 * 
	 *  -3, -8, +3 => 0 - 3, 0 - 8, 0 + 3
	 */
	public static final Node zero = new Zero();

	public abstract Value interp(Scope s);

	public static Value interp(Node node, Scope s) {
		return node.interp(s);
	}

	public abstract Value typecheck(Scope s);

}
