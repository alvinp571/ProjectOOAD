package helper;

import java.util.List;

import models.Role;
import models.User;

public class Session {

	public static User user;
	
	public String showRoleName() {
		Role role = new Role();
		List<Role> theRoles = role.all();
		for (Role role2 : theRoles) {
			if(role2.getId().equals(user.getRoleId())) {
				if(role2.getName().equals("Manager")) {
					return "Manager";
				}else if(role2.getName().equals("Administrator")) {
					return "Administrator";
				}else if(role2.getName().equals("Human Capital")) {
					return "Human Capital";
				}else if(role2.getName().equals("Membership")) {
					return "Membership";
				}else if(role2.getName().equals("Purchasing")) {
					return "Purchasing";
				}
			}
		}
		return "";
	}
}
