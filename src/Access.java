public class Access extends Node {

	public Name name;
	public Index idxs;

	public Access(Name name, Index idxs) {
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
					S.error("list %s 索引必须是int值 %s", this, idx);
				}
				if (!(ret instanceof ListValue)) {
					S.error("不是list类型 不支持索引 %s", this, idx);
				}
				ret = ((ListValue) ret).get(((IntValue) idx).value);
			}
			return ret;
		}
		if (val instanceof DictValue) {
			Value ret = (DictValue) val;
			ListValue xx = idxs.interp(s);
			for (Value idx : xx) {
				if (!(idx instanceof PrimValue)) {
					S.error("Dict %s 索引必须是基本类型值 %s", this, idx);
				}
				if (!(ret instanceof DictValue)) {
					S.error("不是Dict类型 不支持索引 %s", this, idx);
				}
				ret = ((DictValue) ret).get(((PrimValue) idx));
			}
			return ret;
		}
		S.error("不是list类型 不支持索引 %s", this, idxs);
		return Value.VOID; // never touch
	}
	@Override
	public String toString() {
		return name.toString() + idxs;
	}

}
