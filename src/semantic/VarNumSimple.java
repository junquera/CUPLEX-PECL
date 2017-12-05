package semantic;

/**
 * Created by junquera on 5/12/17.
 */
public class VarNumSimple implements Variable {

    private String name;
    private Number value;

    public VarNumSimple(String name, Number value){
        this.name = name;
        this.value = value;
    }

    @Override
    public int getTipo() {
        return 1;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getValue() {
        return this.value;
    }
}
