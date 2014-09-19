public abstract class Node {
	public static Stmt Null = new Null();
	/**
	 * 
	 *  -3, -8, +3 => 0 - 3, 0 - 8, 0 + 3
	 */
	public static final Node zero = new Zero();

	public abstract Value interp(Scope s);

}
