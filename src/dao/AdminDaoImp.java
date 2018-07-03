package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.*;
import db.DBConnection;

public class AdminDaoImp implements AdminDao {
	static Connection con= DBConnection.getConnect();
	
	public Connection getCon() {
		return con;
	}

	public AdminDaoImp() {
	}



	@Override
	public boolean isLagel(Admin admin) throws Exception {
		String sql  = "select * from admin where aname=? and apass=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, admin.getAname());
		ps.setString(2,admin.getApass());
		boolean isLagel = false;
		try (ResultSet rs = ps.executeQuery();) {
			isLagel = rs.next();
		}
		return isLagel;
	}

	@Override
	public void addPlace(City city) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editPlace(Zone zone) throws Exception {
		// TODO Auto-generated method stub
		
	}


}
