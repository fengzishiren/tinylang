public class S {
	public static void error(String msg) {
		throw new SyntaxException(msg);
	}

	public static void error(Position pos, String msg) {
		throw new SyntaxException(pos + " " + msg);
	}

	public static void error(Position pos, String msg, Object... args) {
		error(pos, String.format(msg, args));
	}
}
