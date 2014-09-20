

define handle(a, hd) {
	hd(a, 10000);
}

define main() {

	a = 10;
	handle(a, lambda (x, y) {print(x, y);});
	
	f = lambda (x) {print(x);};
	f("lambda test");
}