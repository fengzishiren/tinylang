/**
 * like lisp's cons
 * 
 * @author lunatic
 *
 */
public class ConsValue extends ComplexValue {

	//like C++ pair: <first, second>
	public Value first;
	public Value second;
	public ConsValue(Value first, Value second) {
		this.first = first;
		this.second = second;
	}
	@Override
	public Type type() {
		return Type.CONS;
	}
	@Override
	public IntValue size() {
		return new IntValue(2);
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %s)", first, second);
	}

}
