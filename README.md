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


###License


The MIT License (MIT)

Copyright (c) 2014 fengzishiren

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
