package controllers;

import java.util.HashMap;

import java.util.List;
import java.util.Set;


import components.Message;
import models.Book;
import models.Genre;
import views.ViewBookForm;
import views.base.BaseInternalView;

public class BookHandler {
	
	private Book book = new Book();
	
	public BaseInternalView showViewBookForm() {
		return new ViewBookForm();
	}
	
	public List<Book> getAll(){
		Book book = new Book();
		List<Book> theBooks = book.all();
		if(theBooks.isEmpty()) {
			Message.error("Book is empty !");
		}
		return theBooks;
	}
	
	public Book getById(String id) {
		return book.find(id);
	}
	
	public Book getByIsbn(String isbn) {
		return book.getByIsbn(isbn);
	}
	
	public List<Book> getBookByQuantityMoreThanZero() {
		return book.getBookByQuantityMoreThanZero();
	}
	
	public Book decide(HashMap<String,String>inputs) {
		if(validate(inputs)) {
			Book b = new Book();
			if(getByIsbn(inputs.get("isbn"))!= null) {
				b = insert(inputs);
			}else {
				b = update(inputs);
			}
			return b;
		}
		return null;
	}
	
	public Book insert(HashMap<String,String>inputs) {
		Genre genre = new Genre();
		genre = genre.getByType(inputs.get("genre"));
		Integer q = Integer.parseInt(inputs.get("quantity"));
		return new Book(genre.getId(),inputs.get("title"),inputs.get("isbn"),q);
	}
	
	public Book update(HashMap<String,String> inputs) {
		Book b = book.getByIsbn(inputs.get("isbn"));
		Integer q = Integer.parseInt(inputs.get("quantity"));
		b.setQuantity(q);
		b = b.update();
		return b;
	}
	
	public boolean delete(String id) {
		Book delete = book.find(id);
		if(delete != null) {
			return delete.delete();
		}
		return false;
	}
	
//	public Book restockBook(String isbn) {
//		Book b = new Book().getByIsbn(isbn);
//		if(b!=null) {
//			
//		}
//	}
	
	public Boolean testQuantity(String salary) {
		try {
			Integer.parseInt(salary);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public Boolean validate(HashMap<String,String>inputs) {
		Set<String> keys = inputs.keySet();
		for (String string : keys) {
			if(inputs.get(string).equals("")) {
				Message.error("All fields must be filled!");
				return false;
			}
		}
		
		if(!testQuantity(inputs.get("quantity"))) {
			Message.error("Quantity must be a number !");
			return false;
		}
		
		int q = Integer.parseInt(inputs.get("quantity"));
		if(q<=0) {
			Message.error("Quantity must be more than 0");
			return false;
		}
		
		return true;
	}
	
}
