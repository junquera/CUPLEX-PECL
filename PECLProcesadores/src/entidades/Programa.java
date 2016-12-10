package entidades;
import java.util.*;

public class Programa {

	private String name;
	private HashMap<String, Variable> variables;
	private ArrayList<Statement> statements;

	public Programa(String name) {
		this.name = name;
		this.variables = new HashMap<String, Variable>();
		this.variables.put(name, new Pseudo(name));
		this.statements = new ArrayList<Statement>();
	}
	
	public void setVariables(HashMap<String, Variable> variables){
		this.variables.putAll(variables);
	}
	
	
	public void setVarValue(Variable v) throws SemanticException{
		Variable tmp = variables.get(v.getName());
		if(tmp == null)
			throw new SemanticException("La variable que intenta asignar no existe: " + v.toString());
		
		if(tmp.getType() != v.getType())
			throw new SemanticException("Error de tipos: " + v.toString() + "\nLa variable " + tmp.getName() + " es de tipo " + tmp.getTypeString());
		
		this.variables.put(v.getName(), v);
	}
	
	public void addStatement(Statement s){
		this.statements.add(s);
	}
	
	public String toString() {
		String programa = "Programa: " + this.name + "\n";
		programa += "Variables:\n";
		for(String s : variables.keySet()){
			Variable v = variables.get(s);
			programa += "\t" + v.getName() + " is type " + v.getTypeString() + "\n";
		}
		programa += "Código:\n";
		for(Statement s: statements)
			programa += "\t" + s.toString() + "\n";
		
		return programa;
	}

}
