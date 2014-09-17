tinylang



========

###运行环境
JDK 1.7 +

--------

如下为设计的语言：
示例1：

```
define main() {
	a = 10;
	print(a);
	
	return a;
}
```

示例2

```
define main() {
	a = 10;
	print(a);
	print("hello");
	print("bye bye");
	print(8);
	print(9.2);
	return a;
}

```
示例3
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
