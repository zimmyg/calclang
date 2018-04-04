grammar CalcField;

calculation
    : expression EOF
    ;

expression
    : LPAREN expression RPAREN                                  #parenExpr
    | lhs=expression binaryOp rhs=expression                    #binaryOpExpr
    | lhs=expression (INLIST | NOTINLIST) rhs=listExpression    #inListExpr
    | unaryOp exp=expression                                    #unaryOpExpr
    | functionCall                                              #funcExpr
    | caseStatement                                             #caseExpr
    | literal                                                   #literalExpr
    ;

listExpression
    : LPAREN ( expression (COMMA expression)* )? RPAREN;

binaryOp
    : MUL
    | DIV
    | MOD
    | ADD
    | SUB
    | AND
    | OR
    | GT
    | LT
    | LE
    | GE
    | EQUAL
    | NOTEQUAL
    ;

unaryOp
    : ADD
    | SUB
    | BANG
    ;

comparison
    : LPAREN comparison RPAREN                              #parenComp
    | BANG cpm=comparison                                   #notComp
    | lhs=comparison (OR | AND) rhs=comparison              #logicalComp
    | lhs=expression (EQUAL | NOTEQUAL) rhs=expression      #equalityComp
    | lhs=expression (GT | LT | LE | GE) rhs=expression     #equalityComp
    | expression                                            #exprComp
    ;

caseStatement
    : CASE (WHEN cmp=comparison THEN thenExp=expression)+ (ELSE elseExp=expression)? END
    ;

functionCall
    : IDENTIFIER LPAREN ( expression (COMMA expression)* )? RPAREN
    ;


literal
    : NUMERIC_LITERAL
    | STRING_LITERAL
    | BOOL_LITERAL
    | IDENTIFIER
    ;

// Keywords
CASE:               'case' | 'CASE';
WHEN:               'when' | 'WHEN';
THEN:               'then' | 'THEN';
ELSE:               'else' | 'ELSE';
END:                'end'  | 'END';

// Literals
NUMERIC_LITERAL
    : DECIMAL_LITERAL
    | FLOAT_LITERAL
    ;

fragment DECIMAL_LITERAL:    ('0' | Digits);

fragment FLOAT_LITERAL
    : ((Digits '.' Digits+) | ('.' Digits)) ExponentPart?
    | Digits (ExponentPart)
    ;

BOOL_LITERAL:       'true' | 'TRUE' | 'false' | 'FALSE' ;

STRING_LITERAL:     '"' (~["\\\r\n] | EscapeSequence)* '"';


// Separators
LPAREN:             '(';
RPAREN:             ')';
COMMA:              ',';

// Operators
GT:                 '>';
LT:                 '<';
BANG:               '!';
EQUAL:              '=';
LE:                 '<=';
GE:                 '>=';
NOTEQUAL:           '!=';
AND:                '&&';
OR:                 '||';
ADD:                '+';
SUB:                '-';
MUL:                '*';
DIV:                '/';
CARET:              '^';
MOD:                '%';
INLIST:             'IN';
NOTINLIST:          'NOT IN';

// Whitespace and comments
WS:                 [ \t\r\n\u000C]+ -> channel(HIDDEN);

// Identifiers
IDENTIFIER:         Letter LetterOrDigit*;

// Fragment rules
fragment ExponentPart
    : [eE] [+-]? Digits
    ;

fragment EscapeSequence
    : '\\' [btnfr"'\\]
    | '\\' ([0-3]? [0-7])? [0-7]
    | '\\' 'u'+ HexDigit HexDigit HexDigit HexDigit
    ;
fragment HexDigit
    : [0-9a-fA-F]
    ;
fragment Digits
    : [0-9] ([0-9_]* [0-9])?
    ;
fragment LetterOrDigit
    : Letter
    | [0-9]
    ;
fragment Letter
    : [a-zA-Z$_]
    | ~[\u0000-\u007F\uD800-\uDBFF]
    | [\uD800-\uDBFF] [\uDC00-\uDFFF]
    ;