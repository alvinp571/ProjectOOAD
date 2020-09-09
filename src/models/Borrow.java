package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import mySQLConnector.Connect;

public class Borrow {
	private String id,memberId,status;
	private Connect connect = Connect.getInstance();
	
	public Borrow() {}

	public Borrow(String memberId, String status) {
		super();
		this.id = UUID.randomUUID().toString();
		this.memberId = memberId;
		this.status = status;
	}

	public Borrow(ResultSet rs) {
		try {
			this.id = rs.getString(1);
			this.memberId = rs.getString(2);
			this.status = rs.getString(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Borrow insert() {
		String query = String.format("INSERT INTO borrows(id,member_id,status) VALUES ('%s','%s','%s')", id,memberId,status);
		connect.executeUpdate(query);
		return this;
	}
	
	public Borrow update() {
		String query = String.format("UPDATE borrows SET status = '%s' WHERE id = '%s'",status,id);
		int result = connect.executeUpdate(query);
		return (result==1)?this:null;
	}
	
	public Borrow find(String id) {
		String query = String.format("SELECT id,member_id,status FROM borrows WHERE id = '%s'",id);
		ResultSet rs = connect.executeQuery(query);
		try {
			if(rs.next()) {
				return new Borrow(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
