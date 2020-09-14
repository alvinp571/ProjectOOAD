package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import components.Message;
import models.Member;
import models.User;
import views.CreateMembershipForm;
import views.ViewMembershipForm;
import views.base.BaseInternalView;
import views.base.IView;

public class MemberHandler {
	
	public IView showCreateMembershipForm() {
		return new CreateMembershipForm();
	}
	
	public BaseInternalView showViewMembershipForm() {
		return new ViewMembershipForm();
	}
	
	public List<Member> getAll(){
		Member member = new Member();
		List<Member> theMembers = member.all();
		return theMembers;
	}
	
	public Member insert(HashMap<String,String>inputs) {
		UserHandler userHandler = new UserHandler();
		User user = userHandler.insert(inputs);
		if(user==null) {
			return null;
		}
		
		Member member = new Member(user.getId(),inputs.get("address")).insert();
		return member;
	}
	
	public Member CreateMembership(HashMap<String, String>inputs) {
		if(validateRegister(inputs)) {
			Member member = insert(inputs);
			return member;
		}
		return null;
	}
	
	public boolean validateRegister(HashMap<String, String> inputs) {
		Set<String> keys = inputs.keySet();
		for (String x : keys) {
			if(inputs.get(x).equals("")) {
				Message.error("All fields must be filled!");
				return false;
			}
		}
		
		//validasi confirm password = password
		if(!inputs.get("password").equals(inputs.get("confirm_password"))) {
			Message.error("Password and confirmed password must be same !");
			return false;
		}
		
		//validasi gender harus diisi male atau female
		if(!inputs.get("gender").equals("Male")&&!inputs.get("gender").equals("Female")) {
			Message.error("Genders must be filled by male or female!");
			return false;
		}
		
		return true;
	}
}
