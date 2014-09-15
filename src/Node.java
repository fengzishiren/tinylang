import java.util.ArrayList;
import java.util.List;

public class Node {
	public Token token;
	public List<Node> children = new ArrayList<>();

	public Node(Token token) {
		this.token = token;
	}
	public Node(String func) {
		this.token = new Token(Tag.FUNC, func, 0, 0);
	}

	public Node() {
		this.token = new Token(Tag.START, "start", 0, 0);
	}
	
	public Node(int tag, String string) {
		this.token = new Token(tag, string, 0, 0);
	}

	public static final Node funcNode = new Node(Tag.FUNC, "function");

	public String text() {
		return token.content.toString();
	}

	public int type() {
		return token.tag;
	}

	public boolean isNull() {
		return token == null;
	}

	public void addChild(Node node) {
		children.add(node);
	}
	
	@Override
	public String toString() {
		return String.valueOf(token);
	}

	public String toStringTree() {
		StringBuilder sb = new StringBuilder();

		if (children.isEmpty())
			return this.toString();

		if (!isNull()) {
			sb.append('(');
			sb.append(this);
			sb.append(' ');
		}

		for (Node node : children) {
			sb.append(' ').append(node.toStringTree());
		}

		if (!isNull()) {
			sb.append(')');
		}
		return sb.toString();
	}
}
