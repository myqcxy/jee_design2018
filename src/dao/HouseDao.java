package dao;

import java.sql.*;
import java.util.*;

import bean.*;
import db.DBConnection;
/**
 * 这个类是一个House的数据库访问类
 * 有一个static的Connection对象，保存了和数据库的连接，方便每次调用，而不用每次都获取
 */
/**
 * @author MYQ
 *
 */
public class HouseDao {
	public static Connection con= DBConnection.getConnect();
	/**
	 * 获取所有的状态为待出租的房屋的信息
	 * 返回值为一个List*/
	/**
	 * @return 所有的房屋信息
	 * @throws SQLException
	 */
	public List<House> getAllHouse(String uname) throws SQLException{
		//创建一个List<House>对象
		List<House> houses = new ArrayList<House>();
		//sql语句
		String sql = "select * from house where state=0";
		//使用PreparedStatement将SQL语句执行
		PreparedStatement ps = con.prepareStatement(sql);
		//逐个获取得到的结果
		try (ResultSet rs = ps.executeQuery();) {
			while(rs.next()){//如果还有house没有获取

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
				h.setPhotosUrl(rs.getString("photos"));
				int s = rs.getInt("state");
				h.setState(0==s?"待出租":"出租中");
				houses.add(h);
			}
		}
		ps.close();//关闭ps
		return houses;
		
	}
	public List<House> getAllHouseByHot() throws SQLException{
		//创建一个List<House>对象
		List<House> houses = new ArrayList<House>();
		//sql语句
		String sql = "SELECT * FROM house "
				+ "where city in (select city from user where uname=?) "
				+ "and zone in (select zone from user where uname=?)"
				+ " and state=0";
		//使用PreparedStatement将SQL语句执行
		PreparedStatement ps = con.prepareStatement(sql);
		/*ps.setString(1, uname);
		ps.setString(2, uname);*/
		//逐个获取得到的结果
		try (ResultSet rs = ps.executeQuery();) {
			while(rs.next()){//如果还有house没有获取

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
				h.setPhotosUrl(rs.getString("photos"));
				int s = rs.getInt("state");
				h.setState(0==s?"待出租":"出租中");
				houses.add(h);
			}
		}
		ps.close();//关闭ps
		return houses;
	}
	public List<House> getAllHouseByRecommend(String uname) throws SQLException{
		//创建一个List<House>对象
				List<House> houses = new ArrayList<House>();
				//sql语句
				String sql = "SELECT * FROM house "
						+ "where city in (select city from user where uname=?) "
						+ "and zone in (select zone from user where uname=?)"
						+ " and state=0";
				//使用PreparedStatement将SQL语句执行
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, uname);
				ps.setString(2, uname);
				//逐个获取得到的结果
				try (ResultSet rs = ps.executeQuery();) {
					while(rs.next()){//如果还有house没有获取

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
						h.setPhotosUrl(rs.getString("photos"));
						int s = rs.getInt("state");
						h.setState(0==s?"待出租":"出租中");
						houses.add(h);
					}
				}
				ps.close();//关闭ps
				return houses;
	}
	/**
	 * @param house 待插入的House对象
	 * @return 插入成功返回true，失败返回 false
	 * @throws SQLException
	 */
	public int addHouse(House house) throws SQLException {
		//sql语句
		String sql = "insert into house(city,zone,room,area,mode,rent,description,phone,state,uname)"+ 
" values(?,?,?,?,?,?,?,?,10,?)";
		int id = -1;
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, house.getCity().getName());
		ps.setString(2, house.getZone().getName());
		ps.setString(3, house.getRoom());
		ps.setInt(4, house.getArea());
		ps.setString(5, house.getMode());
		ps.setFloat(6, house.getRent());
		ps.setString(7, house.getDescription());
		ps.setString(8, house.getPhone());
		ps.setString(9, house.getOwner());
		ps.executeUpdate();
		sql = "SELECT max(hid)id FROM HOUSE";
		ps = con.prepareStatement(sql);
		try (ResultSet rs = ps.executeQuery();) {
			if(rs.next()){//如果还有house没有获取
				id = rs.getInt("id");
			}
		}
		ps.close();
		
		return id ;
	}

	/**
	 * @param uname 用户，谁收藏了房屋；
	 * @param id 房屋编号，收藏了哪个房屋
	 * @return 执行成功返回true，失败返回false 
	 * @throws SQLException
	 */
	public boolean collect(String uname, int id) throws SQLException {
		String sql = "insert into collect(uname,hid) values(?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, uname);
		ps.setInt(2, id);
		
		int row = ps.executeUpdate();
		ps.close();
		return row>0;
	}

	/**
	 * @param uname 用户名
	 * @return 我的房屋列表 List<House>
	 * @throws SQLException
	 */
	public List<House> getMyCollection(String uname) throws SQLException {
		String sql = "select * from collect where uname=?";
		PreparedStatement ps = con.prepareStatement(sql);
		List<House> houses = new ArrayList<House>();
		ps.setString(1, uname);
		try (ResultSet rs = ps.executeQuery();) {
			while(rs.next()){
				int hid = rs.getInt("hid");
				House h = getHouseById(hid);
				/*if(h.getState().equals("待审核"))
					h.set*/
				houses.add(h);
			}
		}
		return houses;
	}
	
	
	/**
	 * @param hid 房屋id
	 * @return 对应id的房屋
	 * @throws SQLException
	 */
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
				h.setPhotosUrl(rs.getString("photos"));
				int s = rs.getInt("state");
				String state="";
				if(0==s) state="待出租";
				else if(10==s) state="待审核";
				else if(3==s) state="房屋已经过期";
				h.setState(state);
			}
		}
		ps.close();
		return h;
	}

	/**
	 * @param id 房屋主键 hid
	 * @param uname 用户名
	 * @return 执行成功返回true 失败返回false
	 * @throws SQLException
	 */
	public boolean rmCollection(int id, String uname) throws SQLException {
		String sql="delete from collect where uname=? and hid=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, uname);
		ps.setInt(2, id );
		int row = ps.executeUpdate();
		ps.close();

		return row>0;
	}
	/**
	 * @param uname 
	 * @param id
	 * @return
	 */
	public boolean rentHouse(String uname, int id) {
		
		return false;
	}
	/**
	 * @param keyInfo 检索条件
	 * @return List<House> 返回一个House的列表List
	 * @throws SQLException
	 */
	public List<House> getHouseBySearch(String keyInfo) throws SQLException {
		keyInfo = "%" + keyInfo + "%";
		List<House> houses = new ArrayList<House>();
		String sql = "select * from house where (city like ? or zone like ?" + 
						"or mode like ? or description like ?)"
						 + 
						" and state!=3 and state!=10 ";
		/*String sql = "select * from house where (city like ? or zone like ?" + 
				"or mode like ? or description like ?)"
				+ "and mode=? and city=? and zone=? and room=?" + 
				" and state!=3 and state!=10 ";*/
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, keyInfo);
		ps.setString(2, keyInfo);
		ps.setString(3, keyInfo);
		ps.setString(4, keyInfo);
		/*ps.setString(5, house.getMode());
		ps.setString(6, house.getCity().getName());
		ps.setString(7, house.getZone().getName());
		ps.setString(8, house.getRoom());*/
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
				h.setPhotosUrl(rs.getString("photos"));
				int s = rs.getInt("state");
				h.setState(0==s?"待出租":"出租中");
				houses.add(h);
			}
		}
		ps.close();
		return houses;
	}
	/**
	 * @param uname 用户名
	 * @return 一个House的列表
	 * @throws SQLException
	 */
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
				h.setPhotosUrl(rs.getString("photos"));
				
				int s = rs.getInt("state");
				h.setState(0==s?"待出租":"出租中");
				if(10==s){
					h.setState("待审核");
				}
				if(101==s){
					h.setState("审核失败");
				}
				houses.add(h);
			}
		}
		ps.close();
		return houses;
	}
	/**
	 * @param id house的主键
	 * @return 执行成功返回true 失败返回false
	 * @throws SQLException
	 */
	public boolean delMyHouse(int id) throws SQLException {
		String sql = "UPDATE house SET state=3  where hid=?";
		System.out.println(id);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		int row = ps.executeUpdate();
		return row>0;
	}
	/**
	 * @param house 更新后的house
	 * @return 执行成功返回true 失败返回false
	 * @throws SQLException
	 */
	public boolean updateHouse(House house) throws SQLException {
		String sql = "update house set city=?,zone=?,room=?,"+""
				+ "area=?,mode=?,rent=?,description=?,phone=?,photos=?,state=10 where hid=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, house.getCity().getName());
		ps.setString(2, house.getZone().getName());
		ps.setString(3, house.getRoom());
		ps.setInt(4, house.getArea());
		ps.setString(5, house.getMode());
		ps.setFloat(6, house.getRent());
		ps.setString(7, house.getDescription());
		ps.setString(8, house.getPhone());
		ps.setString(9, house.getPhotosUrl());
		
		ps.setInt(10, house.getId());
		
		boolean issuc = ps.executeUpdate()>0;
		ps.close();
		return issuc ;
	}

	public List<House> getNocheck() throws SQLException {
		List<House> houses = new ArrayList<House>();
		String sql = "select * from house where state=10";
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
				h.setPhotosUrl(rs.getString("photos"));
				h.setOwner(rs.getString("uname"));
				System.out.print(h.getOwner());
				int s = rs.getInt("state");
				h.setState(10==s?"待审核":"审核通过");
				houses.add(h);
			}
		}
		ps.close();
		return houses;
	}

	public boolean passCheckHouse(int hid) throws SQLException {
	
		String sql = "UPDATE HOUSE SET state=0 where hid=? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, hid);
		int row = ps.executeUpdate();
		return row>0;
	}

	public boolean noPassCheckHouse(int id) throws SQLException {

		String sql = "UPDATE HOUSE SET state=101 where hid=? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		int row = ps.executeUpdate();
		return row>0;
	}

	public String getPhotoByHid(int id) throws SQLException {
		String sql = "SELECT photos FROM house where hid=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		try (ResultSet rs = ps.executeQuery();) {
			if(rs.next()){
				return rs.getString("photos");
			}
		}
		ps.close();
		return "";
	}
	public List<House> getHouseBySeek(House house) throws SQLException {
		List<House> houses = new ArrayList<House>();
	
		String sql = "select * from house where "
				+ " mode=? and city=? and zone=? and room=?" + 
				" and state=0 ";
		PreparedStatement ps = con.prepareStatement(sql);
	
		ps.setString(1, house.getMode());
		ps.setString(2, house.getCity().getName());
		ps.setString(3, house.getZone().getName());
		ps.setString(4, house.getRoom());
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
				h.setPhotosUrl(rs.getString("photos"));
				int s = rs.getInt("state");
				h.setState(0==s?"待出租":"出租中");
				houses.add(h);
			}
		}
		ps.close();
		return houses;
	}
	public List<House> getHouseByPlace(String city, String zone) throws SQLException {
		zone = "%" + zone + "%";
		String sql = "select * from house where city=? and zone like ? and state=0";
		List<House> houses = new ArrayList<House>();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, city);
		ps.setString(2,zone);
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
				h.setPhotosUrl(rs.getString("photos"));
				int s = rs.getInt("state");
				h.setState(0==s?"待出租":"出租中");
				houses.add(h);
			}
		}
		ps.close();
		return houses;
	}
	public List<House> getHouseByRoom(String room) throws SQLException {
		String sql = "select * from house where room=? and state=0";
		List<House> houses = new ArrayList<House>();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, room);
	
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
				h.setPhotosUrl(rs.getString("photos"));
				int s = rs.getInt("state");
				h.setState(0==s?"待出租":"出租中");
				houses.add(h);
			}
		}
		ps.close();
		return houses;
	}
	public List<House> getHouseByMode(String mode) throws SQLException {
		String sql = "select * from house where mode=? and state=0";
		List<House> houses = new ArrayList<House>();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, mode);
	
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
				h.setPhotosUrl(rs.getString("photos"));
				int s = rs.getInt("state");
				h.setState(0==s?"待出租":"出租中");
				houses.add(h);
			}
		}
		ps.close();
		return houses;
	}

	
}
