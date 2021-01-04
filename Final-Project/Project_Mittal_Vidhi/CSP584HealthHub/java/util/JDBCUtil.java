package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthhub","root","1234");							
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
		System.out.println();
		return conn;
	}
	public static void close(Connection conn) {
		if(conn == null)return;
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
