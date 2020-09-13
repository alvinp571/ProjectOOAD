package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import components.Message;
import models.Employee;
import models.User;
import views.CreateEmployee;
import views.ViewEmployee;
import views.base.IView;

public class EmployeeHandler {
	
	Employee employee = new Employee();
	
	public IView showCreateEmployeeForm() {
		return new CreateEmployee();
	}
	
	public IView showViewEmployeeForm() {
		return new ViewEmployee();
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
	
	public Employee findById(String id) {
		return employee.find(id);
	}
	
	public Employee insert(HashMap<String,String>inputs) {
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
		
		if(findById(user.getId())!=null) {
			Message.error("Employee already exist !");
			return null;
		}	
		
		Employee employee = new Employee(user.getId(),inputs.get("status"),salary).insert();
		return employee;
	}
	
	public Employee acceptEmployee(String id) {
		Employee e = employee.find(id);
		if(e.getStatus().equals("Pending")) {
			e.setStatus("Active");
			e = e.update();
			return e;
		}
		
		return null;
	}
	
	public Employee firedEmployee(String id) {
		Employee e = employee.find(id);
		if(e.getStatus().equals("Active")) {
			e.setStatus("Fired");
			e = e.update();
			return e;
		}
		
		return null;
	}
	
	public Employee createWithActiveStatus(HashMap<String, String> inputs) {
		if(validate(inputs)) {
			//Generate password
			String password = inputs.get("username") + "abc";
			inputs.put("password", password);
			inputs.put("status","Active");
			Employee employee = insert(inputs);
			return employee;
		}
		
		return null;
	}
	
	public Employee createWithPendingStatus(HashMap<String, String> inputs) {
		if(validate(inputs)) {
			//Generate password
			String password = inputs.get("username") + "abc";
			inputs.put("password", password);
			inputs.put("status","Pending");
			Employee employee = insert(inputs);
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
