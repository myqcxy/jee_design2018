package dao;

import bean.*;

public interface UserDao {
	public boolean isLagel(User user)throws Exception;//验证登录是否合法
	public boolean addUser(User user)throws Exception;//添加用户
	
}
