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

	public Seq(Stmt stmt1, Stmt stmt2) {
		super();
		this.stmt1 = stmt1;
		this.stmt2 = stmt2;
	}

	@Override
	public Value interp(Scope s) {
		Value v1 = stmt1.interp(s);
		if (stmt1 instanceof Return)
			return v1;
		if (stmt2 == Stmt.Null)
			return v1;
		return stmt2.interp(s);
	}

	@Override
	public Value typecheck(Scope s) {
		Value t1 = stmt1.typecheck(s);
		if (stmt1 instanceof Return)
			return t1;
		if (stmt2 == Stmt.Null)
			return t1;
		return stmt1.typecheck(s);
	}

	@Override
	public String toString() {

		return U.join(" ", stmt1, stmt2);
	}

}
