
public class Token {
	public final int tag;

	public final Object content;
	int row, col;

	public int row() {
		return row;
	}

	public int col() {
		return col;
	}

	public Token(int tag, String content, int row, int col) {
		this.tag = tag;
		this.row = row;
		this.col = col;
		this.content = content;
	}

	@Override
	public String toString() {
		return content.toString();
	}
}
