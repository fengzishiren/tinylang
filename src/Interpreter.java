import java.io.IOException;

public class Interpreter {
	String file;

	public Interpreter(String file) {
		this.file = file;
	}

	public Value interp() throws IOException {
		Parser parser = new Parser(new Lexer(U.readFile(file)));
		Node root = parser.parse();
		//print ast
		System.out.println(U.toString(root));
		Value retval = root.interp(Scope.initScope());
		return retval;
	}

}
