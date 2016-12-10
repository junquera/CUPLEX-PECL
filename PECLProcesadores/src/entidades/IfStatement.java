package entidades;

import java.util.ArrayList;

public class IfStatement extends Statement {

	private Boolean condition;
	private ArrayList<Statement> elseStatements;

	public IfStatement(Boolean condition) {
		super(Statement.IF);
		this.condition = condition;
		this.elseStatements = new ArrayList<Statement>();
		// TODO Auto-generated constructor stub
	}

	public Boolean getCondition() {
		return condition;
	}

	public void setCondition(Boolean condition) {
		this.condition = condition;
	}

	public ArrayList<Statement> getElseStatements() {
		return elseStatements;
	}

	public void addElseStatements(Statement s) {
		this.elseStatements.add(s);
	}

	@Override
	public String toString() {
		String result = "Si se cumple " + condition + "...\n";
		for (Statement s : this.getSubStatements())
			result += s.toString() + "\n";
		result += "Si no...\n";
		for (Statement s : this.getElseStatements())
			result += s.toString() + "\n";
		return result;
	}

}
