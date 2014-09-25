import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class DictValue extends ComplexValue implements Iterable<Entry<PrimValue, Value>>{
	public Map<PrimValue, Value> dict = new HashMap<>();

	public DictValue(Map<PrimValue, Value> dict) {
		super();
		this.dict = dict;
	}

	//x[] = v
	public Value get(PrimValue pv, Position pos) {
		Value ret = dict.get(pv);
		return ret == null ? Value.NULL : ret;
	}

	public Value remove(PrimValue pv, Position pos) {
		Value rm = dict.remove(pv);
		return rm == null ? Value.NULL : rm;
	}

	@Override
	public String toString() {
		return dict.toString();
	}

	@Override
	public Type type() {
		return Type.Dict;
	}

	@Override
	public IntValue size() {
		return new IntValue(dict.size());
	}

	public void put(PrimValue key, Value e) {
		dict.put(key, e);
	}

	@Override
	public Iterator<Entry<PrimValue, Value>> iterator() {
		return dict.entrySet().iterator();
	}

}
