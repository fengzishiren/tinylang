
public class BoolValue extends PrimValue {
	public boolean value;

	public BoolValue(boolean value) {
		this.value = value;
	}

	public String toString() {
		//Boolean.toString(value)
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
		return Boolean.hashCode(value);
	}

}
