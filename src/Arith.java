

public class Arith extends Node {
	public Node left, right;
	public int op;

	public Arith(Node left, int op, Node right) {
		super();
		this.left = left;
		this.op = op;
		this.right = right;
	}

	@Override
	public Value interp(Scope s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value typecheck(Scope s) {
		// TODO Auto-generated method stub
		return null;
	}

}
