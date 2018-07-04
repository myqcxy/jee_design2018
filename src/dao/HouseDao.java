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
	public List<House> getMyCollection(String uname) throws SQLException {
		String sql = "select * from collect where uname=?";
		PreparedStatement ps = con.prepareStatement(sql);
		List<House> houses = new ArrayList<House>();
		ps.setString(1, uname);
		try (ResultSet rs = ps.executeQuery();) {
			while(rs.next()){
				int hid = rs.getInt("hid");
				House h = getHouseById(hid);
				houses.add(h);
			}
		}
		return houses;
	}
	public House getHouseById(int hid) throws SQLException {
		String sql = "select * from house where hid=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, hid);
		House h = new House();
		try (ResultSet rs = ps.executeQuery();) {
			while(rs.next()){

				
				h.setId(rs.getInt("hid"));
				h.setCity(new City(0,rs.getString("city")));
				h.setZone(new Zone(0,rs.getString("zone")));
				h.setArea(rs.getInt("area"));
				h.setDescription(rs.getString("description"));
				h.setMode(rs.getString("mode"));
				h.setPhone(rs.getString("phone"));
				h.setRent(rs.getFloat("rent"));
				h.setRoom(rs.getString("room"));
			}
		}
		ps.close();
		return h;
	}
	public boolean rmCollection(int id, String uname) throws SQLException {
		String sql="delete from collect where uname=? and hid=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, uname);
		ps.setInt(2, id );
		int row = ps.executeUpdate();
		ps.close();

		
		return row>0;
	}
	public boolean rentHouse(String uname, int id) {
		
		return false;
	}
	public List<House> getHouseBySearch(String keyInfo) throws SQLException {
		keyInfo = "%" + keyInfo + "%";
		List<House> houses = new ArrayList<House>();
		String sql = "select * from house where (city like ? or zone like ?" + 
						"or mode like ? or description like ?) and state!=3";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, keyInfo);
		ps.setString(2, keyInfo);
		ps.setString(3, keyInfo);
		ps.setString(4, keyInfo);
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
	public List<House> getMyHouse(String uname) throws SQLException {
		List<House> houses = new ArrayList<House>();
		String sql = "select * from house where uname=? and state!=3";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, uname);
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
				int s = rs.getInt("state");
				h.setState(0==s?"´ý³ö×â":"³ö×âÖÐ");
				houses.add(h);
			}
		}
		ps.close();
		return houses;
	}
	public boolean delMyHouse(int id) throws SQLException {
		String sql = "UPDATE house SET state=3  where hid=?";
		System.out.println(id);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		int row = ps.executeUpdate();
		return row>0;
	}
	public boolean updateHouse(House house) throws SQLException {
		String sql = "update house set city=?,zone=?,room=?,"+""
				+ "area=?,mode=?,rent=?,description=?,phone=? where hid=?";
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, house.getCity().getName());
						ps.setString(2, house.getZone().getName());
						ps.setString(3, house.getRoom());
						ps.setInt(4, house.getArea());
						ps.setString(5, house.getMode());
						ps.setFloat(6, house.getRent());
						ps.setString(7, house.getDescription());
						ps.setString(8, house.getPhone());
						ps.setInt(9, house.getId());
						boolean issuc = ps.executeUpdate()>0;
						ps.close();
						return issuc ;
	}
}
