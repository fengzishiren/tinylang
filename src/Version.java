import java.util.List;

public class Version extends BuiltinFun {

	
	public Version() {
		super("version", 0); //version()
	}

	@Override
	public Value apply(List<Value> args) {
		return new StringValue("V1.0.1");
	}

	@Override
	public Type type() {
		// TODO Auto-generated method stub
		return Type.builtinFuntion;
	}

}
