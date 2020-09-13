package controllers;

import java.util.HashMap;

import java.util.List;
import java.util.Set;


import components.Message;
import helper.Session;
import models.Book;
import models.Genre;
import views.ManageBook;
import views.ViewBookForm;
import views.base.BaseInternalView;

public class BookHandler {
	/**
	 * As we are using MVC architecture, this BookHandler class will
	 * act as a bridge between the Book model and the views related to Book
	 */
	
	//book object to help operations
	private Book book = new Book();
	
	//connectors to views related to Book model
	public BaseInternalView showViewBookForm() {
		return new ViewBookForm();
	}
	
	public BaseInternalView showManageBook() {
		return new ManageBook();
	}
	
	//CRUD Operations
	public List<Book> getAll(){
		/**
		 * This method is used to retrieve all books from the Book model
		 * and pass them to Book views to be displayed
		 * @param null
		 * @return List<book> allBooks
		 */
		List<Book> theBooks = book.all(); //container for all books from the database
		
		if(theBooks.isEmpty()) {
			//no book at all
			Message.error("Book is empty !");
		}
		
		return theBooks;
	}
	
	public Book getById(String id) {
		//this method is self-explanatory
		return book.find(id);
	}
	
	public Book getByIsbn(String isbn) {
		//this method is self-explanatory
		return book.getByIsbn(isbn);
	}
	
	public List<Book> getBookByQuantityMoreThanZero() {
		//this method is self-explanatory
		return book.getBookByQuantityMoreThanZero();
	}
	
	public Book decide(HashMap<String,String>inputs) {
		//TODO documentation
		if(validate(inputs)) {
			Book b = new Book();
			if(restockBook(inputs.get("isbn"))==null) {
				b = insert(inputs);
			} else {
				b = update(inputs);
			}
			return b;
		}
		return null;
	}
	
	public Book insert(HashMap<String,String>inputs) {
		/**
		 * This method is used to insert the book from user's input to the database
		 * Precondition: the inputs must have been validated using validate() method
		 * Related method: validate()
		 * @param self-explanatory
		 * @return Book insertedBook
		 */
		Genre genre = new Genre();
		genre = genre.getByType(inputs.get("genre"));
		Integer q = Integer.parseInt(inputs.get("quantity"));
		return new Book(genre.getId(),inputs.get("title"),inputs.get("isbn"),q).insert();
	}
	
	public void updateQuantity(Book book, int q) {
		//TODO
		book.setQuantity(book.getQuantity()+q);
		return;
	}
	
	public Book update(HashMap<String,String> inputs) {
		//TODO
		Book b = book.getByIsbn(inputs.get("isbn"));
		Integer q = Integer.parseInt(inputs.get("quantity"));
		if(Session.showRoleName().equals("Purchasing")) {
			Genre g = new Genre().getByType(inputs.get("genre"));
			b.setGenre_id(g.getId());		
		}
		b.setQuantity(b.getQuantity() + q);
		b.setTitle(inputs.get("title"));
		b = b.update();
		return b;
	}
	
	public boolean delete(String id) {
		/**
		 * This method is used to delete a book by its id
		 * @param String bookId
		 * @return Boolean deleteStatus
		 */
		Book deletedBook = book.find(id);
		if(deletedBook != null) {
			return deletedBook.delete();
		}
		return false;
	}
	
	public Book restockBook(String isbn) {
		//TODO
		Book b = new Book().getByIsbn(isbn);
		if(b != null) {
			return b;
		}
		return null;
	}
	
	public Boolean areFieldsAllFilled(HashMap<String,String>inputs) {
		/**
		 * The name is self-explanatory
		 * @param self-explanatory
		 * @return Boolean validationStatus
		 */
		Set<String> keys = inputs.keySet(); //get all hashmap keys
		
		//check all key-value pairs
		for (String string : keys) {
			if(inputs.get(string).equals("")) {
				//value is empty
				Message.error("All fields must be filled!");
				return false;
			}
		}
		
		return true;
	}
	
	public Boolean isStringAllNumber(String s) {
		/**
		 * The name is self-explanatory
		 * @param self-explanatory
		 * @return Boolean validationStatus
		 */
		try {
			//all numbers -> can be parsed
			Long.parseLong(s);
			return true;
		} catch (NumberFormatException e) {
			//contains non-numeric -> can't be parsed
			return false;
		}
	}
	
	public Boolean isISBNvalid(String isbn) {
		/**
		 * This method is used to validate ISBN
		 * @param self-explanatory
		 * @return Boolean validationStatus
		 */
		if (!isStringAllNumber(isbn)) {
			Message.error("ISBN must be a number !");
			return false;
		}
		
		if (isbn.length() < 10 || isbn.length() > 13) {
			Message.error("ISBN length must be between 10 and 13");
			return false;
		}
		
		return true;
	}
	
	public Boolean isQuantityValid(String quantity) {
		/**
		 * This method is used to validate quantity
		 * @param self-explanatory
		 * @return Boolean validationStatus
		 */
		if (!isStringAllNumber(q)) {
			Message.error("Quantity must be a number !");
			return false;
		}
		
		int qty = Integer.parseInt(quantity);
		if (qty <= 0) {
			Message.error("Quantity must be more than 0");
			return false;
		}
		
		return true;
	}
	
	public Boolean validate(HashMap<String,String>inputs) {
		/**
		 * This method is used to validate all inputs from user
		 * @param self-explanatory
		 * @return Boolean validationStatus
		 */
		if (!areFieldsAllFilled(inputs)) return false;
		if (!isISBNvalid(inputs.get("isbn"))) return false;
		if (!isQuantityValid(inputs.get("quantity"))) return false;
		return true;
	}
	
}
