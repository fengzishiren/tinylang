//comment
define echo(msg) {
	print(msg);
	return msg;
}

define main() {
	a = 222;
	
	a = a + 250;
	if (a > 100) {
		print("hello");
		print("World");
	}
	
	if (a < 0 || true)
		print("Hello World");
	msg = echo(echo("hello"));
	print(msg);
	
	return version() == "V1.0.1";
}
