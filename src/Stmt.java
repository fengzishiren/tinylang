public abstract class Stmt extends Node {
	public Stmt(Position pos) {
		super(pos);
	}
	public static Stmt Null = new Null();
	public static Stmt End = new End();
	public static Stmt Enclosing = End;

	@Override
	public abstract Value interp(Scope s);

}
