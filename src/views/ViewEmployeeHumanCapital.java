package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import components.LabelTitle;
import controllers.EmployeeHandler;
import controllers.RoleHandler;
import models.Employee;
import models.Role;
import views.base.BaseView;

public class ViewEmployeeHumanCapital extends BaseView{
	private static final long serialVersionUID = 1L;

	  private LabelTitle title;
	  private JLabel lblUsername, lblPassword;
	  private JTextField txtUsername;
	  private JPasswordField txtPassword;
	  private JButton btnLogin, btnRegister,btnFired;
	  private JTextArea txtShowEmployee1,txtShowEmployee2,txtShowEmployee3;

	  public ViewEmployeeHumanCapital() {
	    super("Employee List", 350, 225);
	  }

	  @Override
	  public void initializeComponent() {
	    title = new LabelTitle("View Employee");
	    lblUsername = new JLabel("Username");
	    lblPassword = new JLabel("Password");
	    txtUsername = new JTextField();
	    txtPassword = new JPasswordField();
	    btnLogin = new JButton("Accept Employee");
	    btnRegister = new JButton("Add New Employee");
	    btnFired = new JButton("Fired Employee");
	    txtShowEmployee1 = new JTextArea("");
	    txtShowEmployee2 = new JTextArea("");
	    txtShowEmployee3 = new JTextArea("");
	    
	    EmployeeHandler employeeHandler = new EmployeeHandler();
	    List<Employee> theEmployees = employeeHandler.getAll();
	    String id = "";
	    String salary = "";
	    String status= "";
	    for (Employee employee : theEmployees) {
			id = id + employee.getId() + "\n";
			salary = salary+  employee.getSalary().toString() + "\n";
			status = status +  employee.getStatus() + "\n";
		}
	    txtShowEmployee1.setText(id);
	    txtShowEmployee2.setText(salary);
	    txtShowEmployee3.setText(status);
	    
	    RoleHandler roleHandler = new RoleHandler();
	    List<Role> theRoles = roleHandler.getAll();
	    String roles = "";
	    for (Role role : theRoles) {
			roles = roles + role.getName() + "\n";
		}
	    System.out.println(roles);
	    txtUsername.setText(roles);
	  }

	  @Override
	  public void addComponent() {
	    JPanel pnlFormLabel = new JPanel(new GridLayout(2, 1, 8, 8));
	    pnlFormLabel.add(lblUsername);
	    pnlFormLabel.add(lblPassword);

	    JPanel pnlFormInput = new JPanel(new GridLayout(2, 1, 8, 8));
//	    pnlFormInput.add(txtUsername);
	    pnlFormInput.add(txtPassword);
	    
	    JPanel pnlFormInput1 = new JPanel(new BorderLayout(4,4));
	    pnlFormInput1.add(txtShowEmployee1,BorderLayout.WEST);
	    pnlFormInput1.add(txtShowEmployee2,BorderLayout.CENTER);
	    pnlFormInput1.add(txtShowEmployee3,BorderLayout.EAST);
	    
	    JPanel pnlFormInput2 = new JPanel(new GridLayout(1,1,8,8));
	    pnlFormInput2.add(txtUsername);
	    
	    JPanel pnlForm = new JPanel(new BorderLayout(8, 8));
//	    pnlForm.add(pnlFormLabel, BorderLayout.WEST);
	    pnlForm.add(pnlFormInput2,BorderLayout.EAST);
	    pnlForm.add(pnlFormInput1, BorderLayout.CENTER);

	    JPanel pnlButton = new JPanel(new GridLayout(3, 1, 8, 8));
//	    pnlButton.add(btnLogin);
	    pnlButton.add(btnRegister);
//	    pnlButton.add(btnFired);

	    JPanel panel = new JPanel(new BorderLayout(8, 8));
	    panel.add(title.getLabel(), BorderLayout.NORTH);
	    panel.add(pnlForm, BorderLayout.CENTER);
	    panel.add(pnlButton, BorderLayout.SOUTH);
	    panel.setBorder(new EmptyBorder(10, 10, 10, 10));

	    add(panel);
	  }

	  @Override
	  public void addListener() {
	    btnLogin.addActionListener(
	      new AbstractAction() {
	        /**
	         *
	         */
	        private static final long serialVersionUID = 1L;

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	new EmployeeHandler().showCreateEmployeeForm().showForm();
	        }
	      }
	    );

	    btnRegister.addActionListener(
	      new AbstractAction() {
	        /**
	         *
	         */
	        private static final long serialVersionUID = 1L;

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	new EmployeeHandler().showCreateEmployeeForm().showForm();
	        }
	      }
	    );
	    
	    btnFired.addActionListener(
	    	new AbstractAction() {
				
	    		private static final long serialVersionUID = 1L;
	    		
				@Override
				public void actionPerformed(ActionEvent e) {
					
				}
			}	
	    		
	    );
	  }
}
