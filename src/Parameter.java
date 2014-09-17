import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 形参
 * 
 * @author lunatic
 *
 */
public class Parameter {
	public List<Name> elements;

	public Parameter(List<Name> elements) {
		this.elements = elements;
	}

	public Parameter() {
		this.elements = new ArrayList<>();
	}

	public void addParam(Name param) {
		this.elements.add(param);
	}

	public static Parameter noParams() {
		return new Parameter(Collections.emptyList());
	}

	public String toString() {
		return U.join(", ", elements);
	}

	public int size() {
		return elements.size();
	}

	public Name get(int index) {
		return elements.get(index);
	}
}
