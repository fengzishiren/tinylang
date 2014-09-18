
/**
 * 实参
 * 
 * @author lunatic
 *
 */
public class Argument extends List {
 
	public void addArg(Node arg) {
		this.addNode(arg);
	}

	public static Argument noArgs() {
		return new Argument( );
	}

	public String toString() {
		return U.join(", ", nodes);
	}
 
}
