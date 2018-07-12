package controller;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import bean.House;
import dao.HouseDao;

public class SearchPlace extends ActionSupport {
	String city;
	String zone;
	List<House> houses;// ·¿ÎÝÁÐ±í
	public List<House> getHouses() {
		return houses;
	}
	public void setHouses(List<House> houses) {
		this.houses = houses;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	@Override
	public String execute() throws Exception {
		if(zone==null) zone="";
		houses = new HouseDao().getHouseByPlace(city,zone);
		return SUCCESS;
	}
	
}
