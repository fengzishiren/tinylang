public class StringValue extends PrimValue {

	public String value;

	public StringValue(String value) {
		super();
		this.value = value;
	}

	@Override
	public String toString() {
		//return "\"" + value + "\"";
		return value;
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof StringValue ? value.equals(((StringValue) obj).value)
				: false;
	}
}
