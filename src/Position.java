public class Position {
	public int row, col;
	public static final Position START = new Position(0, 0);
	public static final Position EOF = new Position(-1, -1);
	public static final Position IGNORE = EOF;
	public Position(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return row == -1 || col == -1 ? "EOF" : ++row + ":" + ++col;
	}
}
