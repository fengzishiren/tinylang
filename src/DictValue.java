import java.util.HashMap;
import java.util.Map;

public class DictValue extends ComplexValue {
	public Map<PrimValue, Value> dict = new HashMap<>();

	public DictValue(Map<PrimValue, Value> dict) {
		super();
		this.dict = dict;
	}

	public Value get(PrimValue pv) {
		return dict.get(pv);
	}

	@Override
	public String toString() {
		return dict.toString();
	}

	@Override
	public Type type() {
		// TODO Auto-generated method stub
		return Type.Dict;
	}

}
