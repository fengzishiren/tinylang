import java.util.HashMap;
import java.util.Map;

public class Scope {
	public Scope enclosing;
	public Map<String, Symbol> symbols = new HashMap<>();

	public Scope(Scope enclosing) {
		this.enclosing = enclosing;
	}

	public Scope getEnclosing() {
		return enclosing;
	}

	public void define(Symbol symbol) {
		symbols.put(symbol.name, symbol);
		symbol.scope = this;
	}

	public Symbol resolve(String name) {
		Symbol symbol = symbols.get(name);
		if (symbol != null)
			return symbol;
		if (getEnclosing() != null)
			return getEnclosing().resolve(name);
		return null;
	}

	public String name() {
		return "";
	}
}
