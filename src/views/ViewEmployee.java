//package views;
//
//import java.awt.BorderLayout;
//
//
//
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.util.List;
//
//import javax.swing.AbstractAction;
//import javax.swing.JButton;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JPasswordField;
//import javax.swing.JTextArea;
////import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
//
//import components.LabelTitle;
//import controllers.EmployeeHandler;
//import controllers.RoleHandler;
//import models.Employee;
//import models.Role;
//import views.base.BaseInternalView;
//import views.base.BaseView;
//
//public class ViewEmployeeManager extends BaseView{
//
//	private static final long serialVersionUID = 1L;
//
//	  private LabelTitle title;
//	  private JLabel lblUsername, lblPassword;
////	  private JTextField txtUsername;
//	  private JPasswordField txtPassword;
//	  private JButton btnLogin, btnRegister,btnFired;
//	  private JTextArea txtShowEmployee1,txtShowEmployee2,txtShowEmployee3,txtShowRole;
//
//	  public ViewEmployeeManager() {
//	    super("Employee List", 350, 225);
//	  }
//
//	  @Override
//	  public void initializeComponent() {
//	    title = new LabelTitle("View Employee");
//	    lblUsername = new JLabel("Username");
//	    lblPassword = new JLabel("Password");
////	    txtUsername = new JTextField();
//	    txtPassword = new JPasswordField();
//	    btnLogin = new JButton("Accept Employee");
//	    btnRegister = new JButton("Add New Employee");
//	    btnFired = new JButton("Fired Employee");
//	    txtShowEmployee1 = new JTextArea("");
//	    txtShowEmployee2 = new JTextArea("");
//	    txtShowEmployee3 = new JTextArea("");
//	    txtShowRole = new JTextArea("");
//	    
//	    EmployeeHandler employeeHandler = new EmployeeHandler();
//	    List<Employee> theEmployees = employeeHandler.getAll();
//	    String id = "";
//	    String salary = "";
//	    String status= "";
//	    for (Employee employee : theEmployees) {
//			id = id + employee.getId() + "\n";
//			salary = salary+  employee.getSalary().toString() + "\n";
//			status = status +  employee.getStatus() + "\n";
//		}
//	    txtShowEmployee1.setText(id);
//	    txtShowEmployee2.setText(salary);
//	    txtShowEmployee3.setText(status);
//	    
//	    RoleHandler roleHandler = new RoleHandler();
//	    List<Role> theRoles = roleHandler.getAll();
//	    String roles = "";
//	    for (Role role : theRoles) {
//			roles = roles + role.getName() + "\n";
//		}
//	    System.out.println(roles);
//	    txtShowRole.setText(roles);
//	  }
//
//	  @Override
//	  public void addComponent() {
//	    JPanel pnlFormLabel = new JPanel(new GridLayout(2, 1, 8, 8));
//	    pnlFormLabel.add(lblUsername);
//	    pnlFormLabel.add(lblPassword);
//
//	    JPanel pnlFormInput = new JPanel(new GridLayout(2, 1, 8, 8));
////	    pnlFormInput.add(txtUsername);
//	    pnlFormInput.add(txtPassword);
//	    
//	    JPanel pnlFormInput1 = new JPanel(new BorderLayout(4,4));
//	    pnlFormInput1.add(txtShowEmployee1,BorderLayout.WEST);
//	    pnlFormInput1.add(txtShowEmployee2,BorderLayout.CENTER);
//	    pnlFormInput1.add(txtShowEmployee3,BorderLayout.EAST);
//	    
//	    JPanel pnlFormInput2 = new JPanel(new GridLayout(1,1,8,8));
//	    pnlFormInput2.add(txtShowRole);
//	    
//	    JPanel pnlForm = new JPanel(new BorderLayout(8, 8));
////	    pnlForm.add(pnlFormLabel, BorderLayout.WEST);
//	    pnlForm.add(pnlFormInput2,BorderLayout.EAST);
//	    pnlForm.add(pnlFormInput1, BorderLayout.CENTER);
//
//	    JPanel pnlButton = new JPanel(new GridLayout(3, 1, 8, 8));
//	    pnlButton.add(btnLogin);
//	    pnlButton.add(btnRegister);
//	    pnlButton.add(btnFired);
//
//	    JPanel panel = new JPanel(new BorderLayout(8, 8));
//	    panel.add(title.getLabel(), BorderLayout.NORTH);
//	    panel.add(pnlForm, BorderLayout.CENTER);
//	    panel.add(pnlButton, BorderLayout.SOUTH);
//	    panel.setBorder(new EmptyBorder(10, 10, 10, 10));
//
//	    add(panel);
//	  }
//
//	  @Override
//	  public void addListener() {
//	    btnLogin.addActionListener(
//	      new AbstractAction() {
//	        /**
//	         *
//	         */
//	        private static final long serialVersionUID = 1L;
//
//	        @Override
//	        public void actionPerformed(ActionEvent e) {
//	        	new EmployeeHandler().showCreateEmployeeForm().showForm();
//	        }
//	      }
//	    );
//
//	    btnRegister.addActionListener(
//	      new AbstractAction() {
//	        /**
//	         *
//	         */
//	        private static final long serialVersionUID = 1L;
//
//	        @Override
//	        public void actionPerformed(ActionEvent e) {
//	        	new EmployeeHandler().showCreateEmployeeForm().showForm();
//	        }
//	      }
//	    );
//	    
//	    btnFired.addActionListener(
//	    	new AbstractAction() {
//				
//	    		private static final long serialVersionUID = 1L;
//	    		
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					
//				}
//			}	
//	    		
//	    );
//	  }
//}

