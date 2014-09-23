import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class SprintfFun extends BuiltinFun {
	private Formatter formatter = new Formatter();// .format(format,
													// args).toString(

	public SprintfFun() {
		super("sprintf", -1);// ignore param's count
	}

	@Override
	public Value apply(List<Value> args) {
		if (args.size() == 0)
			S.error(this + "必须至少有一个参数");
		if (!(args.get(0) instanceof StringValue)) {
			S.error(this + "第一个参数必须是String" + args);
		}
		ArrayList<Object> varargs = new ArrayList<>(args.size());
		
		for (int i = 1; i < args.size(); i++) {
			Value v = args.get(i);
			if (v instanceof IntValue) {
				varargs.add(((IntValue) v).value);
			} else if (v instanceof FloatValue) {
				varargs.add(((FloatValue) v).value);
			} else if (v instanceof StringValue) {
				varargs.add(((StringValue) v).value);
			} else
				S.error(this + "格式化参数必须是：int、float和String");
		}
		String s = formatter.format(args.get(0).toString(), varargs.toArray()).toString();
		return new StringValue(s);
	}

	public static void main(String[] args) {
		System.out.printf("%d\n", (Object) Integer.valueOf(10));
	}
}
