import java.util.ArrayList;
import java.util.Iterator;

public class ListValue extends ComplexValue implements Iterable<Value> {
	public ArrayList<Value> values = new ArrayList<>();

	public ListValue(ArrayList<Value> values) {
		super();
		this.values = values;
	}

	public Value get(IntValue index, Position pos) {
		check(index.value, values.size(), pos);
		return values.get(index.value);
	}

	public Value get(int index, Position pos) {
		check(index, values.size(), pos);
		return values.get(index);
	}

	public void remove(IntValue index, Position pos) {
		check(index.value, values.size(), pos);
		values.remove(index.value);
	}

 

	public void append(Value v, Position pos) {
		values.add(v);
	}

	// x[] = v
	public void set(IntValue index, Value v, Position pos) {
		values.set(index.value, v);
	}

	private void check(int index, int size, Position pos) {
		if (index < 0 || index >= size) {
			S.error(pos, "list下标越界: %d", index);
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
