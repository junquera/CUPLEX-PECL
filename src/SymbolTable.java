import java.util.HashMap;

/**
 * Created by junquera on 5/12/17.
 */
public class SymbolTable {

    private HashMap<String, Node> map;

    public SymbolTable() {
        this.map = new HashMap<>();
    }

    // TODO Diferenciar variable de función
    public void add(String name, Node v) throws Exception {
        Node l;
        if (!(v instanceof Node.Funcion)) {
            l = getLiteral(v);
            ((Node.Literal)l).limitSize(18);
        } else {
            l = v;
        }


        if (exists(name)) {
            update(name, l);
        }
        this.map.put(name, l);
    }

    public Node.Literal getLiteral(Node f) throws Exception {
        Node.Literal input;
        if (f.getType() == Node.Literal.NUMERIC) {
            Float auxValue = Float.parseFloat(f.getPrintableValue(this));
            if (auxValue.intValue() != auxValue.floatValue())
                input = new Node.Literal(auxValue, Node.Literal.NUMERIC);
            else
                input = new Node.Literal(new Integer(auxValue.intValue()), Node.Literal.NUMERIC);
        } else {
            input = new Node.Literal(f.getPrintableValue(this), Node.Literal.STRING);
        }
        return input;
    }

    public Node get(String name) throws Exception {
        if (!exists(name)) {
            throw new Exception("ERROR SEMÁNTICO: LA VARIABLE " + name + " NO HA SIDO DEFINIDA");
        }
        return this.map.get(name);
    }

    public void update(String name, Node v) throws Exception {
        if (!exists(name))
            throw new Exception("ERROR, LA VARIABLE NO EXISTE");

        if (v instanceof Node.Funcion)
            if (!(get(name) instanceof Node.Funcion))
                throw new Exception("ERROR: " + name + " is not a function");
            else if (get(name) instanceof Node.Funcion)
                throw new Exception("ERROR: " + name + " is a function");

        this.map.put(name, v);
    }

    public boolean exists(String name) {
        return this.map.containsKey(name);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (String key : map.keySet()) {
            try {
                Node n = get(key);
                String value = n.getPrintableValue(this);

                sb.append("" + key + "\t|\t" + n.getNodeType() + "\t\t|\t" + value + "\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
