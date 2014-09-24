" Vim syntax file
" Language		: 	tinylang
" Author		:	fengzishiren <xiaoyaozi106@163.com>
" Last Change	:	2014 Sep 24

:syntax keyword tlStmt	false, true
:syntax keyword tlStmt	break
:syntax keyword tlStmt	lambda return in
:syntax keyword tlBuiltin print type len version str sprintf cons car cdr append remove
:syntax keyword tlType	define
:syntax keyword tlConditional else if
:syntax keyword tlRepeat	for while foreach
":syntax keyword tlOperator	|| &&
:syntax region tlString start=+"+ skip=+\\\\\|\\"+ end=+"+ 
:syntax match tlComment /#.*/ 

:highlight link tlStmt	Statement
:highlight link tlType	define
:highlight link tlConditional	Conditional
:highlight link tlRepeat		Repeat
:highlight link tlOperator		Operator
:highlight link tlComment		Comment
:highlight link tlString		String
:highlight link tlBuiltin		Function

