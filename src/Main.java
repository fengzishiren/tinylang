import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			Interpreter interpreter = new Interpreter("langs/example.tl");
			Value ret = interpreter.interp();
			System.out.println(ret);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
