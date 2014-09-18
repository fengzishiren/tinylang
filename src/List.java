import java.util.ArrayList;
import java.util.Iterator;

public class List extends Node implements Iterable<Node> {
	public ArrayList<Node> nodes = new ArrayList<>();

	@Override
	public ListValue interp(Scope s) {
		ArrayList<Value> values = new ArrayList<>(nodes.size());
		for (Node node : nodes) {
			values.add(node.interp(s));
		}
		return new ListValue(values);
	}
	
	public void addNode(Node e) {
		nodes.add(e);
	}
    public Iterator<Node> iterator() {
        return nodes.iterator();
    }
	public int size() {
		return nodes.size();
	}

	public Node get(int index) {
		return nodes.get(index);
	}
	@Override
	public Value typecheck(Scope s) {
		return Type.List;
	}
	
	@Override
	public String toString() {
		return nodes.toString();
	}
}
