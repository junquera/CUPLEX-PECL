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
}
