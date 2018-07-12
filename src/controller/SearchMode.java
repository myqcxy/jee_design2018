package controller;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import bean.House;
import dao.HouseDao;

public class SearchMode extends ActionSupport {
	String mode;
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public List<House> getHouses() {
		return houses;
	}
	public void setHouses(List<House> houses) {
		this.houses = houses;
	}
	List<House> houses;// ·¿ÎÝÁÐ±í
	@Override
	public String execute() throws Exception {
		houses = new HouseDao().getHouseByMode(mode);
		return SUCCESS;
	}
}
