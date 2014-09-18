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
		Value lv = left.interp(s);
		Value rv = right.interp(s);
		if (lv instanceof StringValue && rv instanceof StringValue) {
			if (op != '+')
				S.error("String不支持操作: " + (char) op);
			String l = ((StringValue) lv).value;
			String r = ((StringValue) rv).value;
			return new StringValue(l + r);
		}
		if (lv instanceof IntValue && rv instanceof IntValue) {
			int l = ((IntValue) lv).value;
			int r = ((IntValue) rv).value;
			return new IntValue(op(l, r));
		}
		if (lv instanceof FloatValue && rv instanceof FloatValue) {
			float l = ((FloatValue) lv).value;
			float r = ((FloatValue) rv).value;
			return new FloatValue(op(l, r));
		}
		if (lv instanceof FloatValue && rv instanceof IntValue) {
			float l = ((FloatValue) lv).value;
			int r = ((IntValue) rv).value;
			return new FloatValue(op(l, r));
		}
		if (lv instanceof IntValue && rv instanceof FloatValue) {
			int l = ((IntValue) lv).value;
			float r = ((FloatValue) rv).value;
			return new FloatValue(op(l, r));
		}

		S.error("类型不匹配的操作： " + this);
		return Value.VOID; //never touch
	}

	private int op(int l, int r) {
		int ret = 0;
		switch (op) {
		case '+':
			ret = l + r;
			break;
		case '-':
			ret = l - r;
			break;
		case '*':
			ret = l * r;
			break;
		case '/':
			ret = l / r;
			break;
		default:
			S.error("不支持操作符: " + (char) op);
		}
		return ret;
	}

	private float op(float l, float r) {
		float ret = 0;
		switch (op) {
		case '+':
			ret = l + r;
			break;
		case '-':
			ret = l - r;
			break;
		case '*':
			ret = l * r;
			break;
		case '/':
			ret = l / r;
			break;
		default:
			S.error("不支持操作符: " + (char) op);
		}
		return ret;
	}

	@Override
	public Value typecheck(Scope s) {
		Value lv = left.interp(s);
		Value rv = right.interp(s);
		if (lv instanceof StringValue && rv instanceof StringValue) {
			if (op != '+')
				S.error("String不支持操作: " + (char) op);
		}
		if (lv instanceof FloatValue && rv instanceof FloatValue) {
			return Type.Float;
		}
		if ((lv instanceof IntValue && rv instanceof IntValue)) {
			return Type.INT;
		}
		if ((lv instanceof IntValue && rv instanceof FloatValue)
				|| (lv instanceof FloatValue && rv instanceof IntValue)) {
			return Type.Float;
		}
		S.error("类型不匹配的操作： " + (char) op);
		return Value.ANY;
	}

	@Override
	public String toString() {
		return left + " " + Tag.descOf(op) + " " + right;
	}

}
