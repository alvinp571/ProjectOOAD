package controllers;


import components.Message;
import helper.Session;
import models.User;
import views.AdministratorView;
import views.HumanCapitalView;
import views.LoginForm;
import views.ManagerView;
import views.MembershipView;
import views.PurchasingView;
import views.CreateMembershipForm;
import views.base.IView;

public class AuthController {
	
	public IView showLoginForm() {
		return new LoginForm();
	}
	
	public IView showRegisterForm() {
		return new CreateMembershipForm();
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
		if(roles.equals("Manager")) return new ManagerView();
		else if(roles.equals("Administrator")) return new AdministratorView();
		else if(roles.equals("Human Capital")) return new HumanCapitalView();
		else if(roles.equals("Membership")) return new MembershipView();
		else if(roles.equals("Purchasing")) return new PurchasingView();
		else {
			Message.error("Login Failed !");
		}
		return showLoginForm();
	}
	
}
