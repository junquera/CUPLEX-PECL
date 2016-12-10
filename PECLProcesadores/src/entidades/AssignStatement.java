package entidades;

public class AssignStatement extends Statement {

	private Variable v;

	public AssignStatement(Variable v) {
		super(Statement.ASSIGN);
		this.v = v;
	}

	public Variable getV() {
		return v;
	}

	public void setV(Variable v) {
		this.v = v;
	}
}
