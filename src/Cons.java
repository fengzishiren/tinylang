/**
 * like lisp's cons
 * 
 * @author lunatic
 *
 */
public class Cons extends ComplexValue {

	//like C++ pair: <first, second>
	public Value first;
	public Value second;
	@Override
	public Type type() {
		return Type.CONS;
	}
	@Override
	public IntValue size() {
		return new IntValue(2);
	}

}
