package dao;

import bean.Admin;
import bean.Place;

public interface AdminDao {
	/**
	 * */
	public void addPlace(Place p)throws Exception;//添加地点
	public void editPlace(Place p)throws Exception;//修改地点
	public boolean isLagel(Admin admin)throws Exception;//验证登录是否合法
}
