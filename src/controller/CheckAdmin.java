package controller;

import com.opensymphony.xwork2.ActionSupport;

import bean.Admin;
import dao.*;

public class CheckAdmin extends ActionSupport {
	Admin admin;
	
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	@Override
	public String execute() throws Exception {
		AdminDao ad = new AdminDaoImp(); 
		if(ad.isLagel(admin)){
			return SUCCESS;
		}
		else return "fail";
	}
	@Override
	public void validate() {
		if(this.admin.getAname().isEmpty()){
			addFieldError("admin.aname","û�������û���");	
		}
		if(admin.getApass().isEmpty()){
			addFieldError("admin.apass","û����������");
		}		
		
	}
	
}
