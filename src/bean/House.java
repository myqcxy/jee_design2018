package bean;

import java.util.List;

public class House {
	int id;//����id
	City city;//�������ڳ���
	Zone zone;//����������
	String room;//��������
	int area;//�������
	String mode;//���ⷽʽ
	float rent;//���
	String description;//����
	String phone;//��ϵ�绰
	List<String> photoUrl;//ͼƬ����
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
