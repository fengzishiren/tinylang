import java.io.IOException;

public class Interpreter {
	String file;

	public Interpreter(String file) {
		this.file = file;
	}

	public Value interp() throws IOException {
		Parser parser = new Parser(new Lexer(U.readFile(file)));
		Node root = parser.parse();
		// print ast
		System.out.println(root.toString());
		Value retval = root.interp(Scope.initScope());
		return retval;
	}

	public static void main(String[] args) {
		try {
			Interpreter interpreter = new Interpreter(args[0]);
			interpreter.interp();
		} catch (Exception e) {
			System.err.println(e);
			System.exit(1);
		}
	}
}
