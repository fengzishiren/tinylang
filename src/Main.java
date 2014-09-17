
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			Interpreter interpreter = new Interpreter("langs/test.tl");
			String ret = interpreter.interp();
			System.out.println(ret);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
