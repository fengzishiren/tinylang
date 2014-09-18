
public class BoolValue extends PrimValue {
	public boolean value;

	public BoolValue(boolean value) {
		this.value = value;
	}

	public String toString() {
		return value ? "true" : "false";
	}

	@Override
	public Type type() {
		return Type.BOOL;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof BoolValue ? value == (((BoolValue) obj).value)
				: false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Boolean.hashCode(value);
	}

}
