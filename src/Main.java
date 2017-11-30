/**
 * Created by junquera on 8/11/17.
 */

import java_cup.runtime.Symbol;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    static boolean do_debug_parse = false;

    public static void main(String[] args) throws Exception {

        String program ="src/programa1.bas";
        if(args.length > 1)
            program = args[1];

        // RS 1.- El analizador solamente ha de aceptar ficheros con extensión “.bas”.
        if(!program.matches("^.+\\.bas"))
            throw new Exception("La extensión del archivo tiene que ser .bas");

        // parser parser_obj = new parser(new BASICLexer(new InputStreamReader(System.in)));
        parser parser_obj = new parser(new BASICLexer(new InputStreamReader(new FileInputStream(program))));
        Symbol parse_tree = null;
        try {

            if (do_debug_parse)
                parse_tree = parser_obj.debug_parse();
            else
                parse_tree = parser_obj.parse();
            System.out.println(parse_tree);
            System.out.println("Entrada correcta");
        } catch (Exception e){
            System.out.println("Horror");
        }
    }
}
