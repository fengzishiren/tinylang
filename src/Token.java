

public class Token {
	public int tag;
	public Object content;

	public Token(int tag) {
		this.tag = tag;

	}

	public Token(int tag, Object content) {
		super();
		this.tag = tag;
		this.content = content;
	}

	@Override
	public String toString() {
		//return String.format("<%c, %s>", (char)tag, content == null ? "" : content.toString());
		return content == null ? "" : content.toString();
	}
}
