package entidades;
import java.util.ArrayList;

public class Statement {
	public static final int IF = 0;
	public static final int READ = 1;
	public static final int WRITE = 2;
	public static final int WHILE = 3;
	public static final int ASSIGN = 4;

	private int tipo;
	private ArrayList<Statement> subStatements;
	
	public Statement(int tipo){
		this.tipo = tipo;
		this.subStatements = new ArrayList<Statement>();
	}
	
	public int getTipo() {
		return tipo;
	}

	public ArrayList<Statement> getSubStatements() {
		return subStatements;
	}

	public void addSubStatement(Statement s){
		this.subStatements.add(s);
	}
	
}
