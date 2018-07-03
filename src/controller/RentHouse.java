package controller;

import java.sql.SQLException;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import bean.House;
import bean.RentalInfo;
import dao.HouseDao;

public class RentHouse extends ActionSupport {
	List<House> houses;
	House house;
	

	

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public List<House> getHouses() {
		return houses;
	}

	public void setHouses(List<House> houses) {
		this.houses = houses;
	}
	public String addHouse() throws SQLException{
		if(new HouseDao().addHouse(house)){
			return SUCCESS;
		}
		return "fail";
		
	}
	
	@Override
	public String execute() throws Exception {
		houses = new HouseDao().getAllHouse();
		return SUCCESS;
	}
	
}
