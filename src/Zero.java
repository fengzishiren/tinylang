/**
 * 处理有符号数字
 * @author lunatic
 * 2014年9月18日
 */
public class Zero extends Node {

	@Override
	public Value interp(Scope s) {
		return new IntValue(0);
	}

	@Override
	public String toString() {
		return "0";
	}

}
