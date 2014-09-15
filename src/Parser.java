public class Parser {

	private Token look;
	private Lexer lexer;

	private Node root;
	private Scope currentScope;

	public Parser(Lexer lexer) {
		root = new Node();
		this.lexer = lexer;
		lexer.scan();
		currentScope = new GlobalScope(null);
	}

	private void move() {
		lexer.scan();
	}

	private void match(int tag) {
		if (look.tag == tag)
			move();
		else
			S.error(look, "Expect %s");
	}
	
	public Node parse() {
		Node node = new Node();
		while (look != null) {
			if (look.tag == Tag.DEFINE) {
				node.addChild(define());
			}
		}
		return null;
	}

	private Node define() {
		match(Tag.DEFINE);
		Node node = Node.funcNode;
		node.addChild(new Node(look));
		
		FunctionSymbol funcSymbol = new FunctionSymbol(look.toString(), this.currentScope);
		funcSymbol.scope = this.currentScope;
		return null;
	}

}
