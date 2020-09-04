package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import components.Message;
import models.Employee;
import models.User;
import views.CreateEmployee;
import views.ViewEmployeeManager;
import views.base.IView;

public class EmployeeHandler {
	
	public IView showCreateEmployeeForm() {
		return new CreateEmployee();
	}
	
	public IView showViewEmployeeForm() {
		return new ViewEmployeeManager();
	}
	
	public Boolean testSalary(String salary) {
		try {
			Integer.parseInt(salary);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public List<Employee> getAll(){
		Employee employee = new Employee();
		List<Employee> theEmployees = employee.all();
		return theEmployees;
	}
	
	
	public Employee createWithActiveStatus(HashMap<String, String> inputs) {
		if(validate(inputs)) {
			//Generate password
//			String password = inputs.get("password");
//			password = generatePassword(password);
//			
//			inputs.replace("password",password);
			
			//insert to database
			UserHandler userhandler = new UserHandler();
			User user = userhandler.insert(inputs);
			if(user==null) {
				return null;
			}
			int salary = 0;
			try {
				salary = Integer.parseInt(inputs.get("salary"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			String id= user.getId();
			Employee employee = new Employee(id,"Active",salary);
			employee.insert();
			return employee;
		}
		return null;
	}
	
	public Employee createWithPendingStatus(HashMap<String, String> inputs) {
		if(validate(inputs)) {
			//Generate password
//			String password = inputs.get("password");
//			password = generatePassword(password);
//			
//			inputs.replace("password",password);
			
			//insert to database
			UserHandler userhandler = new UserHandler();
			User user = userhandler.insert(inputs);
			if(user==null) {
				return null;
			}
			int salary = 0;
			try {
				salary = Integer.parseInt(inputs.get("salary"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			String id= user.getId();
			Employee employee = new Employee(id,"Pending",salary);
			employee.insert();
			return employee;
		}
		return null;
	}
	
	public String generatePassword(String password) {
		String newPassword = UUID.randomUUID().toString();
		return newPassword;
	}
	
	public Boolean validate(HashMap<String, String> inputs) {
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
		
		if(!testSalary(inputs.get("salary"))) {
			Message.error("salary must be a number !");
			return false;
		}
		
		int salary= Integer.parseInt(inputs.get("salary"));
		if(salary<10000) {
			Message.error("Salary must be more than 10000");
			return false;
		}
		
		return true;
	}
	
	
}
