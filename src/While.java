

public class While extends Stmt {

	public Node test;
	public Node stmt;
	public While() {
	}
	public void init(Node test, Node stmt) {
		this.test = test;
		this.stmt = stmt;
	}
}
