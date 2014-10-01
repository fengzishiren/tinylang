define main() {
	print("你好，\t世界！\n");
	a  = 1000;
	b = "zifuchuan";
	
	re = a + b;
	print(re);
	
	s = sprintf("%s + %d", re, 250);
	print(s, str(a), type(str(a)));
	
	ls = [].append(33).append(33).append(33).append(33);
	ls.append("EOF").append(33).append(33);
	print(ls);
	s = ls[4].str().str();
	print(s);
	
	return type(null);
}