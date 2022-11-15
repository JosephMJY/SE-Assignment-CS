package Function;
import java.sql.*;
 
public class DataCon {
	static Connection c;
	
	public static Connection getCon() throws Exception {
		if (c == null) {
			
			//com.mysql.cj.jdbc.Driver
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/testing",
	                "me", "TestinG.213");
		}
		return c;
	}
	
	public static void insertData(String sql) throws Exception {
		getCon().createStatement().executeUpdate(sql);
	}
	
	public static ResultSet searchData(String sql) throws Exception {
		ResultSet rSet = DataCon.getCon().createStatement().executeQuery(sql);
		return rSet;
	}

}