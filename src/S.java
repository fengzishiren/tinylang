public class S {
	public static void error(Token tok, String msg) {
		throw new SyntaxException(tok, msg);
	}

	public static void error(String msg) {
		throw new SyntaxException(msg);
	}

	public static void error(String msg, Object... args) {
		throw new SyntaxException(String.format(msg, args));
	}
}
