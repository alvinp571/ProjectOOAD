package controllers;


import java.util.HashMap;

import java.util.Set;

import components.Message;
import helper.Session;
import models.Member;
import models.User;
import views.AdministratorView;
import views.HumanCapitalView;
import views.LoginForm;
import views.ManagerView;
import views.MembershipView;
import views.PurchasingView;
import views.RegisterForm;
import views.base.IView;

public class AuthController {
	public IView showLoginForm() {
		return new LoginForm();
	}
	
	public IView showRegisterForm() {
		return new RegisterForm();
	}
	
	public Boolean login(String username,String password) {
		User user = new User().login(username, password);
		if(user!=null) {
			Session.user = user;
			Message.success("Welcome");
			return true;
		}
		Message.error("Invalid username or password ! ");
		return false;
	}
	
	public IView showWhichForm(String username) {
		String roles = Session.showRoleName();
		if(roles.equals("Manager")) {
			return new ManagerView();
		}else if(roles.equals("Administrator")) {
			return new AdministratorView();
		}else if(roles.equals("Human Capital")) {
			return new HumanCapitalView();
		}else if(roles.equals("Membership")) {
			return new MembershipView();
		}else if(roles.equals("Purchasing")) {
			return new PurchasingView();
		}
		System.out.println("Login gagal");
		return showLoginForm();
	}
	
	
	
	public boolean register(HashMap<String, String> inputs) {
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
		if(!inputs.get("gender").equals("male")&&!inputs.get("gender").equals("female")) {
			Message.error("Genders must be filled by male or female!");
			return false;
		}
		
		
		//insert to database
		UserHandler userhandler = new UserHandler();
		User user = userhandler.insert(inputs);
		if(user==null) {
			return false;
		}
		String id= user.getId();
		Member member = new Member(id,inputs.get("address"));
		member.createMembership();

		return true;
	}
}
