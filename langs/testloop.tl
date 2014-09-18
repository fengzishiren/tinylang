define main() {
	a = 10;
	while ( a < 20)
	{
		a = a + 1;
		print(a);
		
		if (a > 15) break;
		
		if (a > 14) return 23; 
	}
	
	return a;
}