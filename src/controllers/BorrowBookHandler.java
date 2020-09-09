package controllers;

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
		return carts.getCart();
	}
	
	public boolean addToCart(Book book) {
		if(carts.getCart().size()==10) {
			Message.error("You have borrow max(10) books !");
			return false;
		}
		carts.AddCart(book);
		BookHandler bookHandler = new BookHandler();
		HashMap<String, String> inputs = new HashMap<String, String>();
		inputs.put("isbn",book.getIsbn());
		Integer a = 1;
		inputs.put("quantity",a.toString());
		inputs.put("title",book.getTitle());
		inputs.put("genre",book.getGenre_id());
		bookHandler.update(inputs);
		
		Message.success("Add to cart success !");
		return true;
	}
	
	public boolean removeCart(Book book) {
		carts.removeCart(book);
		BookHandler bookHandler = new BookHandler();
		HashMap<String, String> inputs = new HashMap<String, String>();
		inputs.put("isbn",book.getIsbn());
		Integer a = -1;
		inputs.put("quantity",a.toString());
		inputs.put("title",book.getTitle());
		inputs.put("genre",book.getGenre_id());
		bookHandler.update(inputs);
		

		Message.success("Remove from cart success !");
		return true;
	}
	
	public boolean borrowBook() {
		Borrow borrow = new Borrow();
		borrow.setMemberId(Session.user.getId());
		borrow.setStatus("Pending");
		borrow.insert();
		
		List<Book> theBooks = carts.getCart();
		for (Book book : theBooks) {
			new BorrowItem(borrow.getId(),book.getId()).insert();
		}
		Message.success("Borrow success !");
		return true;
	}
	
	
	
}
