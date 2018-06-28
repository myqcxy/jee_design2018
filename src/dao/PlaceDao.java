package dao;

import java.sql.*;
import java.util.*;

import db.DBConnection;

public class PlaceDao {
	static Connection con= DBConnection.getConnect();
	Map<String,List<String>> place = new HashMap<String,List<String>>();
	public  Map<String,List<String>> getAllPlace() throws SQLException{
		String sql_getcity = "select distinct city from place";
		String sql_getzone = "select * from place where city=?";
		PreparedStatement pscity = con.prepareStatement(sql_getcity);
		PreparedStatement pszone = con.prepareStatement(sql_getzone);
		System.out.println("sdfsdfsdfsdfsdf\n\n\n");
		try (ResultSet rscity = pscity.executeQuery();) {
			while(rscity.next()){
				String city = rscity.getString("city");
				System.out.println(city);
				List zones = new ArrayList<String>();
				pszone.setString(1, city);
				ResultSet rszone = pszone.executeQuery();
				while(rszone.next()){
					
					String zone = rszone.getString("zone");
					System.out.println(zone);
					zones.add(zone);
				} 
				place.put(city, zones);
			}
		}
		return place;

		
	}
}
