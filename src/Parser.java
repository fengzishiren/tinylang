public class Parser {
	private Token look;
	private Lexer lexer;

	public Parser(Lexer lexer) {
		this.lexer = lexer;
		move();
	}

	private void move() {
		look = lexer.scan();
	}

	private void match(int c) {
		if (look.tag == c)
			move();
		else
			error("expect '%c'", c);
	}

	private void error(String msg, Object... args) {
		S.error(look, String.format(msg, args));
	}

	public Node parse() {
		Unit root = new Unit();
		// 必须以define打头
		do
			root.addFun(function());
		while (look != null && look.tag == Tag.DEFINE);
		if (look != null)
			error("Expect EOF");
		return root;
	}

	private Fun function() {
		match(Tag.DEFINE);
		String name = look.toString();
		match(Tag.ID);
		match('(');
		Parameter params = new Parameter();
		while (look.tag != ')') {
			params.addParam(new Name(look.toString()));
			match(Tag.ID);
			if (look.tag != ')')
				match(',');
		}
		match(')');
		Fun fun = new Fun(new Name(name), params, block());
		return fun;
	}

	private Stmt block() {
		match('{');
		Stmt stmt = stmts();
		match('}');
		return stmt;
	}

	private Stmt stmts() {
		return look.tag == '}' ? Stmt.Null : new Seq(stmt(), stmts());
	}

	private Stmt stmt() {
		Node x;
		Stmt s1, s2;
		Stmt savedStmt;
		switch (look.tag) {
		case '{':
			return block();
		case ';':
			move();
			return Stmt.Null;
		case Tag.IF:
			move();
			match('(');
			x = bool();
			match(')');
			s1 = stmt();
			if (look.tag != Tag.ELSE)
				return new If(x, s1);
			match(Tag.ELSE);
			s2 = stmt();
			return new Else(x, s1, s2);
		case Tag.WHILE:
			While whileNode = new While();
			savedStmt = Stmt.Enclosing;
			Stmt.Enclosing = whileNode;
			match(Tag.WHILE);
			match('(');
			x = bool();
			match(')');
			s1 = stmt();
			whileNode.init(x, s1);
			Stmt.Enclosing = savedStmt;
			return whileNode;
		case Tag.BREAK:
			if (Stmt.Enclosing == Stmt.Null)
				error("unenclosed break");
			match(Tag.BREAK);
			match(';');
			return new Break();
		case Tag.RETURN:
			move();
			Return ret = new Return(look.tag == ';' ? null : bool());
			match(';');
			return ret;
		default:
			Name name = new Name(look.toString());
			match(Tag.ID);
			if (look.tag == '=') {
				move();
				Assign assign = new Assign(name, bool());
				match(';');
				return assign;
			}
			Argument args = _call();
			match(';');
			return new Call(name, args);
		}
	}

	/**
	 * unsupport: ||、&&
	 * 
	 * @return
	 */
	private Node bool() {
		// TODO support ||, &&
		Node x = expr();
		switch (look.tag) {
		case '<':
		case '>':
		case Tag.EQ:
		case Tag.NE:
		case Tag.LE:
		case Tag.GE:
			Token tok = look;
			move();
			return new Rel(tok.tag, x, expr());
		default:
			return x;
		}

	}

	private Node expr() {
		Node x = term();
		while (look.tag == '+' || look.tag == '-') {
			Token tok = look;
			move();
			x = new Arith(x, tok.tag, term());
		}
		return x;
	}

	private Node term() {
		Node x = factor();
		while (look.tag == '*' || look.tag == '/') {
			Token tok = look;
			move();
			x = new Arith(x, tok.tag, factor());
		}
		return x;
	}

	/**
	 * 
	 * int|float|bool|call|id|
	 * 
	 * Note: unsupport '('、')'
	 * 
	 * @return
	 */
	private Node factor() {
		Node x = null;
		switch (look.tag) {
		case Tag.INT:
			x = new Int(look.content);
			move();
			return x;
		case Tag.FLOAT:
			x = new Float(look.content);
			move();
			return x;
		case Tag.TRUE:
		case Tag.FALSE:
			x = new Bool(look.content);
			move();
			return x;
		case Tag.ID:
			String id = look.toString();
			Name name = new Name(id);
			move();
			if (look.tag == '(') {
				return new Call(name, _call());
			}
			if (look.tag == '[' || look.tag == '{');
				//TODO
			return name;

		case Tag.STRING:
			String str = look.toString();
			move();
			return new Str(str);
		default:
			struct();
		}
		return null;
	}

	private Node struct() {
		switch (look.tag) {
		case '['://list = [1, 2, 3, "4"]
			move();
			List list = new List();
			while (look.tag != ']') {
				list.addNode(bool());
				if (look.tag != ']')
					match(',');
			}
			return list;
		case '{':
			// dict = {"zheng": 3, 1: "", ...}
			move();
			Dict dict = new Dict();
			while (look.tag != '}') {
				Node key = bool();
				match(':');
				Node val = bool();
				dict.addKV(key, val);
				if (look.tag != '}')
					match(',');
			}
			return dict;
		default:
			error("unrecognize!" + look);
			return null;
		}
	}

	// //////////////////////////////
	// helper
	// /
	
	/**
	 * call(a, b, c) - >list(a, b, c)
	 * @return
	 */
	private Argument _call() {
		Argument args = new Argument();
		match('(');
		while (look.tag != ')') {
			args.addArg(expr());
			if (look.tag != ')')
				match(',');
		}
		match(')');
		// match(';');
		return args;
	}
}
