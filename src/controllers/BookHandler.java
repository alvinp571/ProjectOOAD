package controllers;

import java.util.List;

import components.Message;
import models.Book;
import views.ViewBookForm;
import views.base.BaseInternalView;

public class BookHandler {
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
	
	
}
