
public class Tag {
	
	public static final int EOF = -1;
	public static final int INT = 256;
	public static final int STRING = 257;
	public static final int FLOAT = 258;
	public static final int AND = 259;
	public static final int OR = 260;
	public static final int ID = 261;
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
	public static final int RETURN = 274;
	public static final int FOR = 275;
	public static final int FOREACH = 276;
	public static final int IN = 277;
	public static final int LAMBDA = 278;
	public static final int CONTINUE = 279;
	public static final int NULL = 280;
	
	public static final String[] desc = {
		"int",
		"string",
		"float",
		"and",
		"or",
		"id",
		"if",
		"else",
		">=",
		"<=",
		"!=",
		"==",
		"while",
		"break",
		"do",
		"false",
		"true",
		"define",
		"return",
		"for",
		"foreach",
		"in",
		"lambda",
		"continue",
		"null"
	};


	public static String descOf(int tag) {
		if (tag == -1) return "EOF";
		return tag > 255 ? desc[tag - 256]: String.valueOf((char)tag);
	}
	
	public static String toString(int tag) {
		return descOf(tag);
	}

}
