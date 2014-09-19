import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author lunatic 2014年9月6日
 */
public class Lexer {

	private String text;
	private int offset;

	public Lexer(String s) {
		text = s;
	}

	private Map<String, Integer> reserves = new HashMap<String, Integer>();
	{
		reserves.put("define", Tag.DEFINE);
		reserves.put("if", Tag.IF);
		reserves.put("else", Tag.ELSE);
		reserves.put("while", Tag.WHILE);
		reserves.put("for", Tag.For);
		reserves.put("break", Tag.BREAK);
		reserves.put("do", Tag.DO);
		reserves.put("true", Tag.TRUE);
		reserves.put("false", Tag.FALSE);
		reserves.put("return", Tag.RETURN);
		reserves.put("null", Tag.NULL);
		reserves.put("foreach", Tag.Foreach);
		reserves.put("in", Tag.IN);
	}

	private boolean skipSpace() {
		int oldPos = offset;
		while (text.length() != offset
				&& Character.isWhitespace(text.charAt(offset)))
			forward();
		return oldPos != offset;
	}

	private boolean lookequal(char c) {
		return offset != text.length() && text.charAt(offset) == c;
	}

	/**
	 * comment 2 style: #xxxx //xxxxxx
	 * 
	 * @return
	 */
	private boolean skipComment() {
		int old = offset;
		if (lookequal('#'))
			do
				forward();
			while (!lookequal('\n'));
		if (lookequal('/')) {
			forward();
			if (lookequal('/'))
				do
					forward();
				while (!lookequal('\n'));
		}
		return offset != old;
	}

	private void forward() {
		if (text.charAt(offset++) == '\n') {
		} else
			;
	}

	private char peek() {
		return text.charAt(offset);
	}

	public Token scan() {
		while (skipSpace() || skipComment())
			;
		if (text.length() == offset)
			return null;

		switch (peek()) {
		case '&':
			forward();
			if (lookequal('&')) {
				forward();
				return new Token(Tag.AND);
			} else
				return new Token('&');
		case '|':
			forward();
			if (lookequal('|')) {
				forward();
				return new Token(Tag.OR);
			} else
				return new Token('|');
		case '=':
			forward();
			if (lookequal('=')) {
				forward();
				return new Token(Tag.EQ);
			} else
				return new Token('=');
		case '!':
			forward();
			if (lookequal('=')) {
				forward();
				return new Token(Tag.NE);
			} else
				return new Token('!');
		case '<':
			forward();
			if (lookequal('=')) {
				forward();
				return new Token(Tag.LE);
			} else
				return new Token('<');
		case '>':
			forward();
			if (lookequal('=')) {
				forward();
				return new Token(Tag.GE);
			} else
				return new Token('>');
		}
		if (Character.isDigit(peek())) {
			int v = 0;
			do {
				v = v * 10 + (peek() - '0');
				forward();
			} while (offset != text.length() && Character.isDigit(peek()));
			if (!lookequal('.')) {
				return new Token(Tag.INT, v);
			}
			float x = v;
			float d = 10;
			forward();
			while (offset != text.length() && Character.isDigit(peek())) {
				x = x + Character.digit(peek(), 10) / d;
				d *= 10;
				forward();
			}
			return new Token(Tag.FLOAT, x);
		}
		if (lookequal('"')) {
			int start = offset + 1;
			do {
				forward();
			} while (offset != text.length() && (peek() != '"'));
			if (offset != text.length())
				forward();
			return new Token(Tag.STRING, text.substring(start, offset - 1)); // note:
																				// "
		}
		if (Character.isLetter(peek()) || peek() == '_') {
			int start = offset;
			do {
				forward();
			} while (offset != text.length()
					&& (Character.isLetterOrDigit(peek()) || peek() == '_'));
			String word = text.substring(start, offset);
			Integer tag = reserves.get(word);
			return new Token(tag == null ? Tag.ID : tag, word);
		}
		Token to = new Token(peek());
		forward();
		return to;
	}

	public static void main(String[] args) throws IOException, IOException {
		Lexer lexer = new Lexer(U.readFile("example.sl"));
		Token tok;
		while ((tok = lexer.scan()) != null) {
			System.out.println(tok);
		}
	}
}
