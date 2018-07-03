package dao;

import bean.Admin;
import bean.*;

public interface AdminDao {
	/**
	 * */
	public void addPlace(City city)throws Exception;//添加地点
	public void editPlace(Zone zone)throws Exception;//修改地点
	public boolean isLagel(Admin admin)throws Exception;//验证登录是否合法
}
