package controllers;

import java.util.Date;
import java.util.List;
import javax.print.attribute.standard.MediaSize.ISO;
import helper.Session;
import models.Borrow;
import models.BorrowItem;
import views.ViewBorrowHistory;
import views.ViewPendingBorrowBook;
import views.base.BaseInternalView;

public class BorrowTransactionHandler {
	
	private Borrow borrow = new Borrow();
	private BorrowItem borrowItem = new BorrowItem();
	
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
	
}
