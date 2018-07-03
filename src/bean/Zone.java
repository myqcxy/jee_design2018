package bean;

public class Zone {
	int id;
	String name;
	public Zone(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public Zone() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
