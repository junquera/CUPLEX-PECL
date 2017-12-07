import java.util.ArrayList;
import java.util.List;

/**
 * Created by junquera on 6/12/17.
 */
public abstract class Node {

    public static class Variable extends Node {

        public static final int NUMERIC = 0;
        public static final int STRING = 1;

        private String name;
        private int type;

        public Variable() {
        }

        public Variable(String name) {
            this.name = name;
        }

        public Variable(String name, int type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            String result = "Variable " + this.name + ", type: ";
            switch (type){
                case NUMERIC:
                    result += "numeric";
                    break;
                case STRING:
                    result += "string";
                    break;
                default:
                    result += "unknown";
            }
            return result;
        }
    }

    public static class Literal extends Node {

        public static final int NUMERIC = 0;
        public static final int STRING = 1;

        private Object content;
        private int type;

        public Literal() {
        }

        public Literal(Object content) {
            this.content = content;
        }

        public Literal(Object content, int type) {
            this.content = content;
            this.type = type;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public String toString() {
            switch (type) {
                case NUMERIC:
                    return "Literal numérico: " + content;
                case STRING:
                    return "Literal cadena: " + content;
                default:
                    return "Literal desconocido...";
            }
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }


    public static class BinExpression extends Node {
        public static final int BASIC = 1;
        public static final int POW = 2;
        public static final int MUL = 3;
        public static final int DIV = 4;
        public static final int SUM = 5;
        public static final int SUB = 6;

        private int op;

        public BinExpression() {
        }

        public BinExpression(int op) {
            this.op = op;
        }

        public void addSonNode(Node n) throws Exception {

            if (n.getType() != Literal.NUMERIC)
                throw new Exception("Sólo acepto números");

            super.addSonNode(n);
        }


        public String toString() {
            switch (op) {
                case BASIC:
                    return getNodeType() + ": BASIC";
                case POW:
                    return getNodeType() + ": POW";
                case MUL:
                    return getNodeType() + ": MUL";
                case DIV:
                    return getNodeType() + ": DIV";
                case SUM:
                    return getNodeType() + ": SUM";
                case SUB:
                    return getNodeType() + ": SUB";
                default:
                    return getNodeType() + ": OP. DESCONOCIDA";
            }
        }

        public int getOp() {
            return op;
        }

        public void setOp(int op) {
            this.op = op;
        }

        public int getType() {
            return Literal.NUMERIC;
        }
    }


    public static class CondExpression extends Node {

        public static final int LT = 7;
        public static final int LE = 8;
        public static final int GE = 9;
        public static final int GT = 10;
        public static final int EQU = 11;
        public static final int NEQ = 12;

        private int op;

        public CondExpression() {
        }

        public CondExpression(int op) {
            this.op = op;
        }

        public int getOp() {
            return op;
        }

        public void setOp(int op) {
            this.op = op;
        }

        public String toString() {
            switch (op) {
                case LT:
                    return getNodeType() + ": LT";
                case LE:
                    return getNodeType() + ": LE";
                case GE:
                    return getNodeType() + ": GE";
                case GT:
                    return getNodeType() + ": GT";
                case EQU:
                    return getNodeType() + ": EQU";
                case NEQ:
                    return getNodeType() + ": NEQ";
                default:
                    return getNodeType() + ": OP. DESCONOCIDA";
            }
        }

        public int getType() {
            return Literal.NUMERIC;
        }
    }

    public static class DeclDim extends Node {
        public DeclDim() {
        }
    }

    public static class Funcion extends BinExpression {
        private boolean created;

        private String value;

        public Funcion(String value) {
            this.value = value;
            this.created = false;
        }

        public Funcion(String value, boolean created) {
            this.value = value;
            this.created = created;
        }

        public boolean isCreated() {
            return created;
        }

        public void setCreated(boolean created) {
            this.created = created;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class Programa extends Node {
        public Programa() {
        }
    }

    public static class Linea extends Node {

        private int lineNumber;

        public Linea(int ln) {
            this.lineNumber = ln;
        }

        public int getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(int lineNumber) {
            this.lineNumber = lineNumber;
        }

        public String toString() {
            return this.lineNumber + " " + super.toString();
        }
    }

    public static class Sentencia extends Node {
        public Sentencia() {
        }
    }

    public static class Asignacion extends Node {
        public Asignacion() {
        }
    }

    public static class GoTo extends Node {
        public GoTo() {
        }
    }

    public static class IfThen extends Node {

        public IfThen() {
        }


    }

    public static class GoSub extends Node {
        public GoSub() {
        }
    }

    public static class OnGoTo extends Node {
        public OnGoTo() {
        }
    }

    public static class Stop extends Node {
        public Stop() {
        }
    }

    public static class ForTo extends Node {
        public ForTo() {
        }
    }

    public static class Next extends Node {
        public Next() {
        }
    }

    public static class Print extends Node {
        public Print() {
        }
    }

    public static class Input extends Node {
        public Input() {
        }
    }

    public static class DefFuncion extends Node {
        public DefFuncion() {
        }
    }

    public static class Data extends Node {
        public Data() {
        }
    }

    public static class Read extends Node {
        public Read() {
        }
    }

    public static class Dim extends Node {
        public Dim() {
        }
    }

    private ArrayList<Node> sons;

    public Node() {
        this.sons = new ArrayList<>();
    }

    public void addSonNode(Node n) throws Exception {
        this.sons.add(n);
    }

    public List<Node> getSons() {
        return sons;
    }

    public String toString() {
        return getNodeType();
    }

    public String getNodeType() {
        return this.getClass().getName();
    }

    public int getType() {
        return -1;
    }

    public String getTree() {
        return getTree(0);
    }

    public String getTree(int level) {
        StringBuffer result = new StringBuffer();
        if (level > 0) {
            for (int i = 0; i <= level; i++)
                result.append('\t');
            result.append("|-");
        }
        result.append("[" + toString() + "]\n");
        if (sons.size() > 0) {
            for (Node n : sons)
                if (n != null) {
                    result.append(n.getTree(level + 1));
                }
        }
        return result.toString();
    }

    public void check() throws Exception {
        for (Node n : sons)
            if (n != null)
                n.check();
    }


}
