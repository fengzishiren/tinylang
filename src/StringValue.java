

public class StringValue extends Value {

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
