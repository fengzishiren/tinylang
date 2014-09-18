import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

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
			testInterp(file.toString());
		}
	}

	@Test
	public void testInterp() throws IOException {
		Interpreter interpreter = new Interpreter("langs/testtype");
		Value ret = interpreter.interp();
		System.out.println(ret);
	}
}
