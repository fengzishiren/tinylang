public abstract class Stmt extends Node {
	public static Stmt End = new End();
	public static Stmt Enclosing = End;

	@Override
	public abstract Value interp(Scope s);

	@Override
	public abstract Value typecheck(Scope s);

	@Override
	public String toString() {
		return "";
	}
}
