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

	@Override
	public String toString() {
		if (this.v instanceof Bool)
			return "Setting " + v.getName() + " to " + ((Bool) v).getValue();
		else
			return "Setting " + v.getName() + " to " + ((Int) v).getValue();
	}
}
