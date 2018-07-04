package bean;

import java.util.List;

public class House {
	int id;//房屋id
	City city;//房屋所在城市
	Zone zone;//房屋所在区
	String room;//房屋厅室
	int area;//房屋面积
	String mode;//出租方式
	float rent;//租金
	String description;//描述
	String phone;//联系电话
	List<String> photoUrl;//图片链接
	String state;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone zone) {
		this.zone = zone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public float getRent() {
		return rent;
	}
	public void setRent(float rent) {
		this.rent = rent;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(List<String> photoUrl) {
		this.photoUrl = photoUrl;
	}
}
