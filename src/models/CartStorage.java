package models;

import java.util.Collection;
import java.util.HashMap;

public class CartStorage {
	
	private HashMap<String,Book> carts;
	
	public CartStorage() {
		carts = new HashMap<String, Book>();
	}
	
	public Collection<Book> getCart(){
		Collection<Book> theCarts = carts.values();
		return theCarts;
	}
	
	public void AddCart(Book book) {
		carts.put(book.getId(),book);
		return;
	}
	
	public void removeCart(Book book) {
		carts.remove(book.getId());
		return;
	}
	
}
