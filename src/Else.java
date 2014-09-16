

public class Else extends Stmt {
	public Node test;
	public Node then;
	public Node other;

	public Else(Node test, Node then, Node other) {
		super();
		this.test = test;
		this.then = then;
		this.other = other;
	}
}
