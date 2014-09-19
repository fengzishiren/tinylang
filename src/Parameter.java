
/**
 * 形参
 * 
 * @author lunatic
 *
 */
public class Parameter extends Node {
	public List params = new List();

	public void addParam(Name param) {
		this.params.addNode(param);
	}

	public static Parameter noParams() {
		return new Parameter( );
	}

	public String toString() {
		return U.join(", ", params.nodes);
	}

	public int size() {
		return params.size();
	}

	public Name get(int index) {
		return (Name) params.get(index);
	}

	/**
	 * 形参不允许求值
	 */
	@Override
	public Value interp(Scope s) {
		S.error("unsupport formal parameter eval!");
		return Value.VOID;
	}
}
