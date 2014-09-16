

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

}
