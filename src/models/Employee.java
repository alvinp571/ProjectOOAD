package models;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mySQLConnector.Connect;

public class Employee {
	private String id,status;
	private Integer salary;
	
	private Connect connect = Connect.getInstance();
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String user_id,String status,Integer salary) {
		super();
		this.id = user_id;
		this.status = status;
		this.salary = salary;
	}
	
	public Employee(ResultSet rs) {
		try {
			this.id = rs.getString("user_id");
			this.status = rs.getString("status");
			this.salary = rs.getInt("salary");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Employee insert() {
		String query = String.format("INSERT INTO employees VALUE('%s',%d,'%s')",id,salary,status);;
		connect.executeUpdate(query);
		return this;
	}
	
//	public Employee update() {
//		
//	}
//	
	public List<Employee> all(){
		String query = String.format("SELECT * FROM employees");
		ResultSet rs = connect.executeQuery(query);
		List<Employee> theEmployees = new ArrayList<Employee>();
		try {
			if(!rs.next()) {
				System.out.println("The employee is still empty !");
			}
			else {
				do {
					Employee employee = new Employee(rs);
					theEmployees.add(employee);
				} while (rs.next());
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theEmployees; 
	}
	
	
	public Employee find(String id) {
		String query = String.format("SELECT * FROM  employees WHERE user_id = '%s' ",id);
		ResultSet rs = connect.executeQuery(query);
		try {
			if(rs.next()) {
				Employee employee = new Employee(rs);
				return employee;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
}
