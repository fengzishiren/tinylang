/**
 * 实参
 * 
 * @author lunatic
 *
 */
public class Argument extends List {

	public Argument(Position pos) {
		super(pos);
	}

	public void addArg(Node arg) {
		this.addNode(arg);
	}

	public void addFirst(Node arg) {
		this.nodes.add(0, arg);
	}

	public static Argument noArgs(Position pos) {
		return new Argument(pos);
	}

	public String toString() {
		return U.join(", ", nodes);
	}

}
