public class ScopedSymbol extends Symbol {
	public Scope scope;

	public ScopedSymbol(String name, Scope enclosing) {
		super(name);
		scope = new Scope(enclosing);
	}

}
