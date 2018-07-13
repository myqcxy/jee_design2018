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
	public List<House> getAllHouse(String uname) throws SQLException{
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
	public List<House> getAllHouseByHot() throws SQLException{
		//����һ��List<House>����
		List<House> houses = new ArrayList<House>();
		//sql���
		String sql = "SELECT * FROM house "
				+ "where city in (select city from user where uname=?) "
				+ "and zone in (select zone from user where uname=?)"
				+ " and state=0";
		//ʹ��PreparedStatement��SQL���ִ��
		PreparedStatement ps = con.prepareStatement(sql);
		/*ps.setString(1, uname);
		ps.setString(2, uname);*/
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
	public List<House> getAllHouseByRecommend(String uname) throws SQLException{
		//����һ��List<House>����
				List<House> houses = new ArrayList<House>();
				//sql���
				String sql = "SELECT * FROM house "
						+ "where city in (select city from user where uname=?) "
						+ "and zone in (select zone from user where uname=?)"
						+ " and state=0";
				//ʹ��PreparedStatement��SQL���ִ��
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, uname);
				ps.setString(2, uname);
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
	public int addHouse(House house) throws SQLException {
		//sql���
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
			if(rs.next()){//�������houseû�л�ȡ
				id = rs.getInt("id");
			}
		}
		ps.close();
		
		return id ;
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
		String sql = "select * from collect where uname=?";
		PreparedStatement ps = con.prepareStatement(sql);
		List<House> houses = new ArrayList<House>();
		ps.setString(1, uname);
		try (ResultSet rs = ps.executeQuery();) {
			while(rs.next()){
				int hid = rs.getInt("hid");
				House h = getHouseById(hid);
				/*if(h.getState().equals("�����"))
					h.set*/
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
				String state="";
				if(0==s) state="������";
				else if(10==s) state="�����";
				else if(3==s) state="�����Ѿ�����";
				h.setState(state);
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
				h.setState(0==s?"������":"������");
				if(10==s){
					h.setState("�����");
				}
				if(101==s){
					h.setState("���ʧ��");
				}
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
				h.setState(0==s?"������":"������");
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
				h.setState(0==s?"������":"������");
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
				h.setState(0==s?"������":"������");
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
				h.setState(0==s?"������":"������");
				houses.add(h);
			}
		}
		ps.close();
		return houses;
	}

	
}
