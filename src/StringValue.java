

public class StringValue extends PrimValue {

	public String value;

	public StringValue(String value) {
		super();
		this.value = value;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "\"" + value + "\"";
	}
}
