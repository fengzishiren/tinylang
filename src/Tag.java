public class Tag {

	public static final int START = 256;
	public static final int AND = 257;
	public static final int OR = 258;
	public static final int INT = 259;
	public static final int ID = 260;
	public static final int STRING = 261;
	public static final int IF = 262;
	public static final int ELSE = 263;
	public static final int GE = 264;
	public static final int LE = 265;
	public static final int NE = 266;
	public static final int EQ = 267;
	public static final int WHILE = 268;
	public static final int BREAK = 269;
	public static final int DO = 270;
	public static final int FALSE = 271;
	public static final int TRUE = 272;
	public static final int DEFINE = 273;
	public static final int NL = 274; // newline
	public static final int FUNC = 275;

	public String tagOf(int tag) {
		// return tag < 256 ? String.valueOf(tag) : Tag.class.getd
		return tag < 256 ? String.valueOf(tag) : "";
	}

}
