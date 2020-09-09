//package views;
//
//import java.awt.BorderLayout;
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
//import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
//
//import components.LabelTitle;
//import controllers.EmployeeHandler;
//import controllers.RoleHandler;
//import models.Employee;
//import models.Role;
//import views.base.BaseView;
//
//public class ViewEmployeeHumanCapital extends BaseView{
//	private static final long serialVersionUID = 1L;
//
//	  private LabelTitle title;
//	  private JLabel lblUsername, lblPassword;
//	  private JTextField txtUsername;
//	  private JPasswordField txtPassword;
//	  private JButton btnLogin, btnRegister,btnFired;
//	  private JTextArea txtShowEmployee1,txtShowEmployee2,txtShowEmployee3;
//
//	  public ViewEmployeeHumanCapital() {
//	    super("Employee List", 350, 225);
//	  }
//
//	  @Override
//	  public void initializeComponent() {
//	    title = new LabelTitle("View Employee");
//	    lblUsername = new JLabel("Username");
//	    lblPassword = new JLabel("Password");
//	    txtUsername = new JTextField();
//	    txtPassword = new JPasswordField();
//	    btnLogin = new JButton("Accept Employee");
//	    btnRegister = new JButton("Add New Employee");
//	    btnFired = new JButton("Fired Employee");
//	    txtShowEmployee1 = new JTextArea("");
//	    txtShowEmployee2 = new JTextArea("");
//	    txtShowEmployee3 = new JTextArea("");
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
//	    txtUsername.setText(roles);
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
//	    pnlFormInput2.add(txtUsername);
//	    
//	    JPanel pnlForm = new JPanel(new BorderLayout(8, 8));
////	    pnlForm.add(pnlFormLabel, BorderLayout.WEST);
//	    pnlForm.add(pnlFormInput2,BorderLayout.EAST);
//	    pnlForm.add(pnlFormInput1, BorderLayout.CENTER);
//
//	    JPanel pnlButton = new JPanel(new GridLayout(3, 1, 8, 8));
////	    pnlButton.add(btnLogin);
//	    pnlButton.add(btnRegister);
////	    pnlButton.add(btnFired);
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
import components.PanelForm;
import components.Table;
import controllers.EmployeeHandler;
import models.Employee;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
public final class ViewEmployeeHumanCapital extends BaseInternalView {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private LabelTitle title;
  private Table table;
  private JTabbedPane tabbedPane;
  private PanelForm panelAdd, panelAccept, panelFired;
  private JLabel lblInsertCode, lblInsertName, lblInsertCredit;
  private JLabel lblUpdateCode, lblUpdateName, lblUpdateCredit;
  private JLabel lblDeleteCode;
  private JLabel lblSelectUpdateCode, lblSelectDeleteCode;
  private JTextField txtInsertCode, txtInsertName;
  private JTextField txtUpdateName;
  private JComboBox<String> cbInsertCredit;
  private JComboBox<String> cbUpdateCredit;
  private JButton btnInsert, btnUpdate, btnDelete;
  private ButtonInternalClose close;

  public ViewEmployeeHumanCapital() {
    super("View Employee", 1000, 350);
  }

  @Override
  public void initializeComponent() {
    Vector<Object> tHeader = new Vector<>();
    tHeader.add("Id");
    tHeader.add("Salary");
    tHeader.add("Status");
    Vector<Vector<Object>> tRows = new Vector<>();
    
    EmployeeHandler employeeHandler = new EmployeeHandler();
    List<Employee> theEmployees = employeeHandler.getAll();
    
    Vector<Object> forEachRow;
    for (Employee e : theEmployees) {
    	forEachRow = new Vector<>();
		forEachRow.add(e.getId());
		forEachRow.add(e.getSalary());
		forEachRow.add(e.getStatus());
		tRows.add(forEachRow);
	}
    
    table = new Table(tHeader, tRows);

    title = new LabelTitle("Employees");

    tabbedPane = new JTabbedPane();

    /**
     * Initialize Component for Insert Form
     */
    
    lblInsertCode = new JLabel("Course Code");
    lblInsertName = new JLabel("Course Name");
    lblInsertCredit = new JLabel("Course Credit");
    txtInsertCode = new JTextField();
    txtInsertName = new JTextField();
    cbInsertCredit =
      new JComboBox<>(
        new String[] {
          "Choose Course Credit",
          "1",
          "2",
          "4",
          "5",
          "2/1",
          "2/2",
          "2/4",
          "4/2",
        }
      );
    btnInsert = new JButton("Insert");

    Component[][] insert = {
      new Component[] { lblInsertCode, lblInsertName, lblInsertCredit },
      new Component[] { txtInsertCode, txtInsertName, cbInsertCredit },
    };

    panelAdd = new PanelForm(insert, btnInsert, new Dimension(350, 350));

    /**
     * Initialize Component for Update Form
     */

    lblUpdateCode = new JLabel("Course Code");
    lblUpdateName = new JLabel("Course Name");
    lblUpdateCredit = new JLabel("Course Credit");
    lblSelectUpdateCode = new JLabel("Please Choose Course Code");
    txtUpdateName = new JTextField();
    txtUpdateName.setEnabled(Boolean.FALSE);
    cbUpdateCredit =
      new JComboBox<>(
        new String[] {
          "Choose Course Credit",
          "1",
          "2",
          "4",
          "5",
          "2/1",
          "2/2",
          "2/4",
          "4/2",
        }
      );
    cbUpdateCredit.setEnabled(Boolean.FALSE);
    btnUpdate = new JButton("Update");
    btnUpdate.setEnabled(Boolean.FALSE);

    Component[][] update = {
      new Component[] { lblUpdateCode, lblUpdateName, lblUpdateCredit },
      new Component[] { lblSelectUpdateCode, txtUpdateName, cbUpdateCredit },
    };

    panelAccept = new PanelForm(update, btnUpdate, new Dimension(350, 350));

    /**
     * Initialize Component for Delete Form
     */

    lblDeleteCode = new JLabel("Course Code");
    lblSelectDeleteCode = new JLabel("Please Choose Course Code");
    btnDelete = new JButton("Delete");
    btnDelete.setEnabled(Boolean.FALSE);

    Component[][] delete = {
      new Component[] { lblDeleteCode },
      new Component[] { lblSelectDeleteCode },
    };

    panelFired = new PanelForm(delete, btnDelete, new Dimension(350, 350));

    close = new ButtonInternalClose();
  }

  @Override
  public void addComponent() {
    tabbedPane.add("Add Employee", panelAdd.getPanel());
//    tabbedPane.add("Accept Employee", panelAccept.getPanel());
//    tabbedPane.add("Fired Employee", panelFired.getPanel());

    JPanel pnlCenter = new JPanel(new BorderLayout(8, 8));
    pnlCenter.add(table.getScrollPane(), BorderLayout.CENTER);
    pnlCenter.add(tabbedPane, BorderLayout.EAST);

    JPanel panel = new JPanel(new BorderLayout(8, 8));
    panel.add(title.getLabel(), BorderLayout.NORTH);
    panel.add(pnlCenter, BorderLayout.CENTER);
    panel.add(close.getButton(), BorderLayout.SOUTH);
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
          // TODO Auto-generated method stub

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
          // TODO Auto-generated method stub

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
          // TODO Auto-generated method stub

        }
      }
    );

    close.addListener(this);
  }
}
