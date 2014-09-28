public class Token {
	public static final Token EOF = new Token(Tag.EOF, Position.EOF);// avoid null
																// pointer
	public int tag;
	public Object content = "";

	public Position pos;

	private Token(int tag, Position pos) {
		this.tag = tag;
		this.pos = pos;
	}

	public Token(int tag, int row, int col) {
		this.tag = tag;
		this.pos = new Position(row, col);
	}

	public Token(int tag, Object content, int row, int col) {
		super();
		this.tag = tag;
		this.content = content;
		this.pos = new Position(row, col);
	}

	@Override
	public String toString() {
		return String.format("<%s, %s> %s", Tag.toString(tag),
				content.toString(), pos);
	}
}
