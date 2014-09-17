

public class FloatValue extends PrimValue {
    public float value;


    public FloatValue(float value) {
        this.value = value;
    }


    public String toString() {
    	return Double.toString(value);
    }

}
