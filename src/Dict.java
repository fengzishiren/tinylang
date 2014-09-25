import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Dict extends Node {
	public Map<Node, Node> nodeMap = new HashMap<>();

	public Dict(Position pos) {
		super(pos);
	}

	public void addKV(Node key, Node val) {
		nodeMap.put(key, val);
	}

	@Override
	public Value interp(Scope s) {
		Set<Entry<Node, Node>> se = nodeMap.entrySet();
		Map<PrimValue, Value> dict = new HashMap<>();
		for (Entry<Node, Node> e : se) {
			Value kV = e.getKey().interp(s);
			if (!(kV instanceof PrimValue))
				S.error(pos, "不能作为dict的key: " + kV);
			Value vV = e.getValue().interp(s);
			dict.put((PrimValue) kV, vV);
		}
		return new DictValue(dict);
	}

	@Override
	public String toString() {
		return nodeMap.toString();
	}

}
