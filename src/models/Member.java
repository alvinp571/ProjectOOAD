package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mySQLConnector.Connect;

public class Member {
	
	private String user_id,address,member_since;
	
	private Connect connect = Connect.getInstance();
	
	public Member() {}
	
	public List<Member> all(){
		String query = String.format("SELECT * FROM members");
		ResultSet rs = connect.executeQuery(query);
		List<Member> theMembers = new ArrayList<Member>();
		try {
			if(!rs.next()) {
				System.out.println("The members is still empty !");
			}
			else {
				do {
					Member member = new Member();
					member.user_id = rs.getString(1);
					member.address = rs.getString(2);
					member.member_since = rs.getString(3);
					theMembers.add(member);
				} while (rs.next());
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theMembers; 
	}
	
	public Member(String user_id, String address) {
		super();
		this.user_id = user_id;
		this.address = address;
	}

	public Member createMembership() {
		String query = String.format("INSERT INTO members(user_id,address) VALUE('%s','%s')",user_id,address);
		connect.executeUpdate(query);
		return this;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMember_since() {
		return member_since;
	}

	public void setMember_since(String member_since) {
		this.member_since = member_since;
	}
	
}
