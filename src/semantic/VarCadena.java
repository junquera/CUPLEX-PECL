package semantic;

/**
 * Created by junquera on 5/12/17.
 */
public class VarCadena implements Variable {

    private String name;
    private String value;

    public VarCadena(String name, String value){
        this.name = name;
        this.value = value;
    }

    @Override
    public int getTipo() {
        return 0;
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
