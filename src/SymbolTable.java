import java.util.HashMap;

/**
 * Created by junquera on 5/12/17.
 */
public class SymbolTable {

    private HashMap<String, BASICSymbol> map;

    public SymbolTable(){
        this.map = new HashMap<>();
    }

    public void add(String name, BASICSymbol v){
        this.map.put(name, v);
    }

    public BASICSymbol get(String name){
        return this.map.get(name);
    }

    public void update(String name, BASICSymbol v){
        this.map.put(name, v);
    }

    public boolean exists(String name){
        return this.map.containsKey(name);
    }


}
