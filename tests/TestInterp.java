import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class TestInterp {
	private void testInterp(String path) throws IOException {
		Interpreter interpreter = new Interpreter(path);
		Value ret = interpreter.interp();
		System.out.println(ret);
	}

	@Test
	public void testAuto() throws IOException {
		File dir = new File("langs");
		File[] fs = dir.listFiles();
		for (File file : fs) {
			System.out.println("invoke " + file.toString() + " ......");
			testInterp(file.toString());
			Scope.cleanScope();
		}
	}

	@Test
	public void testInterp() throws IOException {
		Interpreter interpreter = new Interpreter("langs/error.tl");
		Value ret = interpreter.interp();
		// print return value
		System.out.println(ret);
	}
}
