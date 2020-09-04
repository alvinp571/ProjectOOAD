package controllers;

import java.util.List;


import models.Member;
import views.ViewMembership;
import views.base.IView;

public class MemberHandler {
	public IView showMembershipForm() {
		return new ViewMembership();
	}
	
	public List<Member> getAll(){
		Member member = new Member();
		List<Member> theMembers = member.all();
		return theMembers;
	}
}
