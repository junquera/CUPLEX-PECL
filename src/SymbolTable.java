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
        if (exists(name)) {
            update(name, v);
        }
        this.map.put(name, v);
    }

    public Node get(String name) throws Exception {
        if (!exists(name)) {
            throw new Exception("ERROR SEMÁNTICO: LA VARIABLE " + name + " NO HA SIDO DEFINIDA");
        }
        return this.map.get(name);
    }

    public void update(String name, Node v) throws Exception {
        if (!exists(name)) {
            throw new Exception("ERROR, LA VARIABLE NO EXISTE");
        }
        this.map.put(name, v);
    }

    public boolean exists(String name) {
        return this.map.containsKey(name);
    }


}
