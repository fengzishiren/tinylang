

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			Interpreter interpreter = new Interpreter("example.sl");
			interpreter.interp();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
