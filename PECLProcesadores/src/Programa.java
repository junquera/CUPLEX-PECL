import java.util.ArrayList;

public class Programa {
	
	abstract class Variable {
		
		class Bool extends Variable {
			
			private boolean value;
			
			public Bool(String name, boolean value){
				super(name);				System.out.println(name + ":" + value);

				this.value = value;
			}

			public boolean isValue() {
				return value;
			}

			public void setValue(boolean value) {
				this.value = value;
			}
		}
		
		class Int extends Variable {
			private int value;
			
			public Int(String name, int value){
				super(name);				System.out.println(name + ":" + value);

				this.value = value;
			}

			public int getValue() {
				return value;
			}

			public void setValue(int value) {
				this.value = value;
			}
		}
		
		private String name;
		
		public Variable(String name){
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}	
		
		
	}
	
	
	private String name;
	private ArrayList<Variable> vars;
	
	
	public Programa(String name){
		System.out.println(name);
		this.name = name;
	}
	
	public void addVar(Variable v){
		this.vars.add(v);
	}

}
