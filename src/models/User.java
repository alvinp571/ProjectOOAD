package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import mySQLConnector.Connect;

public class User {
	private String id;
	private String roleId;
	private String name;
	private String username;
	private String password;
	private String gender;
	
	private Connect connect = Connect.getInstance();
	
	public User() {}
	
	public User(ResultSet rs) {
		try {
			//bisa pakai index, bisa pake nama kolom
			//kalau index,dimulai dari 1
			this.id = rs.getString("id");
			this.roleId = rs.getString("role_id");
			this.name = rs.getString("name");
			this.username = rs.getString("username");
			this.password = rs.getString("password");	
			this.gender = rs.getString("gender");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public User(String name, String username, String password,String gender,String roles) {
		super();
		this.id = UUID.randomUUID().toString();
		Role role = new Role();
		role = role.getByName(roles);
		this.roleId = role.getId();
		this.name = name;
		this.username = username;
		this.password = password;
		this.gender = gender;
	}
	
	public User login(String username,String password) {	
		String query = String.format("SELECT * FROM  users WHERE username = '%s' AND password = sha1('%s')",username,password);
		ResultSet rs = connect.executeQuery(query);
		try {
			//success login min 1 row
			if(rs.next()) {
				User user = new User(rs);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User getbyUserName(String username) {
		String query = String.format("SELECT * FROM  users WHERE username = '%s' ",username);
		ResultSet rs = connect.executeQuery(query);
		try {
			if(rs.next()) {
				User user = new User(rs);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean checkUser(String username,String password) {
		String query = String.format("SELECT * FROM  users WHERE username = '%s' AND password = sha1('%s')",username,password);
		ResultSet rs = connect.executeQuery(query);
		try {
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public User insert() { 
		String query = String.format("INSERT INTO users VALUE('%s','%s','%s','%s',sha1('%s'),'%s')",id,roleId,name,username,password,gender);
		connect.executeUpdate(query);
		return this;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
