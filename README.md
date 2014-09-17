tinylang



========

###运行环境
JDK 1.7 +

--------




###tinylang语法：

```
program             -> (function + )+ EOF
function 	    -> 'define' ID '(' (ID (',' ID)*)? ')' block
block               -> '{' stmts '}'
stmts               ->  stmts stmt
                       | E
stmt                -> 'print' expr ;
                       | 'return' expr ;
                       | call ;
                       | assign ;
                       | 'if' expr stmts ('else' stmts)?
                       | 'while' expr stmts
                       | E
assign              -> ID '=' expr
expr                -> expr (('+' | '-' | '*' | '/') expr)?
factor              -> ID | INT | FLOAT | BOOL | STRING | call
call                -> ID '(' (expr (',' expr)*)? ')'
```



###语言特性：
目前被设计为趋近于函数式风格
* 执行例程从代码中搜索main函数（无参）并执行
* 内置 print函数
* 不支持全局变量（暂时）
* main函数可以返回任意值给解释器




####代码示例：

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
