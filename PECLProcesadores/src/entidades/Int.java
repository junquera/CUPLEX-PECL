package entidades;

public class Int extends Variable {
	private int value;

	public Int(String name, int value) {
		super(name, Variable.INTEGER);
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public String toString(){
		return "Int: " + this.getName() + " = " + this.value;
	}
}
