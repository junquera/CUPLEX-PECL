package entidades;

public class Variable {

	public static final int INTEGER = 0;
	public static final int BOOLEAN = 1;
	public static final int PSEUDO = Integer.MAX_VALUE;

	private String name;
	private int type;

	public Variable(String name) {
		this.name = name;
		this.type = -1;
	}

	public Variable(String name, int type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeString() {
		switch (this.type) {
		case INTEGER:
			return "integer";
		case BOOLEAN:
			return "boolean";
		case PSEUDO:
			return "pseudo";
		default:
			return "none";
		}
	}

	public String toString() {
		return "Variable: " + this.name + ":" + getTypeString();
	}

}