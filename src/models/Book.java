package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import mySQLConnector.Connect;

public class Book {
	private String id,genre_id,title,isbn;
	private Integer quantity;
	
	private Connect connect = Connect.getInstance();
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(String genre_id, String title, String isbn, Integer quantity) {
		super();
		this.id = UUID.randomUUID().toString();
		this.genre_id = genre_id;
		this.title = title;
		this.isbn = isbn;
		this.quantity = quantity;
	}
	
	public Book insert() {
		String query = String.format("INSERT INTO books VALUES ('%s','%s','%s','%s',%d)", id,genre_id,title,isbn,quantity);
		connect.executeUpdate(query);
		return this;
	}
	
	public Book update() {
		String query = String.format("UPDATE books SET genre_id='%s',title = '%s',isbn = '%s',quantity= %d WHERE id = '%s'",genre_id,title,isbn,quantity,id);
		int result = connect.executeUpdate(query);
		return (result==1)?this:null;
	}
	
	public boolean delete() {
		String query = String.format("DELETE FROM courses WHERE id = '%s'",id);
		int result = connect.executeUpdate(query);
		return (result==1);
	}

	public List<Book> getBookByQuantityMoreThanZero(){
		String query = String.format("SELECT * FROM books WHERE quantity>0");
		ResultSet rs = connect.executeQuery(query);
		List<Book> theBooks = new ArrayList<Book>();
		try {
			while(rs.next()) {
				theBooks.add(new Book(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theBooks;
	}
	
	public Book find(String id) {
		String query = String.format("SELECT * FROM books WHERE id = '%s'",id);
		ResultSet rs = connect.executeQuery(query);
		try {
			if(rs.next()) {
				return new Book(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Book getByIsbn(String isbn) {
		String query = String.format("SELECT * FROM books WHERE isbn = '%s'",isbn);
		ResultSet rs = connect.executeQuery(query);
		try {
			if(rs.next()) {
				return new Book(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Book(ResultSet rs) {
		try {
			this.id = rs.getString(1);
			this.genre_id = rs.getString(2);
			this.title = rs.getString(3);
			this.isbn = rs.getString(4);
			this.quantity = rs.getInt(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Book> all(){
		String query = String.format("SELECT * FROM books");
		ResultSet rs = connect.executeQuery(query);
		List<Book> theBooks = new ArrayList<Book>();
		try {
			while(rs.next()) {
				theBooks.add(new Book(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theBooks;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(String genre_id) {
		this.genre_id = genre_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
