/**
 * eg:
 * 
 * this Seq (id=20) stmt1 Assign (id=26) name Name (id=30) value Int (id=32)
 * stmt2 Seq (id=38) stmt1 Call (id=40) args Argument (id=42) op Name (id=44)
 * stmt2 Seq (id=41) stmt1 Return (id=45) opexpr Name (id=49) stmt2 Null (id=47)
 * 
 * @author lunatic
 */
public class Seq extends Stmt {

	private Stmt stmt1;
	private Stmt stmt2;

	public Seq(Position pos, Stmt stmt1, Stmt stmt2) {
		super(pos);
		this.stmt1 = stmt1;
		this.stmt2 = stmt2;
	}

	@Override
	public Value interp(Scope s) {
		stmt1.interp(s);
		stmt2.interp(s);
		return Value.VOID; //没有return语句则默认返回void
	}

	@Override
	public String toString() {

		return U.join(" ", stmt1, stmt2);
	}

}
