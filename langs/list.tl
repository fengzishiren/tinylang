define main() {
	a = [1, 2, 3, 4, 5, 6];
	foreach(e in a) {
		print(e);
		print("----------");
	}
		
	for (i = 0; i < len(a); i= i+1) {
		print("**************");
	}
	a[0] = 1000;
	print(a);
	a.append(7);
	print(a);
	#hello:se
	a = true;
	a.remove(6);
	print(a);
	return len(a);
}
