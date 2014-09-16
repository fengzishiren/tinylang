

public class SyntaxException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7621710004248268852L;

	public SyntaxException(Token tok, String msg) {
		super(tok + msg);
	}

	public SyntaxException(String msg) {
		super(msg);
	}

}
