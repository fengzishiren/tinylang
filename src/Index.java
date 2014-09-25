
public class Index extends List {

	public Index(Position pos) {
		super(pos);
	}

	@Override
	public String toString() {
		return "[" + U.join("][", nodes) + "]";
	}
	
}
