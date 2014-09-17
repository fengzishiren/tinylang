import java.util.HashMap;
import java.util.Map;


public class DictValue extends ComplexValue {
	public Map<PrimValue, Value> dict = new HashMap<>();

	public DictValue(Map<PrimValue, Value> dict) {
		super();
		this.dict = dict;
	}
	
	@Override
	public String toString() {
		return dict.toString();
	}
	
}
