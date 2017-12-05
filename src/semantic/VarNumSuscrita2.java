package semantic;

/**
 * Created by junquera on 5/12/17.
 */
public class VarNumSuscrita2 extends VarNumSuscrita {

    private String name;
    private Number[][] value;

    public VarNumSuscrita2(String name, Number[][] value){
        super(name);
        this.value = value;
    }

    @Override
    public int getTipo() {
        return 3;
    }

    @Override
    public Object getValue() {
        return this.value;
    }
}
