

public class VoidValue extends Value {
    public String toString() {
        return "void";
    }

	@Override
	public Type type() {
		return Type.VOID;
	}
}
