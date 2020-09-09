package controllers;

import java.util.HashMap;

import java.util.List;
import java.util.Set;


import components.Message;
import helper.Session;
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
			if(restockBook(inputs.get("isbn"))==null) {
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
		return new Book(genre.getId(),inputs.get("title"),inputs.get("isbn"),q).insert();
	}
	
	public void updateQuantity(Book book,int q) {
		book.setQuantity(book.getQuantity()+q);
		return;
	}
	
	public Book update(HashMap<String,String> inputs) {
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
		Book delete = book.find(id);
		if(delete != null) {
			return delete.delete();
		}
		return false;
	}
	
	public Book restockBook(String isbn) {
		Book b = new Book().getByIsbn(isbn);
		if(b!=null) {
			return b;
		}
		return null;
	}
	
	public Boolean testInteger(String s) {
		try {
			Long.parseLong(s);
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
		
		if(!testInteger(inputs.get("isbn"))) {
			Message.error("ISBN must be a number !");
			return false;
		}
		
		if(inputs.get("isbn").length()<10 || inputs.get("isbn").length()>13) {
			Message.error("ISBN length must be between 10 and 13");
			return false;
		}
		
		if(!testInteger(inputs.get("quantity"))) {
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
