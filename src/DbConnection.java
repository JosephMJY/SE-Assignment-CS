import java.sql.*;
import com.mysql.jdbc.*;
public class DbConnection {
	

		// TODO Auto-generated method stub
		Connection conn = null ;
	    public final static Connection database() throws Exception{
	    	try {
	    		Class.forName("com.mysql.cj.jdbc.Driver");
	    		String url = "jdbc:mysql://localhost:3306/visitor";
	    		String username = "root";
	    		String password = "";
	    	
	    		Connection conn = DriverManager.getConnection(url,username,password);
	    		System.out.println("Connected");

	    		
	    		return conn;
	    		
	    	}catch(Exception e) {System.out.println(e);}
			return null;
	    }

	}
	


