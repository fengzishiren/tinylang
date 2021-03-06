import java.util.Map.Entry;

/**
 * 
 * foreach (item in list) foreach (k, v in dict) foreach(k in dict)
 * 
 * @author lunatic
 *
 */
public class Foreach extends Stmt {

	public Name first;
	public Name second; // 可选

	public Node collection;// name or {} or []

	public Stmt body;

	public Foreach(Position pos) {
		super(pos);
	}

	public void init(Name first, Name second, Node collection, Stmt body) {
		this.first = first;
		this.second = second;
		this.collection = collection;
		this.body = body;
	}

	public void init(Name first, Node collection, Stmt body) {
		this.first = first;
		this.second = null;
		this.collection = collection;
		this.body = body;
	}

	@Override
	public Value interp(Scope s) {
		Value val = collection.interp(s);
		if (val instanceof ListValue) {
			if (second != Name.Null) {
				S.error(pos, "List类型不支持for (k, v in x)形式");
			}
			ListValue ls = ((ListValue) val);
			try {
				for (Value v : ls) {
					Binder.assign(first, v, s);
					try {
						body.interp(s);
					} catch (Goon ignore) {
					}
				}
			} catch (Goto ignore) {
			}
		} else if (val instanceof DictValue) {
			DictValue dt = ((DictValue) val);
			try {
				for (Entry<PrimValue, Value> e : dt) {
					Binder.assign(first, e.getKey(), s);
					if (second != Name.Null)
						Binder.assign(second, e.getValue(), s);
					try {
						body.interp(s);
					} catch (Goon ignore) {
					}
				}
			} catch (Goto ignore) {
			}
		} else
			S.error(pos, "不可迭代,必须为集合类型");
		return Value.VOID;
	}

	@Override
	public String toString() {
		return (Name.Null == second) ? String.format("for (%s in %s) %s",
				first, collection, body) : String.format(
				"for (%s, %s in %s) %s", first, second, collection, body);
	}
}
