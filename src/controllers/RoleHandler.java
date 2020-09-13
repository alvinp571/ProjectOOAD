package controllers;

import java.util.List;
import components.Message;
import models.Role;

public class RoleHandler {
	
	public List<Role> getAll(){
		Role role = new Role();
		List<Role> theRoles = role.all();
		if(theRoles.isEmpty()) {
			Message.error("Role is empty !");
		}
		
		return theRoles;
	}
	
}
