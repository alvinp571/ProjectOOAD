//package views;
//
//import java.awt.BorderLayout;
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
//import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
//
//import components.LabelTitle;
//import controllers.EmployeeHandler;
//import controllers.MemberHandler;
//import views.base.BaseView;
//
//public class ViewMembership extends BaseView{
//	private static final long serialVersionUID = 1L;
//
//	  private LabelTitle title;
//	  private JLabel lblUsername, lblPassword;
//	  private JTextField txtUsername;
//	  private JPasswordField txtPassword;
//	  private JButton btnLogin, btnRegister,btnFired;
//	  private JTextArea txtShowEmployee1,txtShowEmployee2;
//
//	  public ViewMembership() {
//	    super("Member List", 350, 225);
//	  }
//
//	  @Override
//	  public void initializeComponent() {
//	    title = new LabelTitle("View Members");
//	    lblUsername = new JLabel("Username");
//	    lblPassword = new JLabel("Password");
//	    txtUsername = new JTextField();
//	    txtPassword = new JPasswordField();
//	    btnLogin = new JButton("Accept Employee");
//	    btnRegister = new JButton("Add New Employee");
//	    btnFired = new JButton("Fired Employee");
//	    txtShowEmployee1 = new JTextArea("");
//	    txtShowEmployee2 = new JTextArea("");
//	    
//	    MemberHandler memberHandler = new MemberHandler();
//	    List<models.Member> theMembers = memberHandler.getAll();
//	    String id = "";
//	    String address= "";
//	    for (models.Member member : theMembers) {
//			id  = id + member.getUser_id() + "\n";
//			address = address + member.getAddress() + "\n";
//		}
//	    
//	    txtShowEmployee1.setText(id);
//	    txtShowEmployee2.setText(address);
//	    
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
//	    
//	    JPanel pnlFormInput2 = new JPanel(new GridLayout(1,1,8,8));
//	    pnlFormInput2.add(txtUsername);
//	    
//	    JPanel pnlForm = new JPanel(new BorderLayout(8, 8));
////	    pnlForm.add(pnlFormLabel, BorderLayout.WEST);
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
import components.Table;
import controllers.MemberHandler;
import models.Member;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import views.base.BaseInternalView;

public final class ViewMembershipForm extends BaseInternalView {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private LabelTitle title;
  private Table table;
  private ButtonInternalClose close;

  public ViewMembershipForm() {
    super("View Membership", 1000, 350);
  }

  @Override
  public void initializeComponent() {
    Vector<Object> tHeader = new Vector<>();
    tHeader.add("Id");
    tHeader.add("Address");
    tHeader.add("Member Since");
    Vector<Vector<Object>> tRows = new Vector<>();
    
    MemberHandler memberHandler = new MemberHandler();
    List<Member> theMembers = memberHandler.getAll();
    
    Vector<Object> forEachRow;
    for (Member m : theMembers) {
    	forEachRow = new Vector<>();
		forEachRow.add(m.getUser_id());
		forEachRow.add(m.getAddress());
		forEachRow.add(m.getMember_since());
		tRows.add(forEachRow);
	}
    
    table = new Table(tHeader, tRows);

    title = new LabelTitle("Memberships");

    close = new ButtonInternalClose();
  }

  @Override
  public void addComponent() {
    JPanel pnlCenter = new JPanel(new BorderLayout(8, 8));
    pnlCenter.add(table.getScrollPane(), BorderLayout.CENTER);

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

    close.addListener(this);
  }
}