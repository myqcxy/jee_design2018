package bean;

import java.util.List;

/**
 * @author MYQ
 *
 */
public class House {
	private int id;//����id
	private City city;//�������ڳ���
	private Zone zone;//����������
	private String room;//��������
	private int area;//�������
	private String mode;//���ⷽʽ
	private float rent;//���
	private String description;//����
	private String phone;//��ϵ�绰
	private String photosUrl;//ͼƬ����
	private String state;
	private String owner;
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
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
	public void setRent(String rent) {
		try{
		this.rent = Float.parseFloat(rent);
		}catch(Exception nfe){
			this.rent=-1;
		}
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
	/*public void setArea(String area) {
		try{
			this.area = (int)Integer.parseInt(area);
			}catch(Exception nfe){
				this.area=-1;
			}
	}*/
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhotosUrl() {
		return photosUrl;
	}
	public void setPhotosUrl(String photosUrl) {
		this.photosUrl = photosUrl;
	}
	
}
