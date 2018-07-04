package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.House;
import bean.RentalInfo;
import dao.HouseDao;

public class RentHouse extends ActionSupport {
	List<House> houses;
	House house;
	String keyInfo;// 检索信息

	public String getKeyInfo() {
		return keyInfo;
	}

	public void setKeyInfo(String keyInfo) {
		this.keyInfo = keyInfo;
	}

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

	public String addHouse() throws SQLException {
		if (new HouseDao().addHouse(house)) {
			return SUCCESS;
		}
		return "fail";

	}

	public String collect() throws SQLException {
		Map session = ActionContext.getContext().getSession();
		String uname = (String) session.get("uname");
		if (new HouseDao().collect(uname, house.getId())) {
			return SUCCESS;
		}
		return "fail";
	}

	/**
	 * 将房屋从我的收藏中移除
	 * 
	 * @throws SQLException
	 * 
	 */
	public String rmCollection() throws SQLException {
		Map session = ActionContext.getContext().getSession();
		String uname = (String) session.get("uname");

		if (new HouseDao().rmCollection(house.getId(), uname)) {
			return SUCCESS;

		}
		return "fail";
	}

	/**
	 * 租房
	 */
	public String rentHouse() {
		Map session = ActionContext.getContext().getSession();
		String uname = (String) session.get("uname");
		if (new HouseDao().rentHouse(uname, house.getId())) {
			return SUCCESS;
		}
		return "fail";
	}

	/**
	 * 我的收藏
	 * 
	 * @throws SQLException
	 */
	public String myCollection() throws SQLException {
		Map session = ActionContext.getContext().getSession();
		String uname = (String) session.get("uname");
		houses = new HouseDao().getMyCollection(uname);
		return SUCCESS;
	}

	public String delMyHouse() throws SQLException {

		if (new HouseDao().delMyHouse(house.getId())) {
			return SUCCESS;
		}
		return "fail";
	}

	public String searchHouse() throws SQLException {
		houses = new HouseDao().getHouseBySearch(keyInfo);
		return SUCCESS;
	}

	public String myHouse() throws SQLException {
		Map session = ActionContext.getContext().getSession();
		String uname = (String) session.get("uname");
		houses = new HouseDao().getMyHouse(uname);
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		houses = new HouseDao().getAllHouse();
		return SUCCESS;
	}

}
