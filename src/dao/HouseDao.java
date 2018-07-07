package dao;

import java.sql.*;
import java.util.*;

import bean.*;
import db.DBConnection;
/**
 * �������һ��House�����ݿ������
 * ��һ��static��Connection���󣬱����˺����ݿ�����ӣ�����ÿ�ε��ã�������ÿ�ζ���ȡ
 */
/**
 * @author MYQ
 *
 */
public class HouseDao {
	public static Connection con= DBConnection.getConnect();
	/**
	 * ��ȡ���е�״̬Ϊ������ķ��ݵ���Ϣ
	 * ����ֵΪһ��List*/
	/**
	 * @return ���еķ�����Ϣ
	 * @throws SQLException
	 */
	public List<House> getAllHouse() throws SQLException{
		//����һ��List<House>����
		List<House> houses = new ArrayList<House>();
		//sql���
		String sql = "select * from house where state=0";
		//ʹ��PreparedStatement��SQL���ִ��
		PreparedStatement ps = con.prepareStatement(sql);
		//�����ȡ�õ��Ľ��
		try (ResultSet rs = ps.executeQuery();) {
			while(rs.next()){//�������houseû�л�ȡ

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
				h.setState(0==s?"������":"������");
				houses.add(h);
			}
		}
		ps.close();//�ر�ps
		return houses;
		
	}

	/**
	 * @param house �������House����
	 * @return ����ɹ�����true��ʧ�ܷ��� false
	 * @throws SQLException
	 */
	public boolean addHouse(House house) throws SQLException {
		//sql���
		String sql = "insert into house(city,zone,room,area,mode,rent,description,phone,state,uname)"+ 
" values(?,?,?,?,?,?,?,?,10,?)";
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
		boolean issuc = ps.executeUpdate()>0;//�洢ִ�н��
		ps.close();
		return issuc ;
	}

	/**
	 * @param uname �û���˭�ղ��˷��ݣ�
	 * @param id ���ݱ�ţ��ղ����ĸ�����
	 * @return ִ�гɹ�����true��ʧ�ܷ���false 
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
	 * @param uname �û���
	 * @return �ҵķ����б� List<House>
	 * @throws SQLException
	 */
	public List<House> getMyCollection(String uname) throws SQLException {
		String sql = "select * from collect where uname=? and state!=10";
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
	
	
	/**
	 * @param hid ����id
	 * @return ��Ӧid�ķ���
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
				h.setState(0==s?"������":"������");
			}
		}
		ps.close();
		return h;
	}

	/**
	 * @param id �������� hid
	 * @param uname �û���
	 * @return ִ�гɹ�����true ʧ�ܷ���false
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
	 * @param keyInfo ��������
	 * @return List<House> ����һ��House���б�List
	 * @throws SQLException
	 */
	public List<House> getHouseBySearch(String keyInfo) throws SQLException {
		keyInfo = "%" + keyInfo + "%";
		List<House> houses = new ArrayList<House>();
		String sql = "select * from house where (city like ? or zone like ?" + 
						"or mode like ? or description like ?) and state!=3 and state!=10 ";
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
				h.setPhotosUrl(rs.getString("photos"));
				int s = rs.getInt("state");
				h.setState(0==s?"������":"������");
				houses.add(h);
			}
		}
		ps.close();
		return houses;
	}
	/**
	 * @param uname �û���
	 * @return һ��House���б�
	 * @throws SQLException
	 */
	public List<House> getMyHouse(String uname) throws SQLException {
		List<House> houses = new ArrayList<House>();
		String sql = "select * from house where uname=? and state!=3  and state!=10";
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
				h.setState(0==s?"������":"������");
				houses.add(h);
			}
		}
		ps.close();
		return houses;
	}
	/**
	 * @param id house������
	 * @return ִ�гɹ�����true ʧ�ܷ���false
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
	 * @param house ���º��house
	 * @return ִ�гɹ�����true ʧ�ܷ���false
	 * @throws SQLException
	 */
	public boolean updateHouse(House house) throws SQLException {
		String sql = "update house set city=?,zone=?,room=?,"+""
				+ "area=?,mode=?,rent=?,description=?,phone=?,photos=? where hid=?";
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
				
				int s = rs.getInt("state");
				h.setState(10==s?"�����":"���ͨ��");
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

	
}
