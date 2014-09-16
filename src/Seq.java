

public class Seq extends Stmt {

	private Stmt stmt1;
	private Stmt stmt2;

	public Seq(Stmt stmt1, Stmt stmt2) {
		super();
		this.stmt1 = stmt1;
		this.stmt2 = stmt2;
	}

}
