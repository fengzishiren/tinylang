public abstract class Stmt extends Node {

	public static Stmt Null = new Null();
	public static Stmt Enclosing = Null;

	@Override
	public abstract Value interp(Scope s);

	@Override
	public abstract Value typecheck(Scope s);

	@Override
	public String toString() {
		return "";
	}
}
