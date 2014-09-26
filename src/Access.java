public class Access extends Stmt {

	public Name name;
	public Index idxs;

	public Access(Position pos, Name name, Index idxs) {
		super(pos);
		this.name = name;
		this.idxs = idxs;
	}

	@Override
	public Value interp(Scope s) {
		Value val = name.interp(s);
		// list or value or error!
		if (val instanceof ListValue) {
			Value ret = (ListValue) val;
			ListValue xx = idxs.interp(s);
			for (Value idx : xx) {
				if (!(idx instanceof IntValue)) {
					S.error(pos, "list %s 索引必须是int值 %s", this, idx);
				}
				if (!(ret instanceof ListValue)) {
					S.error(pos, "不是list类型 不支持索引 %s", this, idx);
				}
				ret = ((ListValue) ret).get(((IntValue) idx).value, idxs.pos);
			}
			return ret;
		}
		if (val instanceof DictValue) {
			Value ret = (DictValue) val;
			ListValue xx = idxs.interp(s);
			for (Value idx : xx) {
				if (!(idx instanceof PrimValue)) {
					S.error(pos, "Dict %s 索引必须是基本类型值 %s", this, idx);
				}
				if (!(ret instanceof DictValue)) {
					S.error(pos, "不是Dict类型 不支持索引 %s", this, idx);
				}
				ret = ((DictValue) ret).get(((PrimValue) idx), idxs.pos);
			}
			return ret;
		}
		S.error(pos, "不是list类型 不支持索引 %s", this, idxs);
		return Value.VOID; // never touch
	}

	@Override
	public String toString() {
		return name.toString() + idxs;
	}

}
