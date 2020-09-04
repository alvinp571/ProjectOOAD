package views;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import components.LabelTitle;
import components.Message;
import controllers.EmployeeHandler;
import helper.Session;
import views.base.BaseView;

public class CreateEmployee extends BaseView{
	private static final long serialVersionUID = 1L;

	  private LabelTitle title;
	  private JLabel lblName, lblUsername, lblPassword, lblConfirmPassword,lblGender,lblSalary;
	  private JTextField txtName, txtUsername, txtGender,txtSalary;
	  private JPasswordField txtPassword, txtConfirmPassword;
	  private JButton btnRegister, btnClose;

	  public CreateEmployee() {
	    super("Register Employee Form", 350, 325);
	  }

	  @Override
	  public void initializeComponent() {
	    title = new LabelTitle("Register New Employee");
	    lblName = new JLabel("Name");
	    lblUsername = new JLabel("Username");
	    lblPassword = new JLabel("Password");
	    lblConfirmPassword = new JLabel("Confirm Password");
	    lblGender = new JLabel("Gender");
	    lblSalary = new JLabel("Salary");
	    txtName = new JTextField();
	    txtGender = new JTextField();
	    txtUsername = new JTextField();
	    txtSalary = new JTextField();
	    txtPassword = new JPasswordField();
	    txtConfirmPassword = new JPasswordField();
	    btnRegister = new JButton("Register");
	    btnClose = new JButton("Close");
	  }

	  @Override
	  public void addComponent() {
	    JPanel pnlFormLabel = new JPanel(new GridLayout(6, 1, 8, 8));
	    pnlFormLabel.add(lblName);
	    pnlFormLabel.add(lblUsername);
	    

	    pnlFormLabel.add(lblPassword);
	    pnlFormLabel.add(lblConfirmPassword);
	    pnlFormLabel.add(lblGender);
	    pnlFormLabel.add(lblSalary);
	    
	    
	    JPanel pnlFormInput = new JPanel(new GridLayout(6, 1, 8, 8));
	    pnlFormInput.add(txtName);
	    pnlFormInput.add(txtUsername);
	    pnlFormInput.add(txtPassword);
	    pnlFormInput.add(txtConfirmPassword);
	    pnlFormInput.add(txtGender);
	    pnlFormInput.add(txtSalary);

	    JPanel pnlForm = new JPanel(new BorderLayout(8, 8));
	    pnlForm.add(pnlFormLabel, BorderLayout.WEST);
	    pnlForm.add(pnlFormInput, BorderLayout.CENTER);

	    JPanel pnlButton = new JPanel(new GridLayout(2, 1, 8, 8));
	    pnlButton.add(btnRegister);
	    pnlButton.add(btnClose);

	    JPanel panel = new JPanel(new BorderLayout(8, 8));
	    panel.add(title.getLabel(), BorderLayout.NORTH);
	    panel.add(pnlForm, BorderLayout.CENTER);
	    panel.add(pnlButton, BorderLayout.SOUTH);
	    panel.setBorder(new EmptyBorder(10, 10, 10, 10));

	    add(panel);
	  }

	  @Override
	  public void addListener() {
	    btnRegister.addActionListener(
	      new AbstractAction() {
	        /**
	         *
	         */
	        private static final long serialVersionUID = 1L;

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	HashMap<String, String> inputs = new HashMap<String, String>();
	            inputs.put("name",txtName.getText());
	            inputs.put("username",txtUsername.getText());
	            inputs.put("password",new String(txtPassword.getPassword()));
	            inputs.put("confirm_password",new String(txtConfirmPassword.getPassword()));
	            inputs.put("gender", txtGender.getText());
	            inputs.put("salary", txtSalary.getText());
	            
	            EmployeeHandler employeeHandler = new EmployeeHandler();
	            Session session = new Session();
	            if(session.showRoleName().equals("Manager")) {
	            	if(employeeHandler.createWithActiveStatus(inputs)!=null) {
						Message.success("Input employee success !");
						employeeHandler.showCreateEmployeeForm().showForm();
					}
	            }else if(session.showRoleName().equals("Human Capital")) {
	            	if(employeeHandler.createWithPendingStatus(inputs)!=null) {
						Message.success("Input employee success !");
						employeeHandler.showCreateEmployeeForm().showForm();
					}
	            }
				
	            
	        }
	      }
	    );

	    btnClose.addActionListener(
	      new AbstractAction() {
	        /**
	         *
	         */
	        private static final long serialVersionUID = 1L;

	        @Override
	        public void actionPerformed(ActionEvent e) {
	          dispose();
	        }
	      }
	    );
	  }
}
