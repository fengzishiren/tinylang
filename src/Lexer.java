import java.io.FileNotFoundException;
import java.io.FileReader;
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

	public void load(String s) {
		text = s;
		offset = row = col = 0;
	}

	private Map<String, Integer> reserves = new HashMap<String, Integer>();
	{
		reserves.put("define", Tag.DEFINE);
		reserves.put("if", Tag.IF);
		reserves.put("while", Tag.WHILE);
		reserves.put("break", Tag.BREAK);
		reserves.put("do", Tag.DO);
		reserves.put("true", Tag.TRUE);
		reserves.put("false", Tag.FALSE);

	}

	private boolean skipSpace() {
		int oldPos = offset;
		while (text.length() != offset
				&& Character.isWhitespace(text.charAt(offset)))
			forward();
		return oldPos != offset;
	}

	private boolean skipComment() {
		int old = offset;
		if (offset != text.length() && text.charAt(offset) == '#')
			do
				forward();
			while (offset != text.length() && text.charAt(offset) != '\n');
		return offset != old;
	}

	private void forward() {
		if (text.charAt(offset++) == '\n') {
			row++;
			col = 0;
		} else
			col++;
	}

	private boolean lookequal(char c) {
		return offset != text.length() && text.charAt(offset) == c;
	}

	private char peek() {
		return text.charAt(offset);
	}

	public Token scan() {
		while (skipSpace() || skipComment())
			;
		if (text.length() == offset)
			return null;

		int col = this.col, row = this.row;
		switch (peek()) {
		case '&':
			forward();
			if (lookequal('&')) {
				forward();
				return new Token(Tag.AND, "&&", col, row);
			} else
				return new Token('&', "&", col, row);
		case '|':
			forward();
			if (lookequal('|')) {
				forward();
				return new Token(Tag.OR, "||", col, row);
			} else
				return new Token('|', "|", col, row);
		case '=':
			forward();
			if (lookequal('=')) {
				forward();
				return new Token(Tag.EQ, "==", col, row);
			} else
				return new Token('=', "=", col, row);
		case '!':
			forward();
			if (lookequal('=')) {
				forward();
				return new Token(Tag.NE, "!=", col, row);
			} else
				return new Token('!', "!", col, row);
		case '<':
			forward();
			if (lookequal('=')) {
				forward();
				return new Token(Tag.LE, "<=", col, row);
			} else
				return new Token('<', "<", row, col);
		case '>':
			forward();
			if (lookequal('=')) {
				forward();
				return new Token(Tag.GE, ">=", col, row);
			} else
				return new Token('>', ">", col, row);
		case ';':
			forward();
			return new Token(Tag.NL, "\\n", col, row);
		}
		if (Character.isDigit(peek())) {
			int v = 0;
			do {
				v = v * 10 + (peek() - '0');
				forward();
			} while (offset != text.length() && Character.isDigit(peek()));
			if (!lookequal('.')) {
				return new Token(Tag.INT, v + "", row, col);
			}
			/*
			 * float x = v; float d = 10; forward(); while (offset !=
			 * text.length() && Character.isDigit(peek())) { x = x +
			 * Character.digit(peek(), 10) / d; d *= 10; forward(); } return new
			 * REAL(x, row, col);
			 */
		}
		if (lookequal('"')) {
			int start = offset + 1;
			do {
				forward();
			} while (offset != text.length() && (peek() != '"'));
			if (offset != text.length())
				forward();
			return new Token(Tag.ID, text.substring(start, offset), row, col);
		}
		if (Character.isLetter(peek()) || peek() == '_') {
			int start = offset;
			do {
				forward();
			} while (offset != text.length()
					&& (Character.isLetterOrDigit(peek()) || peek() == '_'));
			String word = text.substring(start, offset);
			Integer tag = reserves.get(word);
			return new Token(tag == null ? Tag.ID : tag, word, row, col);
		}
		char c = peek();

		Token to = new Token(c, c + "", row, col);
		forward();
		return to;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Lexer lexer = new Lexer();
		lexer.load(U.read(new FileReader("example.sl")));
		
		Token token;
		while ((token = lexer.scan()) != null) {
			System.out.println(token);
		}
	}
}
