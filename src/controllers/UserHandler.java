package controllers;

import java.util.HashMap;

import components.Message;
import helper.Session;
import models.User;

public class UserHandler {
	
	public User insert(HashMap<String, String> inputs) {
		Session session = new Session();
		String roles;
		if(Session.user == null) {
			roles = "Membership";
		}else {
			roles = session.showRoleName();			
		}
		
		if(roles.equals("Manager")||roles.equals("Human Capital")) {
			roles = "Purchasing";
		}
		User user = new User(inputs.get("name"),inputs.get("username"),inputs.get("password"),inputs.get("gender"),roles);
		if(!user.checkUser(inputs.get("username"),inputs.get("password"))) {
			user.insert();
			Message.success("Successfully create a new user");
			return user;
		}else {
			Message.error("Username is taken!");
			return null;
		}
	}
}
