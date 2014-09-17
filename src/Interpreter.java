import java.io.IOException;

public class Interpreter {
	String file;

	public Interpreter(String file) {
		this.file = file;
	}

	public String interp() throws IOException {
		Parser parser = new Parser(new Lexer(U.readFile(file)));
		Node root = parser.parse();
		System.out.println(root.toString());
		Value retval = root.interp(Scope.initScope());
		return retval.toString();
	}

}
