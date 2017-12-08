/**
 * Created by junquera on 8/11/17.
 */

import java_cup.runtime.Symbol;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class Main {
    static boolean do_debug_parse = false;
    private Symbol s;

    public static void main(String[] args) throws Exception {
        parse(args);
    }

    public static void parse(String[] args) throws Exception {
        String program = "src/programa1.bas";
        if (args.length > 1)
            program = args[1];

        // RS 1.- El analizador solamente ha de aceptar ficheros con extensión “.bas”.
        if (!program.matches("^.+\\.bas"))
            throw new Exception("La extensión del archivo tiene que ser .bas");

        BASICLexer bl = new BASICLexer(new InputStreamReader(new FileInputStream(program)));
        parser parser_obj = new parser(bl);
        parser p = new parser();

        Symbol parse_tree = null;
        try {

            if (do_debug_parse)
                parse_tree = parser_obj.debug_parse();
            else
                parse_tree = parser_obj.parse();

            if(parser_obj.errors.size()>0){
                for(SyntaxError e: parser_obj.errors) {
                    System.err.println(e.toString());
                }
                System.out.println("Arregle los errores y vuelva a intentarlo.");
            } else {
                Node.Programa programa = (Node.Programa) parse_tree.value;

                programa.check();

                System.out.println("Análisis léxico:");
                System.out.println(bl);
                System.out.println("Análisis sintáctico:");
                System.out.println(programa.getTree());
                System.out.println("Tabla de símbolos:");
                System.out.println(programa.getTabla());

                System.out.println("Entrada correcta");
            }

        } catch (Exception e) {
            System.err.println("ERROR SEMÁNTICO:");
            System.err.println(e.getMessage());
        }
    }

    public static void lex(String[] args) throws Exception {
        String program = "src/programa1.bas";

        Map<Integer, String> tokenValues = new HashMap<>();

        Field[] fields = sym.class.getFields();


        for(Field f: fields){
            tokenValues.put(f.getInt(null), f.getName());
        }

        if (args.length > 1)
            program = args[1];

        // RS 1.- El analizador solamente ha de aceptar ficheros con extensión “.bas”.
        if (!program.matches("^.+\\.bas"))
            throw new Exception("La extensión del archivo tiene que ser .bas");

        BASICLexer bl = new BASICLexer(new InputStreamReader(new FileInputStream(program)));

        Symbol s;
        try {

            do {
                s = bl.next_token();
                // System.out.println("Token: " + s);
                System.out.println(tokenValues.get(s.sym) + (s.value != null? "(" + s.value + ")" : ""));
            } while( s.sym != sym.EOF);
            System.out.println("Entrada correcta");
        } catch (Exception e) {
            System.out.println("Horror");
        }
    }
}
