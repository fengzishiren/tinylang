
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * @author lunatic 2014年7月26日
 */
public class U {
	private U() {
	}

	private static Map<Character, String> escapeMap = new HashMap<Character, String>();

	static {
		escapeMap.put('\"', "\\\"");
		escapeMap.put('\\', "\\\\");
		escapeMap.put('/', "\\/");
		escapeMap.put('\b', "\\b");
		escapeMap.put('\f', "\\f");
		escapeMap.put('\n', "\\n");
		escapeMap.put('\r', "\\r");
		escapeMap.put('\t', "\\t");
		// escape.put("\\u", '\u5845')
	}
	/**
	 * 不同操作系统上的换行符号不统一 这里在读取文件的时候统一用’\n‘代替 在词法分析阶段被记录以便精确定位语法错误位置
	 */
	public static final char NEW_LINE = '\n';

	public static final String read(Reader _reader) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(_reader);

			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append(U.NEW_LINE);
			}
			sb.deleteCharAt(sb.length() - 1);
		} finally {
			if (reader != null)
				reader.close();

		}
		return sb.toString();
	}

	public static final String join(String sep, List<String> ss) {
		if (ss.isEmpty())
			return "";
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = ss.iterator();
		while (true) {
			sb.append(it.next());
			if (it.hasNext())
				sb.append(sep);
			else
				break;
		}
		return sb.toString();
	}

	public static final String join(String sep, String... ss) {
		return join(sep, Arrays.asList(ss));
	}

	public static void main(String[] args) {
		String join = U.join(" ", Arrays.asList("a", "b"));

		System.out.println(join);
	}

}
