package openjFX.Login_Register_FX;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.*;

public class database {
	private static String DB_Driver = "com.mysql.cj.jdbc.Driver";
	private static String DB_URL = "jdbc:mysql://localhost:3306/mydb";
	private static String DB_User = "root";
	private static String DB_Password = "Password";
	
	private static Connection conn = null;
	static Logger logger = Logger.getLogger(database.class.getName());
	
	public static Connection DbConnected() {
		try {
			Class.forName(DB_Driver);
		}catch (ClassNotFoundException e) {
			logger.log(Level.SEVERE,e.getMessage());
		}
		try {
			conn = DriverManager.getConnection(DB_URL,DB_User,DB_Password);
		} catch (SQLException e) {
			logger.log(Level.SEVERE,e.getMessage());
		}
		return conn;
	}
}
