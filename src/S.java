public class S {

	public static void error(String msg) {
		System.err.println(msg);
		//Thread.dumpStack();
		//new SyntaxException(msg).printStackTrace();
		//System.exit(1);
		throw new SyntaxException(msg);
	}

 	public static void error(String msg, Object... args) {
 		error(String.format(msg, args));
 	}
}
