define main() {

	a = 10;
	b = 200;
	pair = cons(a, cons(a, b ));
	print(pair);
	return cdr(pair);
}