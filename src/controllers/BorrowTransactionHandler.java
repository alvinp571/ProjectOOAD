package controllers;

import java.util.List;

import models.Borrow;
import models.BorrowItem;
import views.ViewPendingBorrowBookAdmin;
import views.base.BaseInternalView;

public class BorrowTransactionHandler {
	private Borrow borrow = new Borrow();
	private BorrowItem borrowItem = new BorrowItem();
	
	public BaseInternalView showBorrowForm() {
		return new ViewPendingBorrowBookAdmin();
	}
	
	public List<Borrow> getPendingStatus(boolean isOnlyCurrentMember){
		return borrow.getPendingStatus(isOnlyCurrentMember);
	}
	
	public List<BorrowItem> getBookItem(String id){
		return borrowItem.getBookItem(id);
	}
	
//	public boolean acceptBorrowRequest(String id) {
//		if(borrowItem.isBookAlreadyReturn(id,BookId)) {
//			
//		}
//		return true;
//	}
}
