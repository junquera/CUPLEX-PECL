package semantic;

/**
 * Created by junquera on 5/12/17.
 */
public class VarNumSuscrita implements Variable {

    private String name;
    private Number[] value;

    public VarNumSuscrita(String name, Number[] value) {
        this.name = name;
        this.value = value;
    }

    public VarNumSuscrita(String name) {
        this.name = name;
    }

    @Override
    public int getTipo() {
        return 2;
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
