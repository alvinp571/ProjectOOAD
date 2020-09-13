package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mySQLConnector.Connect;

public class Role {

	private String id, name;
	private Connect connect = Connect.getInstance();

	
	public List<Role> all(){
		String query = String.format("SELECT * FROM roles");
		ResultSet rs = connect.executeQuery(query);
		List<Role> theRoles = new ArrayList<Role>();
		try {	
			while(rs.next()) {
				Role role = new Role();
				role.id = rs.getString(1);
				role.name = rs.getString(2);
				theRoles.add(role);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theRoles; 
	}
	
	public Role getByName(String name) {
		
		String query = String.format("SELECT * FROM roles WHERE name = '%s'",name);
		ResultSet rs = connect.executeQuery(query);

		try {
			if(rs.next()) {
				Role role = new Role();
				role.id = rs.getString("id");
				return role;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Role() {}

	public Role(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
