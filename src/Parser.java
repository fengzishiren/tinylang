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
			error("expect '%s', but given %s", Tag.toString(look.tag), Tag.toString(c));
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

	/**
	 * Note:左递归 Seq(stmts(), stmt())
	 * @return
	 */
	private Stmt stmts() {
		return look.tag == '}' ? Stmt.End : new Seq(stmt(), stmts());
	}

	private Stmt stmt() {
		Node x;
		Stmt s1, s2;
		Stmt savedStmt;
		switch (look.tag) {
		case '{':
			return block();
		case ';'://意义在于： ; 独占一行 什么也别做 类似python：pass
			move();
			return Stmt.End;
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
		case Tag.For:
			move();
			//它的一般形式为： for(;;) 语句；
			//初始化总是一个赋值语句，它用来给循环控制变量赋初值；
			//条件表达式是一个关系表达式，它决定什么时候退出循环；
			//增量定义循环控制变量每循环一次后按什么方式变化。
			//这三个部分之间用";"分开
			For forNode = new For();
			savedStmt = Stmt.Enclosing;
			Stmt.Enclosing = forNode;
			Stmt init = Stmt.End;
			match('(');
			if (look.tag != ';') {
				init = assign();
				match(';');
			}
			x = bool();
			match(';');
			Stmt update = Stmt.End;
			if (look.tag != ')') {
				update = assign();
			}
			match(')');
			s1 = stmt();
			Stmt.Enclosing = savedStmt;
			forNode.init(init, x, update, s1);
			return forNode;
		case Tag.BREAK:
			if (Stmt.Enclosing == Stmt.End)
				error("unenclosed break");
			match(Tag.BREAK);
			match(';');
			return new Break();
		case Tag.RETURN:
			move();
			Return ret = new Return(look.tag == ';' ? Stmt.End : bool());
			match(';');
			return ret;
		default:
			s1 =  assign();
			match(';');
			return s1;
		}
	}

	private Stmt assign() {
		Name name = new Name(look.toString());
		match(Tag.ID);
		if (look.tag == '=') {
			move();
			Assign assign = new Assign(name, bool());
			//match(';');
			return assign;
		}
		Argument args = argument();
		//match(';');
		return new Call(name, args);
	}

	/**
	 * call(a, b, c) - >list(a, b, c)
	 * 
	 * @return
	 */
	private Argument argument() {
		Argument args = new Argument();
		match('(');
		while (look.tag != ')') {
			args.addArg(bool());
			if (look.tag != ')')
				match(',');
		}
		match(')');
		// match(';');
		return args;
	}
	
	/**
	 * 注意优先级递归
	 * 
	 * || -> && -> == > != -> <>
	 * @return
	 */
	private Node bool() {
		Node x = join();
		while (look.tag == Tag.OR) {
			move();
			x = new Or(Tag.OR, x, join());
		}
		return x;
	}

	private Node join() {
		Node x = equality();
		while (look.tag == Tag.AND) {
			move();
			x = new And(Tag.AND, x, equality());
		}
		return x;
	}

	private Node equality() {
		Node x = rel();
		while (look.tag == Tag.EQ || look.tag == Tag.NE) {
			Token tok = look;
			move();
			x = new Rel(tok.tag, x, rel());
		}
		return x;
	}

	/**
	 * unsupport: ||、&&
	 * 
	 * @return
	 */
	private Node rel() {
		//   support ||, &&
		Node x = expr();
		switch (look.tag) {
		case '<':
		case '>':
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
		case '+':
		case '-':
			//support: +3, -3 = > 0 + 3, 0 - 3
			return Node.zero;
		case '(':
			move();
			x = bool();
			match(')');
			return x;
		case Tag.NULL:
			x =  Node.Null;
			move();
			return x;
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
				return new Call(name, argument());
			}
			if (look.tag == '[')
				return new Access(name, access());
			return name;
		case Tag.STRING:
			String str = look.toString();
			move();
			return new Str(str);
		default:
			return struct();
		}
	}

	/**
	 * a[3], a[3][4][xxx] a[""]
	 * 
	 * @return
	 */
	private Index access() {
		Index idx = new Index();
		do {
			match('[');
			idx.addNode(bool());
			match(']');
		} while (look.tag == '[');
		return idx;
	}

	private Node struct() {
		switch (look.tag) {
		case '[':// list = [1, 2, 3, "4"]
			move();
			List list = new List();
			while (look.tag != ']') {
				list.addNode(bool());
				if (look.tag != ']')
					match(',');
			}
			match(']');
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
			match('}');
			return dict;
		default:
			error("unrecognize!" + look);
			return null;
		}
	}

}
