public class Or extends Node {

	public Node left, right;
	public int op;

	public Or(int op, Node left, Node right) {
		this.op = op;
		this.left = left;
		this.right = right;
	}

	@Override
	public Value interp(Scope s) {
		Value lv = left.interp(s);
		Value rv = right.interp(s);

		if (lv instanceof BoolValue && rv instanceof BoolValue) {
			return new BoolValue(((BoolValue) lv).value
					|| ((BoolValue) rv).value);
		}
		S.error("Type match error!");
		return Value.FALSE; // never touch
	}


	@Override
	public String toString() {
		return left + " " + Tag.descOf(op) + " " + right;
	}
}
