import java.util.ArrayList;
import java.util.Iterator;

public class ListValue extends ComplexValue implements Iterable<Value> {
	public ArrayList<Value> values = new ArrayList<>();

	public ListValue(ArrayList<Value> values) {
		super();
		this.values = values;
	}
	
	public Value get(int index) {
		return values.get(index);
	}
	
	@Override
	public String toString() {
		return values.toString();
	}

	@Override
	public Iterator<Value> iterator() {
		return values.iterator();
	}
	
}
