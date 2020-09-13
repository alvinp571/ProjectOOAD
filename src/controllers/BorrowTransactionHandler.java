package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import helper.Session;
import models.Book;
import models.Borrow;
import models.BorrowItem;
import views.ViewBorrowHistory;
import views.ViewPendingBorrowBook;
import views.base.BaseInternalView;

public class BorrowTransactionHandler {
	
	private Borrow borrow = new Borrow();
	private BorrowItem borrowItem = new BorrowItem();
	private Book book = new Book();
	
	public BaseInternalView showBorrowForm() {
		return new ViewPendingBorrowBook();
	}
	
	public BaseInternalView showBorrowHistoryForm() {
		return new ViewBorrowHistory();
	}
	
	public List<Borrow> getPendingStatus(boolean isOnlyCurrentMember){
		return borrow.getPendingStatus(isOnlyCurrentMember);
	}
	
	public List<BorrowItem> getBookItem(String id){
		return borrowItem.getBookItem(id);
	}
	
	public boolean acceptBorrowRequest(String id) {
		Borrow b = borrow.find(id);
		b.setStatus("Accepted");
		b = b.update();
		return true;
	}
	
	public List<Borrow> getAcceptStatus(Date date) {
		boolean isOnlyCurrentMember = false;
		if(Session.showRoleName().equals("Membership")) {
			isOnlyCurrentMember = true;
		}
		
		return borrow.getAcceptStatus(date, isOnlyCurrentMember);
	}
	
	public BorrowItem returnBook(HashMap <String,String> inputs) {
		boolean alreadyReturn = borrowItem.isBookAlreadyReturn(inputs.get("borrow_id"), inputs.get("book_id"));
		
		if (alreadyReturn) {
			return null;
		}
		
		borrowItem.setBorrow_id(inputs.get("borrow_id"));
		borrowItem.setBook_id(inputs.get("book_id"));
		
		borrowItem.update();
		return borrowItem;
	}
	
	public long calculateLate(String borrowDate, String returnDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		
		long differenceInDays = 0;
		try { 
			Date d1 = sdf.parse(borrowDate); 
			Date d2 = sdf.parse(returnDate); 
			long differenceInTime = d2.getTime() - d1.getTime(); 
			differenceInDays = (differenceInTime / (1000 * 60 * 60 * 24)) % 365; 
		} catch (Exception e) { 
			e.printStackTrace(); 
		}; 
		
		// 14 (2 weeks)
		return differenceInDays - 14;
	}
	
	public long calculateFine(BorrowItem bi) {
		String borrowDate = borrow.getBorrowTimeStamp(bi.getBorrow_id());
		String returnDate = borrowItem.getReturnTime(bi.getBorrow_id(), bi.getBook_id());

		long late = calculateLate(borrowDate, returnDate);
		
		//2 weeks, no late
		if (late <= 0) {
			return 0;
		}
		//2 weeks, late
		else {
			return late * 1000;
		}
	}
}
