public class IndexAssign extends Stmt {
	public Name name;
	public Index idxs;
	public Node value;

	public IndexAssign(Name name, Index idxs, Node value) {
		super();
		this.name = name;
		this.idxs = idxs;
		this.value = value;
	}

	@Override
	public Value interp(Scope s) {
		Value e = value.interp(s);
		Value val = name.interp(s);
		// list or value or error!
		if (val instanceof ListValue) {
			Value ret = (ListValue) val;
			ListValue xx = idxs.interp(s);// [][][][][][][][]...
			for (int i = 0; i < xx.size().value; i++) {
				Value idx = xx.get(i);
				if (!(idx instanceof IntValue)) {
					S.error("list %s 索引必须是int值 %s", this, idx);
				}
				if (!(ret instanceof ListValue)) {
					S.error("不是list类型 不支持索引 %s", this, idx);
				}
				if (i == xx.size().value - 1) {
					((ListValue) ret).set(((IntValue) idx), e);
					return Value.VOID;
				}
				ret = ((ListValue) ret).get(((IntValue) idx));
			}

		}
		if (val instanceof DictValue) {
			Value ret = (DictValue) val;
			ListValue xx = idxs.interp(s);// [][][][][][][][]...
			for (int i = 0; i < xx.size().value; i++) {
				Value idx = xx.get(i);
				if (!(idx instanceof PrimValue)) {
					S.error("Dict %s 索引必须是基本类型值 %s", this, idx);
				}
				if (!(ret instanceof DictValue)) {
					S.error("不是Dict类型 不支持索引 %s", this, idx);
				}
				if (i == xx.size().value - 1) {
					((DictValue) ret).put(((PrimValue) idx), e);
					return Value.VOID;
				}
				ret = ((DictValue) ret).get(((PrimValue) idx));
			}
		}
		S.error("不是list或dict类型 不支持索引 %s", this, idxs);
		return Value.VOID; // never touch
	}

}