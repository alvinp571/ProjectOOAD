package models;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import helper.Session;
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
	
	public List<Borrow> getAcceptStatus(Date date,boolean isOnlyCurrentMember){
		String query = "";
		
		if(date==null) {
			if(!isOnlyCurrentMember) {
				query = String.format("SELECT id,member_id,status FROM borrows WHERE status = 'Accepted'");
			}else {
				query = String.format("SELECT id,member_id,status FROM borrows WHERE status = 'Accepted' AND member_id = '%s'",Session.user.getId());
			}
		}else {
			int month = date.getMonth() + 1;
			int year = date.getYear()+1900;
			System.out.println(month+","+year);
			if(!isOnlyCurrentMember) {
				query = String.format("SELECT id,member_id,status FROM borrows WHERE status = 'Accepted' AND month(borrow_timestamp) = %d AND year(borrow_timestamp) = %d",month,year);
			}else {
				query = String.format("SELECT id,member_id,status FROM borrows WHERE status = 'Accepted' AND member_id = '%s' AND month(borrow_timestamp) = %d AND year(borrow_timestamp) = %d",Session.user.getId(),month,year);
			}
		}
		
		ResultSet rs = connect.executeQuery(query);
		List<Borrow> bookBorrows = new ArrayList<Borrow>();
		try {
			while(rs.next()) {
				bookBorrows.add(new Borrow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookBorrows;
	}
	
	public List<Borrow> getPendingStatus(boolean isOnlyCurrentMember){
		String query = "";
		if(!isOnlyCurrentMember) {
			query = String.format("SELECT id,member_id,status FROM borrows WHERE status = 'Pending'",status);
		}else {
			query = String.format("SELECT id,member_id,status FROM borrows WHERE status = 'Pending' AND member_id = '%s'",Session.user.getId());
		}
		ResultSet rs = connect.executeQuery(query);
		List<Borrow> bookBorrows = new ArrayList<Borrow>();
		try {
			while(rs.next()) {
				bookBorrows.add(new Borrow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookBorrows;
	}
	
	public boolean isBookStillBorrowing(String userId,String bookId) {
		String query = String.format("SELECT * FROM borrows b JOIN borrow_items bi ON b.id = bi.borrow_id "
				+ "WHERE b.member_id = '%s' AND bi.return_timestamp IS NULL AND bi.book_id = '%s'",userId,bookId);
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
	
	public int getCountBookStillBorrowing(String userId) {
		String query = String.format("SELECT * FROM borrows b JOIN borrow_items bi ON b.id = bi.borrow_id "
				+ "WHERE b.member_id = '%s' AND bi.return_timestamp IS NOT NULL",userId);
		ResultSet rs = connect.executeQuery(query);
		int countBook = 0;
		try {
			while(rs.next()) {
				countBook++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return countBook;
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
	
	public String getBorrowTimeStamp(String id) {
		String query = String.format("SELECT borrow_timestamp FROM borrows WHERE id = '%s'",id);
		ResultSet rs = connect.executeQuery(query);
		
		String borrowTime = "";
		try {
			rs.next();
			borrowTime = rs.getString("borrow_timestamp");
		} catch (SQLException e) {
			e.printStackTrace();
		};

		return borrowTime;
	}
	
}
