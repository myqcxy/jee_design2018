package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.User;
import dao.UserDao;
import db.DBConnection;

public class UserDaoImp implements UserDao {
	static Connection con= DBConnection.getConnect();
	
	public Connection getCon() {
		return con;
	}
	@Override
	public boolean isLagel(User user) throws Exception {
		String sql  = "select * from user where uname=? and upass=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, user.getUname());
		ps.setString(2,user.getUpass());
		boolean isLagel = false;
		try (ResultSet rs = ps.executeQuery();) {
			isLagel = rs.next();
		}
		return isLagel;
	
	}

	@Override
	public boolean addUser(User user) throws Exception {
		String sql  = "insert into user(uname,upass) values(?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, user.getUname());
		ps.setString(2,user.getUpass());
		int cl = ps.executeUpdate();
		return cl > 0;
	}

}
