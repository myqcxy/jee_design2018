package controller;

import java.sql.SQLException;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import bean.House;
import dao.HouseDao;

public class CheckHouse extends ActionSupport {
	List<House> houses;// 房屋列表
	House house;//房屋

	public List<House> getHouses() {
		return houses;
	}

	public void setHouses(List<House> houses) {
		this.houses = houses;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public String getCheckHouse() throws SQLException{
		houses = new HouseDao().getNocheck();
		return SUCCESS;
	}
	
	public String passCheckHouse() throws SQLException{
		if(new HouseDao().passCheckHouse(house.getId()))
		return SUCCESS;
		return "fail";
	}
	public String noPassCheckHouse() throws SQLException{
		if(new HouseDao().noPassCheckHouse(house.getId()))
		return SUCCESS;
		return "fail";
	}
}
