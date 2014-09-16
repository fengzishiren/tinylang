

public class FloatValue extends Value {
    public float value;


    public FloatValue(float value) {
        this.value = value;
    }


    public String toString() {
    	return Double.toString(value);
    }

}
