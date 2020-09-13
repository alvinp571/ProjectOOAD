package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import mySQLConnector.Connect;

public class Book {
	
	//Book Attributes
	private String id,genre_id,title,isbn;
	private Integer quantity;
	
	//Get the MySQL database connection
	private Connect connect = Connect.getInstance();

	//Constructors
	public Book() {
		//empty default constructor
	}
	
	public Book(String genre_id, String title, String isbn, Integer quantity) {
		/**
		 * This constructor is used to create a new Book object using given parameters
		 * @param the parameters and the data type above are self-explanatory
		 * @return Book bookWithSpecifiedAttributes (the attributes are no longer nulls)
		 */
		super();
		this.id = UUID.randomUUID().toString(); //auto-generate unique id
		this.genre_id = genre_id;
		this.title = title;
		this.isbn = isbn;
		this.quantity = quantity;
	}
	
	public Book(ResultSet rs) {
		/**
		 * This constructor is used to create a new Book object using a result set
		 * This constructor is commonly used with SELECT queries to handle result set
		 * @param ResultSet bookFromSelectStatement
		 * @return Book bookFromDatabase
		 */
		try {
			/**
			 * Retrieve all attributes from the columns of the result set
			 * The column number is 1-based
			 * Column number sequence  must be the same with the database table
			 */
			this.id = rs.getString(1);
			this.genre_id = rs.getString(2);
			this.title = rs.getString(3);
			this.isbn = rs.getString(4);
			this.quantity = rs.getInt(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//CRUD Operations
	public Book insert() {
		/**
		 * This method is used to insert a Book object itself to the Database
		 * @param null
		 * @return Book insertedBook
		 */
		String query = String.format(
				"INSERT INTO books VALUES ('%s','%s','%s','%s',%d)",
				id, genre_id, title, isbn, quantity);
		connect.executeUpdate(query);
		return this;
	}
	
	public Book update() {
		/**
		 * This method is used to update a Book object itself to the Database
		 * @param null
		 * @return Book updatedBook OR null (if nothing's updated)
		 */
		String query = String.format(
				"UPDATE books SET genre_id='%s',title = '%s',isbn = '%s',quantity= %d WHERE id = '%s'",
				genre_id, title, isbn, quantity, id);
		
		int result = connect.executeUpdate(query); //the number of row(s) affected during update
		return (result == 1) ? this : null; //result != 1 if no row is affected (i.e. result = 0)
	}
	
	public boolean delete() {
		/**
		 * This method is used to delete a Book object itself from the Database
		 * @param null
		 * @return Boolean deleteStatus
		 */
		String query = String.format("DELETE FROM books WHERE id = '%s'",id);
		
		int result = connect.executeUpdate(query); //the number of row(s) affected during delete
		return (result == 1); //true if the delete is success (i.e. 1 row affected)
	}

	public List<Book> getBookByQuantityMoreThanZero(){
		/**
		 * The method name is already self-explanatory
		 * @param null
		 * @return List<Book> booksMoreThanZeroQuantity
		 */
		String query = String.format("SELECT * FROM books WHERE quantity > 0");
		ResultSet rs = connect.executeQuery(query); //books from select statement above
		
		//storing books from result set to a list
		List<Book> theBooks = new ArrayList<Book>();
		try {
			while(rs.next()) {
				//retrieve all books, while there is still next book from result set
				theBooks.add(new Book(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theBooks;
	}
	
	public Book find(String id) {
		/**
		 * This method is used to find a book by its id from the database
		 * @param String bookId
		 * @return Book bookWithRequestedId OR null
		 */
		String query = String.format("SELECT * FROM books WHERE id = '%s'",id);
		ResultSet rs = connect.executeQuery(query); //result from query
		try {
			if(rs.next()) {
				//book exists
				return new Book(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//book does not exist
		return null;
	}
	
	public Book getByIsbn(String isbn) {
		/**
		 * This method is used to find a book by its isbn from the database
		 * @param String bookISBN
		 * @return Book bookWithRequestedISBN or null
		 */
		String query = String.format("SELECT * FROM books WHERE isbn = '%s'",isbn);
		ResultSet rs = connect.executeQuery(query);
		try {
			if(rs.next()) {
				//book exists
				return new Book(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//book does not exist
		return null;
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
	
	

	// LIST OF SETTER AND GETTERS
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
