import semantic.Variable;

import java.util.HashMap;

/**
 * Created by junquera on 5/12/17.
 */
public class SymbolTable {

    private HashMap<String, Variable> map;

    public SymbolTable(){
        this.map = new HashMap<>();
    }

    public void add(String name, Variable v){
        this.map.put(name, v);
    }

    public Variable get(String name){
        return this.map.get(name);
    }

    public void update(String name, Variable v){
        this.map.put(name, v);
    }

    public boolean exists(String name){
        return this.map.containsKey(name);
    }


}
