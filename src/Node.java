import java.util.ArrayList;
import java.util.List;

/**
 * Created by junquera on 6/12/17.
 */
public abstract class Node {

    public static class Variable extends Node {

        public static final int NUMERIC = 0;
        public static final int STRING = 1;

        private Object content;
        private int type;

        public Variable() {
        }

        public Variable(Object content) {
            this.content = content;
        }

        public Variable(Object content, int type) {
            this.content = content;
            this.type = type;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }


    public static class BinExpression extends Node {
        public BinExpression() {
        }
    }

    public static class DeclDim extends Node {
        public DeclDim() {
        }
    }

    public static class Funcion extends Node {
        private boolean created;
        private String value;

        public Funcion(String value) {
            this.value = value;
            this.created = false;
        }

        public Funcion(String value, boolean created){
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
        public Linea() {
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

    public void addSonNode(Node n) {
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

}
