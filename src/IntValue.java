


public class IntValue extends PrimValue {
    public int value;


    public IntValue(int value) {
        this.value = value;
    }


    public String toString() {
        return Integer.toString(value);
    }

}
