
define return1() {
	return 8;
}

define return2() {
	return return1() + 1;
}

define return3() {
	return return2();
}

define return4() {
	return return3();
}

define getRetVal() {
	return return4();
}

define main() {
	a = 10;
	return getRetVal();
}