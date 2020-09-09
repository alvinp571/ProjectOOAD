package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartStorage {
	private HashMap<String,Book> carts;
	
	public CartStorage() {}
	
	public List<Book> getCart(){
	List<Book> theCarts = new ArrayList<Book>();
		for(Map.Entry<String,Book> set: carts.entrySet()) {
			Book book = new Book();
			book = set.getValue();
			theCarts.add(book);
		}
		return theCarts;
	}
	
	public void AddCart(Book book) {
		carts.put(book.getId(),book);
		return;
	}
	
	public void removeCart(Book book) {
		carts.remove(book.getId(), book);
		return;
	}
}