package views;

import components.ButtonInternalClose;
import components.LabelTitle;
import components.Message;
import components.PanelForm;
import components.Table;
import controllers.EmployeeHandler;
import helper.Session;
import models.Employee;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import views.base.BaseInternalView;

/**
 * Manage Course Form
 *
 * @author kevinsudut <kevinsuryaw@gmail.com>
 */
public final class ViewEmployee extends BaseInternalView {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private LabelTitle title;
  private Table table;
  private JTabbedPane tabbedPane;
  private PanelForm panelAdd, panelAccept, panelFired;
  private JLabel lblInsertName, lblInsertUsername, lblInsertSalary,lblInsertRole,lblInsertGender;
  private JLabel lblUpdateName;
  private JLabel lblDeleteEmployee;
  private JLabel lblSelectUpdateEmployee, lblSelectDeleteEmployee;
  private JTextField txtInsertName, txtInsertUsername,txtInsertSalary,txtInsertRole;
  private JRadioButton rbInsertMaleGender,rbInsertFemaleGender;
  private ButtonGroup bg1;
  private JComboBox<String> cbInsertGender;
  private JButton btnInsert, btnUpdate, btnDelete;
  private ButtonInternalClose close;

  private EmployeeHandler employeeHandler = new EmployeeHandler();
  
  public ViewEmployee() {
    super("View Employe", 1000, 350);
  }

  @Override
  public void initializeComponent() {
    Vector<Object> tHeader = new Vector<>();
    tHeader.add("Employee Id");
    tHeader.add("Salary");
    tHeader.add("Status");
    Vector<Vector<Object>> tRows = new Vector<>();
    
    EmployeeHandler employeeHandler = new EmployeeHandler();
    List<Employee> theEmployees = employeeHandler.getAll();
    
    for (Employee e : theEmployees) {
    	Vector<Object> forEachRow = addRow(e);
		tRows.add(forEachRow);
	}
    
    table = new Table(tHeader, tRows);

    title = new LabelTitle("Employees");

    tabbedPane = new JTabbedPane();

    /**
     * Initialize Component for Insert Form
     */
    
    lblInsertName = new JLabel("Employee Name");
    lblInsertSalary = new JLabel("Employee Salary");
    lblInsertUsername = new JLabel("Employee Username");
    lblInsertRole= new JLabel("Employee Role");
    lblInsertGender = new JLabel("Employee Gender");
    txtInsertUsername = new JTextField();
    txtInsertName = new JTextField();
    txtInsertRole = new JTextField();
    txtInsertSalary = new JTextField();
    rbInsertMaleGender = new JRadioButton("Male");
    rbInsertFemaleGender = new JRadioButton("Female");
    bg1 = new ButtonGroup();
    bg1.add(rbInsertMaleGender);
    bg1.add(rbInsertFemaleGender);
    
    cbInsertGender =
      new JComboBox<>(
        new String[] {
          "Choose Gender",
          "Male",
          "Female",
        }
      );
    btnInsert = new JButton("Add Employee");

    Component[][] insert = {
      new Component[] { lblInsertName, lblInsertUsername, lblInsertRole,lblInsertSalary,lblInsertGender },
      new Component[] { txtInsertName, txtInsertUsername,txtInsertRole,txtInsertSalary,cbInsertGender},
    };

    panelAdd = new PanelForm(insert, btnInsert, new Dimension(350, 350));

    /**
     * Initialize Component for Update Form
     */

    lblUpdateName = new JLabel("Employee Name");
    lblSelectUpdateEmployee = new JLabel("Please Choose Employee");
    btnUpdate = new JButton("Accept Employee");
    btnUpdate.setEnabled(Boolean.FALSE);

    Component[][] update = {
      new Component[] { lblUpdateName},
      new Component[] { lblSelectUpdateEmployee},
    };

    panelAccept = new PanelForm(update, btnUpdate, new Dimension(350, 350));

    /**
     * Initialize Component for Delete Form
     */

    lblDeleteEmployee = new JLabel("Fired Employee");
    lblSelectDeleteEmployee = new JLabel("Please Choose Employee");
    btnDelete = new JButton("Fired Employee");
    btnDelete.setEnabled(Boolean.FALSE);

    Component[][] delete = {
      new Component[] { lblDeleteEmployee},
      new Component[] { lblSelectDeleteEmployee},
    };

    panelFired = new PanelForm(delete, btnDelete, new Dimension(350, 350));
    
    close = new ButtonInternalClose();
  }

private Vector<Object> addRow(Employee e) {
	Vector<Object> forEachRow = new Vector<>();
	forEachRow.add(e.getId());
	forEachRow.add(e.getSalary());
	forEachRow.add(e.getStatus());
	return forEachRow;
}

