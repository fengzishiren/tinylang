import java.util.ArrayList;

public class List extends Node {
	public ArrayList<Node> nodes = new ArrayList<>();

	@Override
	public Value interp(Scope s) {
		ArrayList<Value> values = new ArrayList<>(nodes.size());
		for (Node node : nodes) {
			values.add(node.interp(s));
		}
		return new ListValue(values);
	}
	
	public void addNode(Node e) {
		nodes.add(e);
	}

	@Override
	public Value typecheck(Scope s) {
		return Value.List;
	}

}
