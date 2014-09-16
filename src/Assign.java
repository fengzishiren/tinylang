

public class Assign extends Stmt {
	public Name name;
	public Node value;

	public Assign(Name name, Node value) {
		this.name = name;
		this.value = value;
	}
	
	@Override
	public Value interp(Scope s) {
		Value vv = value.interp(s);
		Binder.assign(name, vv, s);
		return Value.VOID;
	}
	
	@Override
	public Value typecheck(Scope s) {
		Value vt = value.typecheck(s);
		Binder.assign(name, vt, s);
		return Value.VOID;

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " = " + value;
	}

}
