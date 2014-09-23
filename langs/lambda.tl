

define handle(a,  hd) {
	fun = hd(111, 10000);
	fun(print);
}

define main() {

	a = 10;b  = 110;
	handle(a,  lambda (x, y) {return lambda(how) { how(a, b);};});
	
	//f = lambda (x) {print(x, a);};
	//f("lambda test");
}