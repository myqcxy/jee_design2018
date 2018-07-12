package controller;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import bean.House;
import dao.HouseDao;

public class SearchRoom extends ActionSupport {
	String room;
	List<House> houses;// ·¿ÎÝÁÐ±í
	public String getRoom() {
		return room;
	}
	@Override
	public String execute() throws Exception {
		houses = new HouseDao().getHouseByRoom(room);
		return SUCCESS;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public List<House> getHouses() {
		return houses;
	}
	public void setHouses(List<House> houses) {
		this.houses = houses;
	}
}
