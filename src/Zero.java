
public class Zero extends Node {

	@Override
	public Value interp(Scope s) {
		return new IntValue(0);
	}

	@Override
	public Value typecheck(Scope s) {
		return Type.INT;
	}
	
	@Override
	public String toString() {
		return "0";
	}

}
