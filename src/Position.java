public class Position {
	public static final Position IGNORE = new Position(-1, -1);
	public static final Position EOF = new Position(-1, -1);
	public int row, col;

	public Position(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return this == EOF ? "EOF"
				: (this == IGNORE ? "" : ++row + ":" + ++col);
	}
}
