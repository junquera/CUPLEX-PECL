public class Programa {

	private String name;

	public Programa(String name) {
		System.out.println(name);
		this.name = name;
	}

	public String toString() {
		return "Programa: " + this.name;
	}

}
