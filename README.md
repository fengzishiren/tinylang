tinylang 微型语言解释器
========

如下为设计的语言：

```

define echo(msg) {
	print(msg);
	return msg;
}

define main() {
	a = 10;
	
	a = a + 250;
	if (a > 100) {
		print("hello");
	}
	
	print(echo("hello"));
	
	return a;
}


```
