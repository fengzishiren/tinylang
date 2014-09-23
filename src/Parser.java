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
			error("expect '%s', but given '%s'.", Tag.toString(c),
					Tag.toString(look.tag));
	}

	private void error(String msg, Object... args) {
		S.error(String.format(msg, args));
	}

	public Node parse() {
		Unit root = new Unit();
		while (look != Token.EOF)
			root.addFun(function());
		return root;
	}

	private Fun function() {
		match(Tag.DEFINE);
		String name = look.content();
		match(Tag.ID);
		Fun fun = new Fun(new Name(name), param(), block());
		return fun;
	}

	private Parameter param() {
		match('(');
		Parameter params = new Parameter();
		while (look.tag != ')') {
			params.addParam(new Name(look.content()));
			match(Tag.ID);
			if (look.tag != ')')
				match(',');
		}
		match(')');
		return params;
	}

	private Stmt block() {
		match('{');
		Stmt stmt = stmts();
		match('}');
		return stmt;
	}

	/**
	 * Note:左递归 Seq(stmts(), stmt())
	 * 
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
		case ';':// 意义在于： ; 独占一行 什么也别做 类似python：pass
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
			// 它的一般形式为： for(;;) 语句；
			// 初始化总是一个赋值语句，它用来给循环控制变量赋初值；
			// 条件表达式是一个关系表达式，它决定什么时候退出循环；
			// 增量定义循环控制变量每循环一次后按什么方式变化。
			// 这三个部分之间用";"分开
			For forNode = new For();
			savedStmt = Stmt.Enclosing;
			Stmt.Enclosing = forNode;
			Stmt init = Stmt.Null;
			match('(');
			if (look.tag != ';') {
				init = assign();
				match(';');
			}
			x = bool();
			match(';');
			Stmt update = Stmt.Null;
			if (look.tag != ')') {
				update = assign();
			}
			match(')');
			s1 = stmt();
			Stmt.Enclosing = savedStmt;
			forNode.init(init, x, update, s1);
			return forNode;
		case Tag.Foreach:// foreach (item in list) foreach (k, v in dict)
			move();
			Foreach foreachNode = new Foreach();
			savedStmt = Stmt.Enclosing;
			Stmt.Enclosing = foreachNode;
			match('(');
			Name k = new Name(look.content());
			match(Tag.ID);
			Name v = Name.Null;
			// Name v= Node.Null;
			if (look.tag == ',') {
				match(',');
				v = new Name(look.content());
				match(Tag.ID);
			}
			match(Tag.IN);
			Node cols = factor();
			match(')');
			s1 = stmt();
			Stmt.Enclosing = savedStmt;
			foreachNode.init(k, v, cols, s1);
			return foreachNode;
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
		case Tag.ID:
			s1 = assign();
			match(';');
			return s1;
		default:
			error("unexpected '%s'", Tag.toString(look.tag));
			return null;
		}
	}

	/**
	 * xx = xxx xx[xx] = xxx
	 * 
	 * @return
	 */
	private Stmt assign() {
		Name name = new Name(look.content());
		match(Tag.ID);
		Argument args;
		Access ac = null;
		switch (look.tag) {
		case '=':
			move();
			Assign assign = new Assign(name, bool());
			// match(';');
			return assign;
		case '[': // a[2][3] = xxxx
			Index idx = access();
			if (look.tag == '=') {
				match('=');
				return new IndexAssign(name, idx, bool());
			}
			ac = new Access(name, idx);
			// case through: ls[0][0].append()....
		case '.':// eg: ls.append(x) => append(ls, x)
			move();
			Name oname = new Name(look.content());
			match(Tag.ID);
			args = argument();
			args.addFirst(ac == null ? name : ac);
			return new Call(oname, args);
		default:
			args = argument();
			// match(';');
			return new Call(name, args);
		}
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
	 * 
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
		// support ||, &&
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
			return x;// eg: if(true) -： x = true
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
			// support: +3, -3 = > 0 + 3, 0 - 3
			return Node.zero;
		case '(':
			move();
			x = bool();
			match(')');
			return x;
		case Tag.NULL:
			x = Node.Null;
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
		case Tag.ID:// 可能是变量
			String id = look.content();
			Name name = new Name(id);
			move();
			if (look.tag == '(') {// 函数调用
				return new Call(name, argument());
			}
			if (look.tag == '[')// 下标访问
				return new Access(name, access());
			return name;// 变量
		case Tag.STRING://"xxxx"
			String str = look.content();
			move();
			return new Str(str);
		case Tag.Lambda: // eg: Lambda (x, y){x+y}
			match(Tag.Lambda);
			return new Lambda(param(), block());
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
			error("unexpected '%s'", Tag.toString(look.tag));
			return null;
		}
	}

}
