import java.util.ArrayList;

public class ListValue extends ComplexValue {
	public ArrayList<Value> values = new ArrayList<>();

	public ListValue(ArrayList<Value> values) {
		super();
		this.values = values;
	}
	
	@Override
	public String toString() {
		return values.toString();
	}
	
}
