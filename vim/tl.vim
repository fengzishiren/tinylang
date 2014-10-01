" Vim syntax file
" Language		: 	tinylang
" Author		:	fengzishiren <xiaoyaozi106@163.com>
" Last Change	:	2014 Sep 24

:syntax keyword tlStmt	false, true, null
:syntax keyword tlStmt	continue break
:syntax keyword tlStmt	lambda return in
:syntax keyword tlBuiltin print type len size version str sprintf cons car cdr append remove
:syntax keyword tlType	define
:syntax keyword tlConditional else if
:syntax keyword tlRepeat for while foreach 
":syntax keyword tlOperator	|| &&
:syntax region tlString start=+"+ skip=+\\\\\|\\"+ end=+"+ 
:syntax match tlComment /#.*/ 

:syntax match tlNumber	"\<0[oO]\=\o\+[Ll]\=\>"
:syntax match tlNumber	"\<0[xX]\x\+[Ll]\=\>"
:syntax match tlNumber	"\<0[bB][01]\+[Ll]\=\>"
:syntax match tlNumber	"\<\%([1-9]\d*\|0\)[Ll]\=\>"
:syntax match tlNumber	"\<\d\+[jJ]\>"
:syntax match tlNumber	"\<\d\+[eE][+-]\=\d\+[jJ]\=\>"
:syntax match tlNumber
	\ "\<\d\+\.\%([eE][+-]\=\d\+\)\=[jJ]\=\%(\W\|$\)\@="
:syntax match pythonNumber
	\ "\%(^\|\W\)\@<=\d*\.\d\+\%([eE][+-]\=\d\+\)\=[jJ]\=\>"

:highlight link tlStmt	Statement
:highlight link tlType	define
:highlight link tlConditional	Conditional
:highlight link tlRepeat		Repeat
:highlight link tlOperator		Operator
:highlight link tlComment		Comment
:highlight link tlString		String
:highlight link tlBuiltin		Function
:highlight link tlNumber		Number
