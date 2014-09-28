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
	private int row, col;

	public Lexer(String s) {
		text = s;
	}

	private static Map<String, Integer> reserves = new HashMap<String, Integer>();
	static {
		reserves.put("define", Tag.DEFINE);
		reserves.put("if", Tag.IF);
		reserves.put("else", Tag.ELSE);
		reserves.put("while", Tag.WHILE);
		reserves.put("for", Tag.FOR);
		reserves.put("break", Tag.BREAK);
		reserves.put("do", Tag.DO);
		reserves.put("true", Tag.TRUE);
		reserves.put("false", Tag.FALSE);
		reserves.put("return", Tag.RETURN);
		reserves.put("foreach", Tag.FOREACH);
		reserves.put("in", Tag.IN);
		reserves.put("lambda", Tag.LAMBDA);
		reserves.put("continue", Tag.CONTINUE);
		reserves.put("null", Tag.NULL);
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

	private boolean lookequal(int n, char c) {
		return offset + n < text.length() && text.charAt(offset + n) == c;
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
		if (lookequal('/') && lookequal(1, '/')) {
			forward();
			do
				forward();
			while (!lookequal('\n'));
		}
		return offset != old;
	}

	private void forward() {
		if (text.charAt(offset++) == '\n') {
			row++;
			col = 0;
		} else
			col++;
	}

	private char peek() {
		return text.charAt(offset);
	}

	public Token scan() {
		while (skipSpace() || skipComment())
			;
		if (text.length() == offset)
			return Token.EOF;
		int thisRow = row, thisCol = col;
		switch (peek()) {
		case '&':
			forward();
			if (lookequal('&')) {
				forward();
				return new Token(Tag.AND, thisRow, thisCol);
			} else
				return new Token('&', thisRow, thisCol);
		case '|':
			forward();
			if (lookequal('|')) {
				forward();
				return new Token(Tag.OR, thisRow, thisCol);
			} else
				return new Token('|', thisRow, thisCol);
		case '=':
			forward();
			if (lookequal('=')) {
				forward();
				return new Token(Tag.EQ, thisRow, thisCol);
			} else
				return new Token('=', thisRow, thisCol);
		case '!':
			forward();
			if (lookequal('=')) {
				forward();
				return new Token(Tag.NE, thisRow, thisCol);
			} else
				return new Token('!', thisRow, thisCol);
		case '<':
			forward();
			if (lookequal('=')) {
				forward();
				return new Token(Tag.LE, thisRow, thisCol);
			} else
				return new Token('<', thisRow, thisCol);
		case '>':
			forward();
			if (lookequal('=')) {
				forward();
				return new Token(Tag.GE, thisRow, thisCol);
			} else
				return new Token('>', thisRow, thisCol);
		}
		if (Character.isDigit(peek())) {
			int v = 0;
			do {
				v = v * 10 + (peek() - '0');
				forward();
			} while (offset != text.length() && Character.isDigit(peek()));
			if (!lookequal('.')) {
				return new Token(Tag.INT, v, thisRow, thisCol);
			}
			float x = v;
			float d = 10;
			forward();
			while (offset != text.length() && Character.isDigit(peek())) {
				x = x + Character.digit(peek(), 10) / d;
				d *= 10;
				forward();
			}
			return new Token(Tag.FLOAT, x, thisRow, thisCol);
		}
		if (lookequal('"') || lookequal('\'')) {
			StringBuilder buf = new StringBuilder();
			do {
				forward();
				if (lookequal('\\')) {// escape character
					forward();
					if (offset != text.length()) {
						Character c = U.unescape(text.charAt(offset));
						buf.append(c);
					}
				} else if (offset != text.length() && (peek() != '"'))
					buf.append(peek());
				else
					break;
			} while (true);
			if (offset != text.length())
				forward();
			return new Token(Tag.STRING, buf.toString(), thisRow, thisCol); // note:
		}
		if (Character.isLetter(peek()) || peek() == '_') {
			int start = offset;
			do {
				forward();
			} while (offset != text.length()
					&& (Character.isLetterOrDigit(peek()) || peek() == '_'));
			String word = text.substring(start, offset);
			Integer tag = reserves.get(word);
			return new Token(tag == null ? Tag.ID : tag, word, thisRow, thisCol);
		}
		Token to = new Token(peek(), thisRow, thisCol);
		forward();
		return to;
	}

	public static void main(String[] args) throws IOException, IOException {
		Lexer lexer = new Lexer(U.readFile("langs/error.tl"));
		Token tok;
		while ((tok = lexer.scan()) != Token.EOF) {
			System.out.println(tok);
		}
	}
}
