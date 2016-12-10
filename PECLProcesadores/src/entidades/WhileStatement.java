package entidades;

public class WhileStatement extends Statement {

	private Boolean condition;

	public WhileStatement(Boolean condition) {
		super(Statement.WHILE);
		this.condition = condition;
	}

	@Override
	public String toString() {
		String result = "Mientras se cumpla " + this.condition + "...\n";
		for (Statement s : this.getSubStatements())
			result += s.toString() + "\n";
		return result;
	}

}
