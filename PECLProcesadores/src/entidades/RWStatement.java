package entidades;

public class RWStatement extends Statement {

	private Variable v;

	public RWStatement(boolean read, String identifier) {
		super(read ? Statement.READ : Statement.WRITE);
		this.v = new Variable(identifier);
	}

	public Variable getVariable() {
		return this.v;
	}
	
	@Override
	public String toString(){
		if(this.getTipo() == Statement.READ)
			return "Leyendo " + this.v;
		else
			return "Escribiendo " + this.v;
	}
}
