package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnect() {
		String driverName = "org.hsqldb.jdbcDriver"; // ����JDBC����
		String dbURL = "jdbc:hsqldb:hsql://localhost";
		String userName = "sa"; // Ĭ���û���
		String userPwd = "";
		try {
			Class.forName(driverName);
			return DriverManager.getConnection(dbURL, userName, userPwd);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void colse(Connection conn) {
		
			try {
				if(conn!=null)
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
