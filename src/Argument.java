import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 实参
 * 
 * @author lunatic
 *
 */
public class Argument {
	public List<Node> elements;

	public Argument(List<Node> elements) {
		this.elements = elements;
	}

	public Argument() {
		this.elements = new ArrayList<>();
	}

	public void addArg(Node arg) {
		this.elements.add(arg);
	}

	public static Argument noArgs() {
		 List<Node> empty = Collections.emptyList();
		return new Argument(empty);
	}

	public String toString() {
		return U.join(", ", elements);
	}

	public int size() {
		return elements.size();
	}

	public Node get(int index) {
		return elements.get(index);
	}
}
