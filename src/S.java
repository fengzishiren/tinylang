public class S {

	public static void error(Token tok, String format, Object... args) {
		System.err.println(String.format(format, args));
		Thread.dumpStack();
		System.out.println(tok.col + ", " + tok.row);

	}
}
