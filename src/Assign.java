

public class Assign extends Stmt {
	public Node name;
	public Node value;

	public Assign(Node name, Node value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " = " + value;
	}

}
