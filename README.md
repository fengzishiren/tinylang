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
目前已经支持函数的调用，包括参数的传递和返回，以及if、else、while、break、return语句。下一步将支持list和dict




语言风格：
--------
几乎是我心目中语言的样子： 

函数定义以关键字 “define”开头，借鉴了Lisp方言Scheme的定义方式，以后陆续会有限制的加入一些函数式语言的东西。
和C语言相比，最直观的区别是没有类型系统。跟python倒很相似，除了“:”和缩进改为“{”， “}”和“;”，当然跟javascript也很像。

另：关于语言的数据结构正在思虑中。。。




-----------------------



tinylang语法：
--------

```
program             -> (function + )+ EOF
function            -> 'define' ID '(' (ID (',' ID)*)? ')' block
block               -> '{' stmts '}'
stmts               ->  stmts stmt
                       | E
stmt                -> 'print' '(' expr ')';
                       | 'return' expr ;
                       | call ;
                       | assign ;
                       | 'if' '(' expr ')' stmts ('else' stmts)?
                       | 'while' '(' expr ')' stmts
                       | E
assign              -> ID '=' bool
bool                -> > expr (( < | == | >= | <=) expr)?
expr                -> term (('+' | '-') term)?
term                -> factor ('*' | '/') factor)?
factor              -> ID | INT | FLOAT | BOOL | STRING | call | struct | access
call                -> ID '(' (expr (',' expr)*)? ')'
struct              -> list
                       |dict
list                -> '[' (bool)* ']'
dict                -> '{' (INT | FLOAT | BOOL | STRING) ':' bool '}'
access              -> ID ('[' ',' ']')+ 
```



语言特性：
--------
目前被设计为趋近于函数式风格
* 执行例程从代码中搜索main函数（无参）并执行
* 内置 print函数
* 不支持全局变量（暂时）
* main函数可以返回任意值给解释器




代码示例：
--------

以下示例代码在tinylang/langs目录下，全部通过测试成功运行并正确返回
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


示例3

```

define echo(msg) {
	print(msg);
	return msg;
}

define main() {
	a = 222;
	
	a = a + 250;
	if (a > 100) {
		print("hello");
		print("World");
	}
	
	if (a > 0)
		print("Hello World");
	msg = echo("hello");
	print(msg);
	
	return null;
}


```

示例4：
```
define main() {
	a = [1, 2, 3, 4, 5, 6];
	return a[3];
}
```

示例5：
```
define main() {
	b = {"zlh": "zhenglinhai", 9 : "xiaoyaogege", 1: {"kety": "value"}};
	return b[1]["kety"];
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
