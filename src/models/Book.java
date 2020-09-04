package models;

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
