package controllers;

import java.util.List;

import models.Member;
import views.ViewMembership;
import views.base.BaseInternalView;

public class MemberHandler {
	public BaseInternalView showMembershipForm() {
		return new ViewMembership();
	}
	
	public List<Member> getAll(){
		Member member = new Member();
		List<Member> theMembers = member.all();
		return theMembers;
	}
}
