
public class Token {
	public int tag;
	public Object content = "";

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
		return String.format("<%s, %s>", Tag.toString(tag), content.toString());
		// return content == null ? "" : content.toString();
	}
}
