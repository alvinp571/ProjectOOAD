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

	public Book(String genre_type, String title, String isbn, Integer quantity) {
		super();
		this.id = UUID.randomUUID().toString();
		Genre genre = new Genre();
		genre = genre.getByType(genre_type);
		this.genre_id = genre.getId();
		this.title = title;
		this.isbn = isbn;
		this.quantity = quantity;
	}
	
	public Book insert() {
		String query = String.format("INSERT INTO books VALUES ('%s','%s','%s','%s',%d)", id,genre_id,title,isbn,quantity);
		connect.executeUpdate(query);
		return this;
	}

	public List<Book> all(){
		String query = String.format("SELECT * FROM books");
		ResultSet rs = connect.executeQuery(query);
		List<Book> theBooks = new ArrayList<Book>();
		try {
			while(rs.next()) {
				Book book = new Book();
				book.id = rs.getString(1);
				book.genre_id = rs.getString(2);
				book.title = rs.getString(3);
				book.isbn = rs.getString(4);
				book.quantity = rs.getInt(5);
				theBooks.add(book);
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
