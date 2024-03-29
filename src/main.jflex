import java_cup.runtime.*;
import java.util.*;
import java.lang.reflect.Field;

%%

%class BASICLexer

%unicode

%cup
%line
%column
%function nextToken
%{
    List<BASICSymbol> tokens = new ArrayList<>();

    StringBuffer string = new StringBuffer();

    private Symbol symbol(int type) {
        return new BASICSymbol(type, yyline + 1, yycolumn + 1);
    }

    private Symbol symbol(int type, Object name){
        return new BASICSymbol(type, yyline + 1, yycolumn + 1, name);
    }

    public int getLine(){
        return yyline;
    }

    // Funcion para guardar los tokens
    public Symbol next_token() throws java.io.IOException{
        BASICSymbol s = (BASICSymbol) nextToken();
        tokens.add(s);
        return s;
    }

    public String toString(){
        HashMap<Integer, String> tokenValues = new HashMap<>();

        Field[] fields = sym.class.getFields();

        for(Field f: fields){
            try{
                tokenValues.put(f.getInt(null), f.getName());
            }catch(Exception e){}
        }

        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for(BASICSymbol s: tokens){
            sb.append(s.toTokenString(tokenValues) + ", ");
        }
        sb.append("]");
        return sb.toString();
    }
%}


/* EL 1. */
Letra = [A-Z]
Digito = [0-9]

Car_Cadena = [?] | {Car_Cad_Delimitado}
Car_Cad_Delimitado = [!#$%&'()*,/:;<=>?\^_] | {Car_No_Delimitado}
Car_No_Delimitado = [ ] | {Car_Cadena_Simple}
Car_Cadena_Simple = [+-.] | {Digito} | {Letra} | [a-z]
Cad_REM = {Car_Cadena}*
Cad_No_Delimitada = {Car_Cadena_Simple} | {Car_Cadena_Simple}{Cad_NoDelimitada}*{Car_Cadena_Simple}

LF = \n
CR = \r
/** EOF = \x00 | \x03 **/

LineTerminator = {LF}|{CR}|{LF}{CR}
WhiteSpace = [ \t\f]

Num_Entero= [+-]?{Digito}+
Num_Real = {Num_Entero}\.{Digito}+
Num_Escalar = {Num_Real}[E]{Num_Entero}

%state STRING
%state REM

%%

<YYINITIAL> {

    "^" { return symbol(sym.POW); }
    "*" { return symbol(sym.MUL); }
    "/" { return symbol(sym.DIV); }
    "+" { return symbol(sym.SUM); }
    "-" { return symbol(sym.SUB); }

    "=" { return symbol(sym.EQU); }
    "(" { return symbol(sym.LPAR); }
    ")" { return symbol(sym.RPAR); }

    "," { return symbol(sym.COMA); }
    ";" { return symbol(sym.PCOMA); }

    "<" { return symbol(sym.LT); }
    "<=" { return symbol(sym.LE); }
    ">=" { return symbol(sym.GE); }
    ">" { return symbol(sym.GT); }
    "<>" { return symbol(sym.NEQ); }


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
    "REM" { yybegin(REM); }
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
    {Letra} { return symbol(sym.VAR_NUM, new String(yytext())); }
    {Letra}"\$" { return symbol(sym.VAR_TXT, new String(yytext())); }

    "FN" { return symbol(sym.FN, new String(yytext())); }

    /* EL 5. Constantes */
    \" { string.setLength(0); yybegin(STRING); }

    {Num_Entero} { return symbol(sym.INTEGER, new Integer(yytext())); }
    {Num_Real}|{Num_Escalar} { return symbol(sym.FLOAT, new Float(yytext())); }



    /* EL 6. */
    {WhiteSpace} {}

    {LineTerminator} { return symbol(sym.CRLF); }

    /* {EOF} { return symbol(sym.EOF); } */
    <<EOF>> { return symbol(sym.EOF); }

    /* RL 1. */
    . {
        System.err.println("LexError <" + yytext() + "> linea: " + (yyline + 1) + " columna: " + (yycolumn + 1));
        return symbol(sym.ERROR, new LexError(yytext(), yyline, yycolumn));
    }
}

<STRING> {
    \"                    { yybegin(YYINITIAL); return symbol(sym.STRING, string.toString());  }
    {Car_Cadena}+          { string.append( yytext() ); }
}

<REM> {
    {Cad_REM} {yybegin(YYINITIAL); return symbol(sym.REM);}
}