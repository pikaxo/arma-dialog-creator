//author: Kayler

grammar Expression;

statements returns [List<AST.Statement> lst] @init{ $lst = new ArrayList<>(); }:
    (s=statement Semicolon {$lst.add($s.ast);})*
    s2=statement Semicolon? {$lst.add($s2.ast);}
    ;

statement returns [AST.Statement ast]:
    a=assignment {$ast = new AST.Statement($a.ast);}
    | e=expression {$ast = new AST.Statement($e.ast);}
    ;

assignment returns [AST.Assignment ast]:
    i=Identifier Equal e=expression {$ast = new AST.Assignment($i.text, $e.ast);}
    ;

code returns [AST.Code ast] locals[List<AST.Statement> lst] @init{ $lst = new ArrayList<>(); }:
    LCurly (s=statements {$lst=$s.lst;})? RCurly {$ast = new AST.Code($lst);}
    ;

expression returns [AST.Expr ast]:
//todo binary logical operators and !expression
    lu=unary_expression {$ast = $lu.ast;}
    | lp=paren_expression {$ast = $lp.ast;}
    | ls=expression Star rs=expression {$ast = new AST.MultExpr($ls.ast, $rs.ast);}
    | lf=expression FSlash rf=expression {$ast = new AST.DivExpr($lf.ast, $rf.ast);} //don't mess with order of arguments because of order of operations
    | la=expression Plus ra=expression {$ast = new AST.AddExpr($la.ast, $ra.ast);}
    | lm=expression Minus rm=expression {$ast = new AST.SubExpr($lm.ast, $rm.ast);}
    | lcomp=expression compOp=(EqEq | NotEq | LtEq | Lt | GtEq | Gt) rcomp=expression {$ast = new AST.CompExpr($lcomp.ast, $rcomp.ast, $compOp.text);}
    | ll=literal_expression {$ast = $ll.ast;}
    | lmax=expression Max rmax=expression {$ast = new AST.MaxExpr($lmax.ast, $rmax.ast);}
    | lmin=expression Min rmin=expression {$ast = new AST.MinExpr($lmin.ast, $rmin.ast);}
    | select_e=expression Select select_i=expression {$ast = new AST.SelectExpr($select_e.ast, $select_i.ast);}
    | ifexp=if_expression {$ast = $ifexp.ast;}
    | forexp=for_expression {$ast = $forexp.ast;}
    | codeExp=code {$ast = new AST.CodeExpr($codeExp.ast);}
    ;

unary_expression returns [AST.UnaryExpr ast]:
    Plus ep=paren_expression {$ast = new AST.UnaryExpr(true, $ep.ast);}
    | Plus ep1=literal_expression {$ast = new AST.UnaryExpr(true, $ep1.ast);}
    | Minus em=paren_expression {$ast = new AST.UnaryExpr(false, $em.ast);}
    | Minus em1=literal_expression {$ast = new AST.UnaryExpr(false, $em1.ast);}
    ;

paren_expression returns [AST.ParenExpr ast]:
    LParen e=expression RParen {$ast = new AST.ParenExpr($e.ast);}
    ;

literal_expression returns [AST.LiteralExpr ast]:
    id=Identifier {$ast = new AST.IdentifierExpr($id.text);}
    | i=int_value {$ast = new AST.IntegerExpr($i.i);}
    | f=float_value {$ast = new AST.FloatExpr($f.d);}
    | s=String {$ast = new AST.StringExpr($s.text);}
    | a=array {$ast = $a.ast;}
    ;

if_expression returns [AST.IfExpr ast]:
    If cond=expression (
        (ExitWith exitWith=expression {$ast = new AST.IfExpr($cond.ast, $exitWith.ast, null, AST.IfExpr.Type.ExitWith);})
        | (Then arr=array       {$ast = new AST.IfExpr($cond.ast, $arr.ast);})
        | (Then condIsTrue=expression Else condIsFalse=expression {$ast = new AST.IfExpr($cond.ast, $condIsTrue.ast, $condIsFalse.ast, AST.IfExpr.Type.IfThen);})
        | (Then condIsTrue=expression {$ast = new AST.IfExpr($cond.ast, $condIsTrue.ast, null, AST.IfExpr.Type.IfThen);})
    )
    ;

for_expression returns [AST.ForExpr ast]:
    (
     For var=expression From fromExp=expression To toExp=expression Step stepExp=expression Do doExp=expression
     {$ast = new AST.ForVarExpr($var.ast, $fromExp.ast, $toExp.ast, $stepExp.ast, $doExp.ast);}
     )
    |(
     For var=expression From fromExp=expression To toExp=expression Do doExp=expression
     {$ast = new AST.ForVarExpr($var.ast, $fromExp.ast, $toExp.ast, null, $doExp.ast);}
     )
    |(
     For arr=expression Do doExp=expression {$ast = new AST.ForArrExpr($arr.ast, $doExp.ast);}
    )
    ;

array returns [AST.Array ast] locals[List<AST.Expr> items] @init{$items = new ArrayList<>();}:
    LBracket
    e1=expression {$items.add($e1.ast);}
    (Comma e2=expression {$items.add($e2.ast);})*
    RBracket {$ast = new AST.Array($items);}
    ;

int_value returns [Integer i]:
    il=IntegerLiteral {$i = new Integer($il.text);}
    | hl = HexLiteral {$i = new Integer(Integer.decode($hl.text));}
    ;

float_value returns [Double d]:
    fl=FloatLiteral {$d = new Double($fl.text);}
    ;

String : (Quote ~('\'')* Quote)+ | (DQuote ~('"')* DQuote)+ ;
Quote : '\'';
DQuote : '"';

LCurly : '{';
RCurly : '}';
LBracket : '[';
RBracket : ']';

Plus : '+';
Minus : '-';
FSlash : '/';
Star : '*';
LParen : '(';
RParen : ')';
Comma :',';
Min : 'min';
Max : 'max';
If : 'if';
Then : 'then';
Else : 'else';
ExitWith : 'exitWith';
Select : 'select';
For :'for';
From :'from';
To :'to';
Step :'step';
Do :'do';
EqEq : '==' ;
NotEq : '!=' ;
Lt : '<' ;
LtEq : '<=' ;
Gt : '>' ;
GtEq : '>=' ;
Equal : '=' ;
Semicolon : ';';


Identifier :  Letter LetterOrDigit*;
IntegerLiteral : Digits;
FloatLiteral : (DecSignificand | DecExponent);

Digits : DIGIT+;
DecSignificand : '.' Digits | Digits '.' DIGIT+;
DecExponent : (DecSignificand | IntegerLiteral) [Ee] [+-]? DIGIT*;

HexLiteral : '0' [xX] '0'* HexDigits ;
HexDigits  : [0-9a-fA-F]+; //allow between 1 and 8 hex digits

Letter :   [a-zA-Z$_]
    |   ~[\u0000-\u00FF\uD800-\uDBFF]
    {Character.isJavaIdentifierStart(_input.LA(-1))}?
    |   [\uD800-\uDBFF] [\uDC00-\uDFFF]
    {Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}? ;
LetterOrDigit: [a-zA-Z0-9$_]
    |   ~[\u0000-\u00FF\uD800-\uDBFF]
    {Character.isJavaIdentifierPart(_input.LA(-1))}?
    |    [\uD800-\uDBFF] [\uDC00-\uDFFF]
    {Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?;

WhiteSpace : (' '|'\t'|'\r'|'\n'|'\r\n') -> skip; //ignore whitespace

fragment DIGIT: ('0'..'9');
