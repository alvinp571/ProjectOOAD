package models;

import mySQLConnector.Connect;

public class BorrowItem {
	private String borrow_id,book_id;
	
	private Connect connect = Connect.getInstance();
	
	public BorrowItem() {
	}

	public BorrowItem(String borrow_id, String book_id) {
		super();
		this.borrow_id = borrow_id;
		this.book_id = book_id;
	}

	public BorrowItem insert() {
		String query = String.format("INSERT INTO borrow_items VALUES ('%s','%s')", borrow_id,book_id);
		connect.executeUpdate(query);
		return this;
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
