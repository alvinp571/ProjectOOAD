package mySQLConnector;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	
	private String username = "root";
	private String password = "";
	private String database= "sweebookdao";
	private String host = "localhost:3306";
	private String connection = String.format("jdbc:mysql://%s/%s",host,database);
	
	//java.sql
	private java.sql.Connection con;
	private Statement st;
	
	private static Connect instance; //Singleton Design Pattern
	
	//public
	public Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(connection, username, password);
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static Connect getInstance() {
		if(instance==null) {
			instance = new Connect();
		}
		return instance;
	}
	
	public ResultSet executeQuery(String query) {
		try {
			return st.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int executeUpdate(String query) {
		try {
			return st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
