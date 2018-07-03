package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.City;
import bean.House;
import bean.Zone;
import db.DBConnection;

public class HouseDao {
	static Connection con= DBConnection.getConnect();
	public List<House> getAllHouse() throws SQLException{
		List<House> houses = new ArrayList<House>();
		String sql = "select * from house where state=0";
		PreparedStatement ps = con.prepareStatement(sql);
		try (ResultSet rs = ps.executeQuery();) {
			while(rs.next()){

				House h = new House();
				h.setId(rs.getInt("hid"));
				h.setCity(new City(0,rs.getString("city")));
				h.setZone(new Zone(0,rs.getString("zone")));
				h.setArea(rs.getInt("area"));
				h.setDescription(rs.getString("description"));
				h.setMode(rs.getString("mode"));
				h.setPhone(rs.getString("phone"));
				h.setRent(rs.getFloat("rent"));
				h.setRoom(rs.getString("room"));
				houses.add(h);
			}
		}
		ps.close();
		return houses;
		
	}
	public boolean addHouse(House house) throws SQLException {
		String sql = "insert into house(city,zone,room,area,mode,rent,description,phone,state)"+ 
" values(?,?,?,?,?,?,?,?,0)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, house.getCity().getName());
		ps.setString(2, house.getZone().getName());
		ps.setString(3, house.getRoom());
		ps.setInt(4, house.getArea());
		ps.setString(5, house.getMode());
		ps.setFloat(6, house.getRent());
		ps.setString(7, house.getDescription());
		ps.setString(8, house.getPhone());
		boolean issuc = ps.executeUpdate()>0;
		ps.close();
		return issuc ;
	}

	public boolean collect(String uname, int id) throws SQLException {
		String sql = "insert into collect(uname,hid) values(?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, uname);
		ps.setInt(2, id);
		int row = ps.executeUpdate();
		ps.close();
		return row>0;
	}
}
