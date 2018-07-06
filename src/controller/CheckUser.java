package controller;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Admin;
import bean.User;
import dao.*;

public class CheckUser extends ActionSupport {
	User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String execute() throws Exception {
		UserDao ud = new UserDaoImp(); 
		
			if(ud.isLagel(user)){
				 Map session=ActionContext.getContext().getSession();
				   session.put("uname", user.getUname());
				   return SUCCESS;
			}
			return "fail";

	}
	public String addUser() throws Exception{
		UserDao ud = new UserDaoImp();
		if(ud.addUser(user)) return SUCCESS;
		return "addError";
		
	}
	@Override
	public void validate() {
		if(this.user.getUname().isEmpty()){
			addFieldError("user.uname","没有输入用户名");	
		}
		if(user.getUpass().isEmpty()){
			addFieldError("user.upass","没有输入密码");
		}	
	}
	
}
