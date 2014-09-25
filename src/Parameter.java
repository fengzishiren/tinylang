import java.util.ArrayList;

/**
 * 形参
 * 
 * @author lunatic
 *
 */
public class Parameter extends Node {
	public Parameter(Position pos) {
		super(pos);
	}

	public ArrayList<Name> params = new ArrayList<>();

	public void addParam(Name param) {
		this.params.add(param);
	}

	public static Parameter noParams(Position pos) {
		return new Parameter(pos);
	}

	public String toString() {
		return U.join(", ", params);
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
		S.error(pos, "unsupport formal parameter eval!", pos);
		return Value.VOID;
	}
}
