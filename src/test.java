import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import db.DBConnection;

public class test {

	public static void main(String[] args) throws SQLException {
		Connection con= DBConnection.getConnect();
		Map<String,List<String>> place = new HashMap<String,List<String>>();
		
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
			/*Set key = place.keySet();
			for(key.isEmpty()){
				System.out.println();
			}*/
			
		}
	}

}
