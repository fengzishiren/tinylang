import java.util.List;

public class VersionFun extends BuiltinFun {

	
	public VersionFun() {
		super("version", 0); //version()
	}

	@Override
	public Value apply(List<Value> args, Position pos) {
		return new StringValue("V1.0.1");
	}

}
