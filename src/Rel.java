public class Rel extends Node {

	public Node left, right;
	public int op;

	public Rel(int op, Node left, Node right) {
		this.op = op;
		this.left = left;
		this.right = right;
	}

	@Override
	public Value interp(Scope s) {
		Value lv = left.interp(s);
		Value rv = right.interp(s);

		if (lv instanceof StringValue && rv instanceof StringValue) {
			String l = ((StringValue) lv).value;
			String r = ((StringValue) rv).value;
			return new BoolValue(op(l.compareTo(r), 0));
		}
		if (lv instanceof IntValue && rv instanceof IntValue) {
			int l = ((IntValue) lv).value;
			int r = ((IntValue) rv).value;
			return new BoolValue(op(l, r));
		}
		if (lv instanceof FloatValue && rv instanceof FloatValue) {
			int l = (int) ((FloatValue) lv).value;
			int r = (int) ((FloatValue) rv).value;
			return new BoolValue(op(l, r));
		}
		return Value.FALSE;
	}

	private boolean op(int l, int r) {
		boolean ret = false;
		switch (op) {
		case '>':
			ret = l > r;
			break;
		case '<':
			ret = l < r;
			break;
		case Tag.EQ:
			ret = l == r;
			break;
		case Tag.NE:
			ret = l != r;
			break;
		case Tag.LE:
			ret = l <= r;
			break;
		case Tag.GE:
			ret = l >= r;
			break;
		}
		return ret;
	}

	@Override
	public Value typecheck(Scope s) {
		Value lv = left.interp(s);
		Value rv = right.interp(s);
		if ((lv instanceof FloatValue && rv instanceof FloatValue)
				|| (lv instanceof IntValue && rv instanceof IntValue)
				|| (lv instanceof StringValue && lv instanceof StringValue))
			return Type.BOOL;
		S.error("Type math error!");
		return null;
	}
}
