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
	
	public BaseInternalView showManageBookForm() {
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
		/**
		 * This method is used to decide whether
		 * the book from inputs is a new book or not
		 * @param self-explanatory
		 * @output Book insertedOrupdatedBook OR null (if inputs not valid)
		 */
		if(validate(inputs)) {
			String isbn = inputs.get("isbn");
			
			if(book.getByIsbn(isbn) == null) {
				//book does not exist -> insert new book
				book = insert(inputs);
			} else {
				//book exist -> update that book
				book = update(inputs);
			}
			return book;
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
	
	public void addQty(Book book, int qty) {
		/*
		 * This method is a helper for other methods, used for add quantity of a book shortly
		 * @param Book theBook, int addQty
		 * @return null
		 */
		book.setQuantity(book.getQuantity()+qty);
		return;
	}
	
	public Book update(HashMap<String,String> inputs) {
		/*
		 * This method updates Book, bridges Book model and view
		 * @param self-explanatory
		 * @output Book updatedBook
		 */
		Book b = book.getByIsbn(inputs.get("isbn"));
		Integer q = Integer.parseInt(inputs.get("quantity"));
		if(Session.showRoleName().equals("Purchasing")) {
			Genre g = new Genre().getByType(inputs.get("genre"));
			b.setGenre_id(g.getId());		
		}
		addQty(b, q);
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
		if (!isStringAllNumber(quantity)) {
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
