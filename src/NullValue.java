
public class NullValue extends PrimValue {

	//Null null;
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof NullValue ;
	}

	@Override
	public int hashCode() {
		return 0;
	}
	
	@Override
	public String toString() {
		return "null";
	}

}
