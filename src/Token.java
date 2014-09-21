public class Token {
	public static final Token EOF = new Token(Tag.EOF);// avoid null pointer

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

	public String content() {
		if (Tag.EOF == this.tag) {
			S.error("unexpected %s", Tag.toString(Tag.EOF));
		}
		return content.toString();
	}

	@Override
	public String toString() {
		return String.format("<%s, %s>", Tag.toString(tag), content.toString());
		// return content == null ? "" : content.toString();
	}
}
