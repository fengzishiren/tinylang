

public class Stmt extends Node {

	public static Stmt Null = new Stmt();
	public static Stmt Enclosing = Null;

	@Override
	public Value interp(Scope s) {
		return null;
	}

	@Override
	public Value typecheck(Scope s) {
		return null;
	}
}
