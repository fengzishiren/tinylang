/**
 * 跳出层层block 直到最近的函数
 * 
 * @author lunatic
 *
 */
public class ReturnJmp extends RuntimeException {
	public Value value = Value.VOID;

	/**
	 * return ; 则返回Void
	 * 
	 * @return
	 */
	public Value attatchment() {
		return value;
	}

	public ReturnJmp(Value value) {
		super();
		this.value = value;
	}

	public ReturnJmp() {
	}
}
