
class Int extends Variable {
	private int value;

	public Int(String name, int value) {
		super(name);
		System.out.println(name + ":" + value);

		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
