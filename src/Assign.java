
/**
 * 复制操作的本质是变量名name与值value的绑定 即：K,V 如果V有变化则为：K, V1....
 * @author lunatic
 *
 */
public class Assign extends Stmt {
	public Name name;
	public Node value;

	public Assign(Position pos, Name name, Node value) {
		super(pos);
		this.name = name;
		this.value = value;
	}
	
	@Override
	public Value interp(Scope s) {
		Value vv = value.interp(s);
		Binder.assign(name, vv, s);
		return Value.VOID;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " = " + value;
	}

}
