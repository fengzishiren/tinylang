/**
 * 
 * @author lunatic
 *
 */
public class Fun extends Node {
	public Name name;
	public Parameter params;
	public Node body;

	public Fun(Position pos, Name name, Parameter params, Node body) {
		super(pos);
		this.name = name;
		this.params = params;
		this.body = body;
	}

	@Override
	public Value interp(Scope s) {
		//Note:所有函数都是全局性的 一次bind Scope:share
		return new Closure(this, Scope.share);
	}

	@Override
	public String toString() {
		return name + "(" + params + ") " + body;
	}

}
