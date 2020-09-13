package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import components.Message;
import helper.Session;
import models.Book;
import models.Borrow;
import models.BorrowItem;
import models.CartStorage;
import views.BorrowBookForm;
import views.base.IView;

public class BorrowBookHandler {
	
	private CartStorage carts = new CartStorage();
	
	public IView showBorrowBookForm() {
		return new BorrowBookForm();
	}
	
	public List<Book> getAvailableBook() {
		BookHandler bookHandler =  new BookHandler();
		List<Book> theBooks = bookHandler.getBookByQuantityMoreThanZero();
		return theBooks;
	}
	
	public List<Book> getCart(){
		List<Book> theCarts = new ArrayList<Book>(carts.getCart());
		return theCarts;
	}
	
	public boolean addToCart(Book book) {
		if(carts.getCart().size()==10) {
			Message.error("Max borrow is 10 books !");
			return false;
		}
		
		carts.AddCart(book);
		BookHandler bookHandler = new BookHandler();
		HashMap<String, String> inputs = new HashMap<String, String>();
		inputs.put("isbn",book.getIsbn());
		Integer a = -1;
		inputs.put("quantity",a.toString());
		inputs.put("title",book.getTitle());
		inputs.put("genre",book.getGenre_id());
		bookHandler.update(inputs);

		return true;
	}
	
	public boolean borrowBook() {
		if(carts.getCart().isEmpty()) {
			Message.error("Carts is empty !");
			return false;
		}
		
		Borrow borrow = new Borrow(Session.user.getId(),"Pending");
		borrow.insert();
		
		List<Book> theBooks = getCart();
		for (Book book : theBooks) {
			new BorrowItem(borrow.getId(),book.getId()).insert();
		}
		
		return true;
	}
	
	public boolean removeCart(Book book) {
		//TODO no false?
		carts.removeCart(book);
		BookHandler bookHandler = new BookHandler();
		HashMap<String, String> inputs = new HashMap<String, String>();
		inputs.put("isbn",book.getIsbn());
		Integer a = 1;
		inputs.put("quantity",a.toString());
		inputs.put("title",book.getTitle());
		inputs.put("genre",book.getGenre_id());
		bookHandler.update(inputs);

		return true;
	}
	
}
