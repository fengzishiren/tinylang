

import java.util.ArrayList;

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
		Funcs root = new Funcs();
		while (look != null)
			root.addFun(function());
		return root;
	}

	private Fun function() {
		match(Tag.DEFINE);
		String name = look.toString();
		match(Tag.ID);
		match('(');
		ArrayList<Name> params = new ArrayList<Name>();
		while (look.tag != ')') {
			params.add(new Name(look.toString()));
			match(Tag.ID);
			if (look.tag != ')')
				match(',');
		}
		match(')');
		Fun fun = new Fun(name, params, block());
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
			Return ret = new Return(expr());
			match(';');
			return ret;
		default:
			Name name = new Name(look.toString());
			match(Tag.ID);
			if (look.tag == '=') {
				move();
				Assign assign = new Assign(name, expr());
				match(';');
				return assign;
			}
			ArrayList<Node> params = new ArrayList<>();
			match('(');
			while (look.tag != ')') {
				params.add(atom());
				if (look.tag != ')')
					match(',');
			}
			match(')');
			match(';');
			return new Call(name, new Argument(params));
		}
	}

	private Node bool() {
		Node x = atom();
		switch (look.tag) {
		case '<':
		case '>':
		case Tag.LE:
		case Tag.GE:
			Token tok = look;
			move();
			return new Rel(tok, x, atom());
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
		Node x = atom();
		while (look.tag == '*' || look.tag == '/') {
			Token tok = look;
			move();
			x = new Arith(x, tok.tag, atom());
		}
		return x;
	}

	private Node atom() {
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
			move();
			return new Name(id);
		case Tag.STRING:
			String str = look.toString();
			move();
			return new Str(str);
		default:
			error("unrecognize!" + look);
		}
		return null;
	}
}
