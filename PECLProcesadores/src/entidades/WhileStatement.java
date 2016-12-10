package entidades;

public class WhileStatement extends Statement {

	private ExprBooleana condition;

	public WhileStatement(ExprBooleana condition){
		super(Statement.WHILE);
		this.condition = condition;
	}
	
}
