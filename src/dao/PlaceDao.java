package dao;

import java.sql.*;
import java.util.*;

import bean.Place;
import db.DBConnection;

public class PlaceDao {
	static Connection con= DBConnection.getConnect();
	Map<String,List<String>> place = new HashMap<String,List<String>>();
	public Place getPlaceById(int id) throws SQLException{
		String sql = "select * from place where pid=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		Place p = new Place();
		try (ResultSet rs = ps.executeQuery();) {
			while(rs.next()){
				p.setPid(id);
				p.setCity(rs.getString("city"));
			//	p.setZone(rs.getString("zone"));
			}
		}
		return p;
	}
	public  Map<String,List<String>> getAllPlace() throws SQLException{
		String sql_getcity = "select distinct city from place";
		String sql_getzone = "select * from place where city=?";
		PreparedStatement pscity = con.prepareStatement(sql_getcity);
		PreparedStatement pszone = con.prepareStatement(sql_getzone);
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
