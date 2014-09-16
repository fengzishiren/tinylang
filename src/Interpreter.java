

import java.io.FileNotFoundException;
import java.io.IOException;

public class Interpreter {
	String file;

	public Interpreter(String file) {
		this.file = file;
	}

	public void interp() throws FileNotFoundException, IOException {
		Parser parser = new Parser(new Lexer(U.readFile(file)));
		Node root = parser.parse();
		root.interp(new Scope());
	}

	public static void main(String[] args) {

		try {
			Interpreter interpreter = new Interpreter("");
			interpreter.interp();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
