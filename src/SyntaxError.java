/**
 * Created by junquera on 6/12/17.
 */
public class SyntaxError {

    private String message;
    private int line;
    private int column;

    public SyntaxError(String message, int line, int column) {
        this.message = message;
        this.line = line;
        this.column = column;
    }

    public String getMessage() {
        return message;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String toString() {

        String res = message + " (at line " + line;
        if(column >= 0)
            res += " and column " + column;
        return res + ")";
    }
}
