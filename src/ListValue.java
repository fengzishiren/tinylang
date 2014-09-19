import java.util.ArrayList;
import java.util.Iterator;

public class ListValue extends ComplexValue implements Iterable<Value> {
	public ArrayList<Value> values = new ArrayList<>();

	public ListValue(ArrayList<Value> values) {
		super();
		this.values = values;
	}

	public Value get(IntValue index) {
		check(index.value, values.size());
		return values.get(index.value);
	}

	public Value get(int index) {
		check(index, values.size());
		return values.get(index);
	}

	public void remove(IntValue index) {
		check(index.value, values.size());
		values.remove(index.value);
	}

	public void remove(Value v) {
		values.remove(v);
	}

	public void append(Value v) {
		values.add(v);
	}

	// x[] = v
	public void set(IntValue index, Value v) {
		values.set(index.value, v);
	}

	private void check(int index, int size) {
		if (index < 0 || index >= size) {
			S.error("list下标越界: %d", index);
		}
	}

	@Override
	public String toString() {
		return values.toString();
	}

	@Override
	public Iterator<Value> iterator() {
		return values.iterator();
	}

	@Override
	public Type type() {
		return Type.List;
	}

	@Override
	public IntValue size() {
		return new IntValue(values.size());
	}

}
