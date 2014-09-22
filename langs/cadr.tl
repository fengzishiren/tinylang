
define eval(a,b) {
	t = a;
	a = a + b;
	b = t - b;
	return cons(a, b);
}


define main() {

	a = 10;
	b = 200;
	pair = cons(a, cons(a, b ));
	print(pair);
	print(cdr(pair));
	
	print("a:", a, " b:", b);
	
	val = eval(a, b);
	
	print("a:", car(val), " b:", cdr(val));
	
}