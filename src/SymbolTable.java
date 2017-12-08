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
        if (!exists(name))
            throw new Exception("ERROR, LA VARIABLE NO EXISTE");

        if(v instanceof Node.Funcion)
            if(!(get(name) instanceof Node.Funcion))
                throw new Exception("ERROR: " + name + " is not a function");
        else
            if(get(name) instanceof Node.Funcion)
                throw new Exception("ERROR: " + name + " is a function");

        this.map.put(name, v);
    }

    public boolean exists(String name) {
        return this.map.containsKey(name);
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        for(String key: map.keySet()){
            try {
                Node n = get(key);
                sb.append("" + key + "\t|\t" + n.getNodeType() + "\t\t|\t" + n.toString() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
