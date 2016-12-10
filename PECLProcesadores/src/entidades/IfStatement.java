package entidades;
import java.util.ArrayList;

public class IfStatement extends Statement {

	private ExprBooleana condition;
	private ArrayList<Statement> elseStatements;
	
	public IfStatement(ExprBooleana condition) {
		super(Statement.IF);
		this.condition = condition;
		this.elseStatements = new ArrayList<Statement>();
		// TODO Auto-generated constructor stub
	}

	public ExprBooleana getCondition() {
		return condition;
	}

	public void setCondition(ExprBooleana condition) {
		this.condition = condition;
	}

	public ArrayList<Statement> getElseStatements() {
		return elseStatements;
	}
	
	public void addElseStatements(Statement s){
		this.elseStatements.add(s);
	}
	
}
