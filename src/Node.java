

public abstract class Node {

	public abstract Value interp(Scope s);
	
	public static Value interp(Node node, Scope s) {
		return node.interp(s);
	}
	
	public abstract Value typecheck(Scope s);

}
