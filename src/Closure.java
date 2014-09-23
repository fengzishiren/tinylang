/**
 * 函数Fun和上下文env
 * 
 * @author lunatic
 *
 */
public class Closure extends Value {
	public Fun fun;
	/**
	 * env: local{contains global} or gloabl
	 * 
	 * Note upvalue bind when closure creating
	 */
	public Scope env;

	public Closure(Fun fun, Scope upvalue) {
		this.fun = fun;
		this.env = upvalue;
	}

	@Override
	public String toString() {
		return fun.toString();
	}

	@Override
	public Type type() {
		return Type.Funtion;
	}

}
