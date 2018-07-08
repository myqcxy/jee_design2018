package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.House;
import bean.RentalInfo;
import dao.HouseDao;
/*
 * 此类实现房屋的信息的增加、删除、修改以及得到需要编辑的房屋*/
public class UpdateHouse extends ActionSupport {
	
	private House house;//房屋
	
	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	/**
	 * 添加房屋，执行成功的话将信息写入数据库并且返回SUCCESS，否侧返回fail
	 */
	/**
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String addHouse() throws SQLException, IOException {
		//得到在session中存储的用户名的值
		Map session = ActionContext.getContext().getSession();
		String uname = (String) session.get("uname");
		house.setOwner(uname);
		if (new HouseDao().addHouse(house)) {//调用HouseDao中提供的访问数据库的方法
			return SUCCESS;
		}
		return "fail";

	}
	/**
	 * 删除房屋，执行成功的话修改数据库并且返回SUCCESS，否侧返回fail
	 */
	/**
	 * @return
	 * @throws SQLException
	 */
	public String delMyHouse() throws SQLException {
		if (new HouseDao().delMyHouse(house.getId())) {
			return SUCCESS;
		}
		return "fail";
	}
	/**
	 * 更新房屋信息，执行成功的话修改数据库中对应的房屋信息并且返回SUCCESS，否侧返回fail
	 */
	/**
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String updateHouse() throws SQLException, IOException {
	
		//数据库操作
		if (new HouseDao().updateHouse(house)) {
			return SUCCESS;
		}
		return "fail";
	}
	/**
	 * 编辑房屋，得到需要编辑的房屋信息
	 */
	/**
	 * @return
	 * @throws SQLException
	 */
	public String editHouse() throws SQLException {
		//访问数据库得到指定的id所对应的房屋
		house = new HouseDao().getHouseById(house.getId());
		return SUCCESS;
	}

	
}
