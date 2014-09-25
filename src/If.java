/**
 * 
 * 
 * 拒绝Yoda: http://www.yinwang.org/blog-cn/2013/04/14/yoda-notation/
 * 
 * @author lunatic
 *
 */
public class If extends Stmt {
	public Node test;
	public Node stmt;

	public If(Position pos, Node test, Node stmt) {
		super(pos);
		this.test = test;
		this.stmt = stmt;
	}

	@Override
	public Value interp(Scope s) {
		Value vt = test.interp(s);
		//拒绝yoga表示法
		if (!(vt instanceof BoolValue))
			S.error(pos,"必须是boolean " + test);
		if (((BoolValue) vt).value) {
			stmt.interp(s);
		}
		return Value.VOID;
	}

	@Override
	public String toString() {
		return String.format("if (%s) %s", test, stmt);
	}

}
