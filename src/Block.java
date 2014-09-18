

import java.util.ArrayList;
import java.util.List;


public class Block extends Node {

	public List<Node> statements = new ArrayList<>();

	public Block(List<Node> statements) {
		super();
		this.statements = statements;
	}

	@Override
	public Value interp(Scope s) {
		s = new Scope(s);
		for (int i = 0; i < statements.size() - 1; i++) {
			statements.get(i).interp(s);
		}
		return statements.get(statements.size() - 1).interp(s);
	}


}
