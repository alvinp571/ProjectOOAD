package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mySQLConnector.Connect;

public class BorrowItem {
	private String borrow_id,book_id;
	
	private Connect connect = Connect.getInstance();
	
	public BorrowItem() {}

	public BorrowItem(String borrow_id, String book_id) {
		super();
		this.borrow_id = borrow_id;
		this.book_id = book_id;
	}

	public BorrowItem insert() {
		String query = String.format("INSERT INTO borrow_items(borrow_id,book_id) VALUES ('%s','%s')", borrow_id,book_id);
		connect.executeUpdate(query);
		return this;
	}
	
	public BorrowItem update() {
		String query = String.format("UPDATE borrow_items SET return_timestamp = NOW() WHERE id = '%s'",borrow_id);
		connect.executeUpdate(query);
		return this;
	}
	
	public List<BorrowItem> getBookItem(String id){
		String query = String.format("SELECT borrow_id,book_id FROM borrow_items WHERE borrow_id = '%s'",id);
		ResultSet rs = connect.executeQuery(query);
		List<BorrowItem> theBorrowItems = new ArrayList<BorrowItem>();
		try {
			while(rs.next()) {
				theBorrowItems.add(new BorrowItem(rs.getString(1),rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return theBorrowItems;
	}
	
	public boolean isBookAlreadyReturn(String id,String BookId) {
		String query = String.format("SELECT return_timestamp FROM borrow_items WHERE borrow_id = '%s' AND book_id = '%s'",borrow_id,book_id);
		ResultSet rs = connect.executeQuery(query);
		try {
			if(rs.getDate("return_timestamp")==null) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public String getBorrow_id() {
		return borrow_id;
	}

	public void setBorrow_id(String borrow_id) {
		this.borrow_id = borrow_id;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	
}
