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

public class RentHouse extends ActionSupport {
	List<House> houses;
	House house;
	String keyInfo;// 检索信息
	File [] upload;
	String [] uploadContenType;
	String [] uploadFileName;
	public File[] getUpload() {
		return upload;
	}
	public void setUpload(File[] upload) {
		this.upload = upload;
	}
	public String[] getUploadContenType() {
		return uploadContenType;
	}
	public void setUploadContenType(String[] uploadContenType) {
		this.uploadContenType = uploadContenType;
	}
	public String[] getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String[] uoloadFileName) {
		this.uploadFileName = uoloadFileName;
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

	public String addHouse() throws SQLException, IOException {
		Map session = ActionContext.getContext().getSession();
		String uname = (String) session.get("uname");
		house.setOwner(uname);
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
	public String updateHouse() throws SQLException, IOException{
		Map session = ActionContext.getContext().getSession();
		String uname = (String) session.get("uname");
		IOUtils io = new IOUtils("photos/"+uname);
		String urls = "";
		for(int i=0;i<uploadFileName.length;i++){
			urls += "photos/"+uname + "/" + uploadFileName[i];
			
		}
		 io.filesCopy(upload, uploadFileName);
		house.setPhotosUrl(urls);
		if(new HouseDao().updateHouse(house)){
			return SUCCESS;
		}
		return "fail";
	}
	public String editHouse() throws SQLException{
		house = new HouseDao().getHouseById(house.getId());
		return SUCCESS;
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
