

import java.util.List;

public class Argument {
	public List<Node> elements;

	public Argument(List<Node> elements) {
		this.elements = elements;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (Node e : elements) {
			if (!first) {
				sb.append(" ");
			}
			sb.append(e);
			first = false;
		}
		return sb.toString();
	}

}
