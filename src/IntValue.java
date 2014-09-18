
public class IntValue extends PrimValue {
	public int value;

	public IntValue(int value) {
		this.value = value;
	}

	public String toString() {
		return Integer.toString(value);
	}

	public boolean equals(IntValue obj) {
		return value == obj.value;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof IntValue ? value == (((IntValue) obj).value)
				: false;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(value);
	}

}
