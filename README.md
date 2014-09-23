The Tinylang Programming Language
====

我将用业余时间设计一门语言，这是我自编程以来一直的一个梦想。语言的名字还没想到，但我知道这至关重要，按照松本行弘先生的看法，一个名字起的很好的项目通常都会获得成功。

然， 路漫漫其修远兮，吾将上下而求索。

========

###运行环境
JDK 1.7 +

--------

当前状态：
--------
目前已经支持的功能：
* 函数的调用，包括参数的传递和返回
* if、else、while、for、foreach、break、return语句
* 内置数据结构：list(ArrayList)和dict。
* 支持高阶、lambda和闭包
* 内置lisp系函数cons、car和cdr




语言风格：
--------
几乎是我心目中语言的样子： 

函数定义以关键字 “define”开头，借鉴了Lisp方言Scheme的定义方式，以后陆续会有限制的加入一些函数式语言的东西。
和C语言相比，最直观的区别是没有类型系统。跟python倒很相似，除了“:”和缩进改为“{”， “}”和“;”，当然跟javascript也很像。

另：数据结构以List和Dict为主，使用方式和python类似。





-----------------------



tinylang语法：
--------

```
program             -> (function + )+ EOF
function            -> 'define' ID '(' (ID (',' ID)*)? ')' block
block               -> '{' stmts '}'
stmts               ->  stmts1 stmt
                       | E
stmt                -> 'print' '(' expr ')';
                       | 'return' expr ;
                       | call ;
                       | assign ;
                       | 'if' '(' expr ')' stmts1 ('else' stmts1)?
                       | 'while' '(' expr ')' stmts1
                       | 'for' '(' opexpr ';' opexpr ';' opexpr ')'
                       | 'foreach' '(' ID (',' ID)? in factor ')'
                       | E
assign              -> ID '=' bool | ID ('[' index ']')+ '=' bool | (ID ('[' index ']')*  '.')? call 
bool                -> > expr (( < | == | >= | <=) expr)?
expr                -> term (('+' | '-') term)?
term                -> factor ('*' | '/') factor)?
factor              -> ID | INT | FLOAT | BOOL | STRING | call | struct | access | lambda
call                -> ID '(' (expr (',' expr)*)? ')'
struct              -> list | dict
list                -> '[' (bool)* ']'
dict                -> '{' (INT | FLOAT | BOOL | STRING) ':' bool '}'
access              -> ID ('[' ',' ']')+ 
lambda              -> 'lambda' '(' (ID (',' ID)*)? ')' block
```



语言特性：
--------
目前被设计为趋近于函数式风格
* 执行例程从代码中搜索main函数（无参）并执行
* 内置 print, len, remove(list, dict), append(list), type, version和lisp函数：cons, car, cdr函数
* 不支持全局变量（暂时）
* main函数可以返回任意值给解释器



代码示例：
--------

以下示例代码在tinylang/langs目录下，全部通过测试成功运行并正确返回

示例1：

```python
define main() {
	a = 10;
	print(a);
	
	return a;
}
```


示例2:

```python

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

```


示例3:

```python
define hign_order(a, how) {
	how(a);
	return how;
}

define test() {
	print("print");
}

define main() {
	t = test;
	t();
	print(9);
	how = hign_order(100, print);
	how(100000000);
	return how;
}

```

示例4：
```python
define main() {
	a = [1, 2, 3, 4, 5, 6];
	
	foreach( e in a) {
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
	
	a.remove(6)
	;
	print(a);
	return len(a);
}
```

示例5：
```python
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
```

示例6：
```python
define eval(a,b) {
	t = a;
	a = a + b;
	b = t - b;
	return cons(a, b);
}

//cons, car, cdr用来模拟Lisp：
//(define (cons x y)
//	(lambda (m) (m x y)))
//(define (car z)
//  (z (lambda (p q) p)))
//(define (cdr z)
//  (z (lambda (p q) q)))

define cadr() {

	a = 10;
	b = 200;
	pair = cons(a, cons(a, b ));
	print(pair);
	print(cdr(pair));
	
	print("a:", a, " b:", b);
	
	val = eval(a, b);
	
	print("a:", car(val), " b:", cdr(val));
	
	return 0;
}

define handle(a, hd) {
	hd(a, 10000);
}

define main() {

	a = 10;
	handle(a, lambda (x, y) {print(x, y);});
	
	f = lambda (x) {print(x);};
	f("lambda test");
 
	return cadr();
}
```

参考：
--------
* yin：https://github.com/yinwang0/yin
* 编译原理：http://book.douban.com/subject/3296317/


License
--------

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
