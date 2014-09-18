
public class FloatValue extends PrimValue {
	public float value;

	public FloatValue(float value) {
		this.value = value;
	}

	public String toString() {
		return Double.toString(value);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof FloatValue ? new Double(value)
				.equals(((FloatValue) obj).value) : false;
	}

	@Override
	public int hashCode() {
		return Double.hashCode(value);
	}

	@Override
	public Type type() {
		return Type.Float;
	}

}
