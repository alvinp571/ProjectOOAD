package controllers;

import java.util.HashMap;

import components.Message;
import helper.Session;
import models.Role;
import models.User;

public class UserHandler {
	
	public User insert(HashMap<String, String> inputs) {
		String roles;
		if(Session.user == null) roles = "Membership";
		else roles = inputs.get("role");
		
		Role role = new Role().getByName(roles);
		if(role==null) {
			Message.error("Role not found !");
			return null;
		}
		
		User user = new User(inputs.get("name"),inputs.get("username"),inputs.get("password"),inputs.get("gender"),roles);
		if(!user.checkUser(inputs.get("username"),inputs.get("password"))) {
			user.insert();
			return user;
		} else {
			Message.error("Username is taken!");
			return null;
		}
	}
	
	public User getByUsername(String username) {
		return new User().getbyUserName(username);
	}
	
}
