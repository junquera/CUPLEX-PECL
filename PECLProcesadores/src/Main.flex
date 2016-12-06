import java_cup.runtime.Symbol;
%%
%{
	// Java code
%}

// Extraídos del manual http://jflex.de/manual.html
LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]


RESERVADAS="program|is|begin|end|var|integer|boolean|read|write|skip|while|do|if|then|else|and|or|true|false|not"

ASIGNACION=":="

RELACION="<=|<|=|>|>=|<>"

MATH="+|-|*|/"

PUNCT="(|)|,|;|:"

IDENTIFIER=[A-Za-z][A-Za-z0-9]*

INTEGER="[+-]?[1-9][0-9]*"

BOOLEAN="true|false"
%cup
%line
%column
%full
%notunix
%%

"program" { return new Symbol(sym.PROGRAM); }
"is" { return new Symbol(sym.IS); }
"begin" { return new Symbol(sym.BEGIN); }
"end" { return new Symbol(sym.END); }
"var" { return new Symbol(sym.VAR); }
"integer" { return new Symbol(sym.IS_INTEGER); }
"boolean" { return new Symbol(sym.IS_BOOLEAN); }
"read" { return new Symbol(sym.READ); }
"write" { return new Symbol(sym.WRITE); }
"skip" { return new Symbol(sym.SKIP); }
"while" { return new Symbol(sym.WHILE); }
"do" { return new Symbol(sym.DO); }
"if" { return new Symbol(sym.IF); }
"then" { return new Symbol(sym.THEN); }
"else" { return new Symbol(sym.ELSE); }
"and" { return new Symbol(sym.AND); }
"or" { return new Symbol(sym.OR); }
"true" { return new Symbol(sym.TRUE); }
"false" { return new Symbol(sym.FALSE); }
"not" { return new Symbol(sym.NOT); }

":=" {return new Symbol (sym.ASIGNACION); }

"<=" { return new Symbol(sym.MINOR_EQUAL); }
"<" { return new Symbol(sym.MINOR); }
"=" { return new Symbol(sym.EQUAL); }
">" { return new Symbol(sym.MAJOR); }
">=" { return new Symbol(sym.MAJOR_EQUAL); }
"<>" { return new Symbol(sym.MINOR_MAJOR); }

"+" {return new Symbol (sym.SUMA); }
"-" {return new Symbol (sym.RESTA); }
"*" { return new Symbol (sym.MULTIPLICACION); }
"/" {return new Symbol (sym.DIVISION); }


"(" {return new Symbol (sym.LPARENT); }
")" {return new Symbol (sym.RPARENT); }
"," {return new Symbol (sym.COMA); }
";" {return new Symbol (sym.SEMI_COLON); }
":" {return new Symbol (sym.COLON); }

{IDENTIFIER} { return new Symbol (sym.IDENTIFIER, yytext().toUpperCase());  }
{INTEGER} { return new Symbol(sym.INTEGER, new Integer(yytext())); }
{BOOLEAN} { return new Symbol(sym.BOOLEAN, new Boolean(yytext())); }

{WhiteSpace} { }

. { 
	System.err.printf("Hay un error léxico en la columna %d de la fila %d:\n %s\n", yycolumn, yyline, yytext());
}
