import java_cup.runtime.Symbol;
import java.util.ArrayList;
%%
%{
	ArrayList<String> tokens = new ArrayList<String>();
%}


// Extraídos del manual http://jflex.de/manual.html
LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]


IDENTIFIER=[A-Za-z][A-Za-z0-9]*

INTEGER=[+-]?[1-9]*[0-9]

BOOLEAN=true|false

%eof{
	System.out.println("Análisis Léxico completado");
	System.out.printf("[");
	for(String s: tokens)
		System.out.printf("%s, ", s);
	System.out.printf("]\n\n");
%eof}
%cup
%line
%column
%full
%notunix
%%

{WhiteSpace} { }

"program" { tokens.add("program"); return new Symbol(sym.PROGRAM); }
"is" { tokens.add("is"); return new Symbol(sym.IS); }
"begin" { tokens.add("begin"); return new Symbol(sym.BEGIN); }
"end" { tokens.add("end"); return new Symbol(sym.END); } // TODO Set EOF
"var" { tokens.add("var"); return new Symbol(sym.VAR); }
"integer" { tokens.add("integer"); return new Symbol(sym.IS_INTEGER); }
"boolean" { tokens.add("boolean"); return new Symbol(sym.IS_BOOLEAN); }
"read" { tokens.add("read"); return new Symbol(sym.READ); }
"write" { tokens.add("write"); return new Symbol(sym.WRITE); }
"while" { tokens.add("while"); return new Symbol(sym.WHILE); }
"do" { tokens.add("do"); return new Symbol(sym.DO); }
"if" { tokens.add("if"); return new Symbol(sym.IF); }
"then" { tokens.add("then"); return new Symbol(sym.THEN); }
"else" { tokens.add("else"); return new Symbol(sym.ELSE); }

"and" { tokens.add("and"); return new Symbol(sym.AND); }
"or" { tokens.add("or"); return new Symbol(sym.OR); }
"not" { tokens.add("not"); return new Symbol(sym.NOT); }

":=" { tokens.add("asign"); return new Symbol (sym.ASIGNACION); }

"<=" { tokens.add("minor_equal"); return new Symbol(sym.MINOR_EQUAL); }
"<" { tokens.add("minor"); return new Symbol(sym.MINOR); }
"=" { tokens.add("equal"); return new Symbol(sym.EQUAL); }
">" { tokens.add("major"); return new Symbol(sym.MAJOR); }
">=" { tokens.add("major_equal"); return new Symbol(sym.MAJOR_EQUAL); }

"+" { tokens.add("suma"); return new Symbol (sym.SUMA); }
"-" { tokens.add("resta"); return new Symbol (sym.RESTA); }
"*" { tokens.add("multiplicacion"); return new Symbol (sym.MULTIPLICACION); }
"/" { tokens.add("division"); return new Symbol (sym.DIVISION); }


"(" { tokens.add("lparent"); return new Symbol (sym.LPARENT); }
")" { tokens.add("rparent"); return new Symbol (sym.RPARENT); }
"," { tokens.add("coma"); return new Symbol (sym.COMA); }
";" { tokens.add("semi_colon"); return new Symbol (sym.SEMI_COLON); }
":" { tokens.add("colon"); return new Symbol (sym.COLON); }

{INTEGER} { tokens.add("integer"); return new Symbol(sym.INTEGER, new Integer(yytext())); }
{BOOLEAN} { tokens.add("boolean"); return new Symbol(sym.BOOLEAN, new Boolean(yytext())); } // Tengo que poner BOOLEAN antes que IDENTIFIER porque si no, todo se lo asigna a IDENTIFIER
{IDENTIFIER} { tokens.add("ide(" + yytext() + ")"); return new Symbol (sym.IDENTIFIER, yytext().toUpperCase());  }

. { 
	System.err.printf("Hay un error léxico en la columna %d de la fila %d:\n %s\n", yycolumn, yyline, yytext());
}

