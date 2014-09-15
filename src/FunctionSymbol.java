import java.util.LinkedList;
import java.util.List;

public class FunctionSymbol extends ScopedSymbol {

	List<Symbol> orderedSymbols = new LinkedList<>();

	public FunctionSymbol(String name, Scope enclosing) {
		super(name, enclosing);
	}

	public List<Symbol> formalArgs() {
		return orderedSymbols;
	}

	public Scope getEnclosingScope() {
		return scope.getEnclosing();
	}

	public void define(Symbol symbol) {
		scope.symbols.put(symbol.name, symbol);
		symbol.scope = this.scope;
		orderedSymbols.add(symbol);
	}

	public Symbol resolve(String name) {
		Symbol symbol = scope.symbols.get(name);
		if (symbol != null)
			return symbol;

		if (scope.getEnclosing() != null)
			return scope.getEnclosing().resolve(name);
		return null;

	}

}
