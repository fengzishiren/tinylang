
public class Index extends List {

	public Index() {
		super();
	}

	@Override
	public String toString() {
		return "[" + U.join("][", nodes) + "]";
	}
	
}
