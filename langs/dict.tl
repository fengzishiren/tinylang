define main() {
	b = {"zlh": "zhenglinhai", 9 : "xiaoyaogege", 1: {"kety": "value"}};
	b[9999] = "fengzishiren";
	print(b);
	
	b.remove(9);
	b[1].remove("kety");
	
	print(b);
	
	foreach(k ,v in b) {
		print(k);
		 print(v);
		 print("-----");
		 
	}
	
	return b["zlh"];
}