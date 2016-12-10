package entidades;
import java.util.*;

public class Programa {

	private String name;
	private HashMap<String, Variable> variables;
	private ArrayList<Statement> statements;

	public Programa(String name) {
		this.name = name;
		this.variables = new HashMap<String, Variable>();
		this.statements = new ArrayList<Statement>();
	}
	
	public void setVariables(HashMap<String, Variable> variables){
		this.variables = variables;
	}
	
	public void addStatement(Statement s){
		this.statements.add(s);
	}
	
	public String toString() {
		String programa = "Programa: " + this.name + "\n";
		for(String s : variables.keySet()){
			Variable v = variables.get(s);
			String aux = "\tVariable: " + s + " := ";
			if(v.getType() == Variable.INTEGER)
				aux += ((Int) v).getValue();
			else if(v.getType() == Variable.BOOLEAN)
				aux += ((Bool) v).getValue();
			aux += "\n";
			programa += aux;
		}
		return programa;
	}

}
