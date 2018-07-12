package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.House;
import dao.HouseDao;

public class RentHouse extends ActionSupport {
	List<House> houses;// 房屋列表
	House house;//房屋
	String keyInfo;// 检索信息
	float higestPrice;
	List<House> allhouses;
	

	public List<House> getAllhouses() {
		return allhouses;
	}

	public void setAllhouses(List<House> allhouses) {
		this.allhouses = allhouses;
	}

	public float getHigestPrice() {
		return higestPrice;
	}

	public void setHigestPrice(float higestPrice) {
		this.higestPrice = higestPrice;
	}

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


	public String collect() throws SQLException {
		Map<String, Object> session = ActionContext.getContext().getSession();
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
		Map<String, Object> session = ActionContext.getContext().getSession();
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

	
	public String searchHouse() throws SQLException {
		houses = new HouseDao().getHouseBySearch(keyInfo);
		return SUCCESS;
	}
	public String seekHouse() throws SQLException {
		houses = new HouseDao().getHouseBySeek(house);
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
		Map session = ActionContext.getContext().getSession();
		String uname = (String) session.get("uname");//获取用户名
		houses = new HouseDao().getAllHouseByRecommend(uname);//根据用户推荐
		allhouses = new HouseDao().getAllHouse(uname);//获取所有房屋信息
		return SUCCESS;
	}

}
