
class Bool extends Variable {

	private boolean value;

	public Bool(String name, boolean value) {
		super(name);
		System.out.println(name + ":" + value);

		this.value = value;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}
}
