/**
 * Created by junquera on 8/11/17.
 */

import java_cup.runtime.Symbol;

import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
    static boolean do_debug_parse = false;

    public static void main(String[] args) throws java.io.IOException {

        // parser parser_obj = new parser(new BASICLexer(new InputStreamReader(System.in)));
        parser parser_obj = new parser(new BASICLexer(new InputStreamReader(new FileInputStream("src/programa1.bas"))));
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
