package controller;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserDao;
import dao.UserDaoImp;

public class Setlocation extends ActionSupport {
	String city;
	String zone;
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
		UserDao ud = new UserDaoImp();
		Map session=ActionContext.getContext().getSession();
		 String uname =  (String) session.get("uname");
		if(ud.setlocation(uname, city, zone)) return SUCCESS;
		return "addError";
	}
	
}