  @Override
  public void addComponent() {
	tabbedPane.add("Add Employee", panelAdd.getPanel());
	if(Session.showRoleName().equals("Manager")) {
		tabbedPane.add("Accept Employee", panelAccept.getPanel());
		tabbedPane.add("Fired Employee", panelFired.getPanel());		
	}

    JPanel pnlCenter = new JPanel(new BorderLayout(8, 8));
    pnlCenter.add(table.getScrollPane(), BorderLayout.CENTER);
    pnlCenter.add(tabbedPane, BorderLayout.EAST);
    
    JPanel pnlSouth = new JPanel(new BorderLayout(4, 4));
    pnlSouth.add(close.getButton(), BorderLayout.SOUTH);

    JPanel panel = new JPanel(new BorderLayout(8, 8));
    panel.add(title.getLabel(), BorderLayout.NORTH);
    panel.add(pnlCenter, BorderLayout.CENTER);
    panel.add(pnlSouth, BorderLayout.SOUTH);
    panel.setBorder(new EmptyBorder(10, 10, 10, 10));

    add(panel);
  }

  @Override
  public void addListener() {
    table.addMouseListener(
      new MouseInputAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
          super.mouseClicked(e);
          
          int row = table.getSelectedRow();
          lblSelectUpdateEmployee.setText(table.getValueAt(row,0));
          btnUpdate.setEnabled(true);
          
          lblSelectDeleteEmployee.setText(table.getValueAt(row,0));
          btnDelete.setEnabled(true);
        }
      }
    );

    btnInsert.addActionListener(
      new AbstractAction() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	HashMap<String,String> inputs = new HashMap<String, String>();
        	inputs.put("name",txtInsertName.getText());
        	inputs.put("username",txtInsertUsername.getText());
        	inputs.put("role",txtInsertRole.getText());
        	inputs.put("salary",txtInsertSalary.getText());
        	inputs.put("gender",cbInsertGender.getSelectedItem().toString());
        	
        	Employee employee = new Employee();
        	if(Session.showRoleName().equals("Manager")) {
        		employee = employeeHandler.createWithActiveStatus(inputs);        		
        	}else {
        		employee = employeeHandler.createWithPendingStatus(inputs);     
        	}
        	
        	if(employee!=null) {
        		table.addNewRow(addRow(employee));
        		txtInsertName.setText("");
        		txtInsertUsername.setText("");
        		txtInsertRole.setText("");
        		txtInsertSalary.setText("");
        		cbInsertGender.setSelectedIndex(0);
        		Message.success("Success insert a new Employee");
        	}
        }
      }
    );

    btnUpdate.addActionListener(
      new AbstractAction() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	int result = Message.confirm("Are you sure want to Accept this employee ?", "Fired Employee");
        	if(result==JOptionPane.YES_OPTION) {
        		Employee em = employeeHandler.acceptEmployee(lblSelectDeleteEmployee.getText());
        		if(em !=null) {
            		table.updateRow(table.getSelectedRow(),em.getId(),em.getSalary().toString(),em.getStatus());
            		Message.success("Success accepting employee !");
            		refreshForm();
        		}else {
        			System.out.println(lblSelectDeleteEmployee.getText());
        			Message.error("Employee is not in pending status!");
            		refreshForm();
        		}
        	}
        }

      }
    );

    btnDelete.addActionListener(
      new AbstractAction() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	int result = Message.confirm("Are you sure want to Fired this employee ?", "Fired Employee");
        	if(result==JOptionPane.YES_OPTION) {
        		Employee em = employeeHandler.firedEmployee(lblSelectDeleteEmployee.getText());
        		if(em !=null) {
        			table.updateRow(table.getSelectedRow(),em.getId(),em.getSalary().toString(),em.getStatus());
            		Message.success("Success firing employee !");
            		refreshForm();
        		}else {
        			Message.error("Employee is not in active status !");
            		refreshForm();
        		}
        	}
        }
      }
    );

    close.addListener(this);
  }
  
  private void refreshForm() {
	  	lblSelectUpdateEmployee.setText("Please Choose Employee");
	  	btnUpdate.setEnabled(false);
	  	
		lblSelectDeleteEmployee.setText("Please Choose Employee Code");
		btnDelete.setEnabled(false);
  }
}
