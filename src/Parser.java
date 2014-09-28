/**
 * 语法分析
 * 
 * @author lunatic
 *
 */
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
		S.error(look.pos + " syntax error! " + String.format(msg, args));
	}

	private String name() {
		if (Tag.EOF == look.tag)
			S.error(look.pos, "unexpected %s", Tag.toString(Tag.EOF));
		return look.content.toString();
	}

	public Node parse() {
		Unit root = new Unit(Position.START);
		while (look != Token.EOF)
			root.addFun(function());
		return root;
	}

	private Fun function() {
		Position defPos = look.pos;
		match(Tag.DEFINE);
		Position funPos = look.pos;
		String name = name();
		match(Tag.ID);
		Fun fun = new Fun(defPos, new Name(funPos, name), param(), block());
		return fun;
	}

	private Parameter param() {
		match('(');
		Parameter params = new Parameter(look.pos);
		while (look.tag != ')') {
			params.addParam(new Name(look.pos, name()));
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
		return look.tag == '}' ? Stmt.End : new Seq(look.pos, stmt(), stmts());
	}

	private Stmt stmt() {
		Node x;
		Stmt s1, s2;
		Stmt savedStmt;
		Position pos = look.pos;
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
				return new If(pos, x, s1);
			match(Tag.ELSE);
			s2 = stmt();
			return new Else(pos, x, s1, s2);
		case Tag.WHILE:
			While whileNode = new While(pos);
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
		case Tag.FOR:
			move();
			// 它的一般形式为： for(;;) 语句；
			// 初始化总是一个赋值语句，它用来给循环控制变量赋初值；
			// 条件表达式是一个关系表达式，它决定什么时候退出循环；
			// 增量定义循环控制变量每循环一次后按什么方式变化。
			// 这三个部分之间用";"分开
			For forNode = new For(pos);
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
		case Tag.FOREACH:// foreach (item in list) or foreach (k, v in dict)
			move();
			Foreach foreachNode = new Foreach(pos);
			savedStmt = Stmt.Enclosing;
			Stmt.Enclosing = foreachNode;
			match('(');
			Name k = new Name(look.pos, name());
			match(Tag.ID);
			Name v = Name.Null;
			// Name v= Node.Null;
			if (look.tag == ',') {
				match(',');
				v = new Name(look.pos, name());
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
			return new Break(pos);
		case Tag.CONTINUE:
			move();
			match(';');
			return new Continue(pos);
		case Tag.RETURN:
			move();
			Return ret = new Return(pos, look.tag == ';' ? Stmt.End : bool());
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
	 * 1. xx = xxx 2. xx[x] = xxx 3. xx.xxx() 5. xx.xxx().xxx().xxx() 6.
	 * xx[x].xxx().xxx() 7. xxx()
	 * 
	 * @return
	 */
	private Stmt assign() {
		Name name = new Name(look.pos, name());
		match(Tag.ID);
		Position pos = look.pos;
		Argument args;
		Stmt x = null;
		switch (look.tag) {
		case '=':
			move();
			Assign assign = new Assign(pos, name, bool());
			return assign;
		case '[': // a[2][3] = xxxx
			Index idx = access();
			if (look.tag == '=') {
				match('=');
				return new IndexAssign(pos, name, idx, bool());
			}
			x = new Access(pos, name, idx);
			if (look.tag != '.')
				return x;
			// case through: ls[0][0].append()....
		case '.':
			// eg: ls.append(x) => append(ls, x)
			// ls.append(x).append(y).append(z)
			// append(append(append(ls, x), y), z)
			move();
			Name dotname = new Name(look.pos, name());
			match(Tag.ID);
			args = argument();
			args.addFirst(name);
			x = new Call(pos, dotname, args);

			while (look.tag == '.') {
				move();
				pos = look.pos;
				dotname = new Name(pos, name());
				move();
				args = argument();
				args.addFirst(x);
				x = new Call(pos, dotname, args);
			}
			return x;
		case '(':
			args = argument();
			// match(';');
			return new Call(pos, name, args);
		default:
			error("unexpected '%s'", Tag.toString(look.tag));
			return null;
		}
	}

	/**
	 * call(a, b, c) - >list(a, b, c)
	 * 
	 * @return
	 */
	private Argument argument() {
		match('(');
		Argument args = new Argument(look.pos);
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
		// 同等优先级的while求值 更高优先级的优先求值
		Node x = join();
		while (look.tag == Tag.OR) {
			Position pos = look.pos;
			move();
			x = new Or(pos, Tag.OR, x, join());
		}
		return x;
	}

	private Node join() {
		Node x = equality();
		while (look.tag == Tag.AND) {
			Position pos = look.pos;
			move();
			x = new And(pos, Tag.AND, x, equality());
		}
		return x;
	}

	private Node equality() {
		Node x = rel();
		while (look.tag == Tag.EQ || look.tag == Tag.NE) {
			Token tok = look;
			Position pos = look.pos;
			move();
			x = new Rel(pos, tok.tag, x, rel());
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
		Position pos = look.pos;
		switch (look.tag) {
		case '<':
		case '>':
		case Tag.LE:
		case Tag.GE:
			Token tok = look;
			move();
			return new Rel(pos, tok.tag, x, expr());
		default:
			return x;// eg: if(true) -： x = true
		}

	}

	private Node expr() {
		Node x = term();
		while (look.tag == '+' || look.tag == '-') {
			Token tok = look;
			Position pos = look.pos;
			move();
			x = new Arith(pos, x, tok.tag, term());
		}
		return x;
	}

	private Node term() {
		Node x = odot();
		while (look.tag == '*' || look.tag == '/') {
			Token tok = look;
			Position pos = look.pos;
			move();
			x = new Arith(pos, x, tok.tag, factor());
		}
		return x;
	}

	// [].ss().ss().ss()
	// ss([]) ss(ss([]))
	/**
	 * Object.method(xxx)
	 * 
	 * @return
	 */
	private Node odot() {
		Node x = factor();
		while (look.tag == '.') {
			move();
			Position pos = look.pos;
			Name name = new Name(pos, name());
			move();
			Argument args = argument();
			args.addFirst(x);
			x = new Call(pos, name, args);
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
		Position pos = look.pos;
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
		case Tag.INT:
			x = new Int(pos, look.content);
			move();
			return x;
		case Tag.FLOAT:
			x = new Float(pos, look.content);
			move();
			return x;
		case Tag.TRUE:
		case Tag.FALSE:
			x = new Bool(pos, look.content);
			move();
			return x;
		case Tag.ID:// 可能是变量
			String id = name();
			Name name = new Name(pos, id);
			move();
			if (look.tag == '(') {// 函数调用
				return new Call(pos, name, argument());
			}
			if (look.tag == '[')// 下标访问
				return new Access(pos, name, access());
			return name;// 变量
		case Tag.STRING:// "xxxx"
			String str = name();
			move();
			return new Str(pos, str);
		case Tag.LAMBDA: // eg: Lambda (x, y){return x+y;}
			match(Tag.LAMBDA);
			return new Lambda(pos, param(), block());
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
		Index idx = new Index(look.pos);
		do {
			match('[');
			idx.addNode(bool());
			match(']');
		} while (look.tag == '[');
		return idx;
	}

	private Node struct() {
		Position pos = look.pos;
		switch (look.tag) {
		case '[':// list = [1, 2, 3, "4"]
			move();
			List list = new List(pos);
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
			Dict dict = new Dict(pos);
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
