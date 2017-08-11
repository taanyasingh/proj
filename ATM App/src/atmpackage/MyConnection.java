package atmpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection 
{
	static Connection con;

	
	static Connection connect()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tanya","root","tanyasingh");
			System.out.println("connected");
		} 
		catch (ClassNotFoundException e)
		{
			
			e.printStackTrace();
		} 
		catch (SQLException e)
		{

			e.printStackTrace();
		}
		return con;

	}
	

	public static void main(String[] args) 
	{
		connect();
	}

}
