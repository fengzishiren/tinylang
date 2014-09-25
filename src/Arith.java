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
		//字符串连接操作： 任意类型和string都可自动连接
		if (lv instanceof StringValue || rv instanceof StringValue) {
			if (op != '+')
				S.error("%s String连接不支持操作: %s", this, Tag.toString(op));
			return new StringValue(lv + "" + rv);
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
		return Value.VOID; // never touch
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
			if (r == 0x00)
				S.error("除0错误:" + this);
			ret = l / r;
			break;
		default:
			S.error("不支持操作符: " + Tag.toString(op));
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
			S.error("不支持操作符: " + Tag.toString(op));
		}
		return ret;
	}

	@Override
	public String toString() {
		return left + " " + Tag.toString(op) + " " + right;
	}

}
