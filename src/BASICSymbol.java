import java.util.HashMap;

/**
 * Created by junquera on 4/12/17.
 */

public class BASICSymbol extends java_cup.runtime.Symbol {
    private int line;
    private int column;

    public BASICSymbol(int type, int line, int column) {
        this(type, line, column, -1, -1, null);
    }

    public BASICSymbol(int type, int line, int column, Object value) {
        this(type, line, column, -1, -1, value);
    }

    public BASICSymbol(int type, int line, int column, int left, int right, Object value) {
        super(type, left, right, value);
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String toTokenString(HashMap<Integer, String> tokenValues) {
        return tokenValues.get(sym)  + (value == null ? "" : ("("+value+")"));
    }

    public String toString() {
        return "line "+line+", column "+column+", sym: "+sym+(value == null ? "" : (", value: '"+value+"'"));
    }
}
