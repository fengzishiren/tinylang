

define hign_order(a, how) {
	how(a);
	return how;
}

define test() {
	print("print");
}

define main() {
	t = test;
	t();
	print(9);
	how = hign_order(100, print);
	how(100000000);
	return how;
}