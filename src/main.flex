import java_cup.runtime.*;

%%

%class BASICLexer

%unicode

%line
%column

%cup

%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }

%}


/* EL 1. */
Letra = [A-Z]
Digito = [0-9]
Car_Cadena = [?] | {Car_Cad_Delimitado}
Car_Cad_Delimitado = [!#$%&'()*,/:;<=>?^_] | {Car_No_Delimitado}
Car_No_Delimitado = [ ] | {Car_Cadena_Simple}
Car_Cadena_Simple = [+-.] | {Digito} | {Letra}
Cad_REM = {Car_Cadena}*
Cad_Delimitada = ["]{Car_Cad_Delimitado}*["]
Cad_No_Delimitada = {Car_Cadena_Simple} | {Car_Cadena_Simple}{Cad_NoDelimitada}*{Car_Cadena_Simple}

/* TODO No estoy seguro de que esto sea del todo correcto */
LF = lf
CR = cr
EOF = eof

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

Num_Entero= [+-]?{Digito}+
Num_Real = {Entero}[.]{Digito}+
Num_Escalar = {Num_Real}[E]{Num_Entero}
Constante_Num = {Num_Entero}|{Num_Real}|{Num_Escalar}

%%

<YYINITIAL> {

    /* EL 2. Palabras reservadas */
    "DATA" { return symbol(sym.DATA); }
    "DEF" { return symbol(sym.DEF); }
    "DIM" { return symbol(sym.DIM); }
    "END" { return symbol(sym.END); }
    "FOR" { return symbol(sym.FOR); }
    "GO" { return symbol(sym.GO); }
    "GOSUB" { return symbol(sym.GOSUB); }
    "GOTO" { return symbol(sym.GOTO); }
    "IF" { return symbol(sym.IF); }
    "INPUT" { return symbol(sym.INPUT); }
    "LET" { return symbol(sym.LET); }
    "NEXT" { return symbol(sym.NEXT); }
    "ON" { return symbol(sym.ON); }
    "PRINT" { return symbol(sym.PRINT); }
    "RANDOMIZE" { return symbol(sym.RANDOMIZE); }
    "READ" { return symbol(sym.READ); }
    "REM" { return symbol(sym.REM); }
    "RESTORE" { return symbol(sym.RESTORE); }
    "RETURN" { return symbol(sym.RETURN); }
    "STEP" { return symbol(sym.STEP); }
    "STOP" { return symbol(sym.STOP); }
    "THEN" { return symbol(sym.THEN); }
    "TO" { return symbol(sym.TO); }

    /* EL 3. Funciones predefinidas */
    "ABS" { return symbol(sym.ABS); }
    "ATN" { return symbol(sym.ATN); }
    "COS" { return symbol(sym.COS); }
    "EXP" { return symbol(sym.EXP); }
    "INT" { return symbol(sym.INT); }
    "LOG" { return symbol(sym.LOG); }
    "RND" { return symbol(sym.RND); }
    "SGN" { return symbol(sym.SGN); }
    "SIN" { return symbol(sym.SIN); }
    "SQR" { return symbol(sym.SQR); }
    "TAN" { return symbol(sym.TAN); }

    /* EL 4. Identificadores */

    /* EL 5. Constantes */
    {Cad_Delimitada} {return symbol(sym.CONST_CADENA, new String(yytext()); }


    /* EL 6. */
    {WhiteSpace} {}

    . {System.out.println("Error <" + yytext() + "> linea: " + (yyline + 1) + " columna: " + (yycolumn + 1)); }
}