//package views;
//
//import java.awt.BorderLayout;
//
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//
//import javax.swing.AbstractAction;
//import javax.swing.JButton;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JPasswordField;
//import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
//
//import components.LabelTitle;
//import controllers.EmployeeHandler;
//import controllers.MemberHandler;
//import views.base.BaseView;
//
//public class ManagerView extends BaseView{
//	private static final long serialVersionUID = 1L;
//
//	  private LabelTitle title;
//	  private JLabel lblUsername, lblPassword;
//	  private JTextField txtUsername;
//	  private JPasswordField txtPassword;
//	  private JButton btnLogin, btnRegister;
//
//	  public ManagerView() {
//	    super("Manager Form", 350, 225);
//	  }
//
//	  @Override
//	  public void initializeComponent() {
//	    title = new LabelTitle("Manager Form");
//	    lblUsername = new JLabel("Username");
//	    lblPassword = new JLabel("Password");
//	    txtUsername = new JTextField();
//	    txtPassword = new JPasswordField();
//	    btnLogin = new JButton("View Membership");
//	    btnRegister = new JButton("View Employee");
//	  }
//
//	  @Override
//	  public void addComponent() {
//	    JPanel pnlFormLabel = new JPanel(new GridLayout(2, 1, 8, 8));
//	    pnlFormLabel.add(lblUsername);
//	    pnlFormLabel.add(lblPassword);
//
//	    JPanel pnlFormInput = new JPanel(new GridLayout(2, 1, 8, 8));
//	    pnlFormInput.add(txtUsername);
//	    pnlFormInput.add(txtPassword);
//
//	    JPanel pnlForm = new JPanel(new BorderLayout(8, 8));
//	    pnlForm.add(pnlFormLabel, BorderLayout.WEST);
//	    pnlForm.add(pnlFormInput, BorderLayout.CENTER);
//
//	    JPanel pnlButton = new JPanel(new GridLayout(2, 1, 8, 8));
//	    pnlButton.add(btnLogin);
//	    pnlButton.add(btnRegister);
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
//	@Override
//	public void addListener() {
//		 btnLogin.addActionListener(
//			new AbstractAction() {
//				private static final long serialVersionUID = 1L;
//				@Override
//		        public void actionPerformed(ActionEvent e) {
//					new MemberHandler().showMembershipForm().showForm();
//				}
//		    }
//		);
//
//		 btnRegister.addActionListener(
//			new AbstractAction() {
//				private static final long serialVersionUID = 1L;
//				@Override
//		        public void actionPerformed(ActionEvent e) {
//			       	new EmployeeHandler().showViewEmployeeForm().showForm();
//				}
//		    }
//		);
//	}
//	
//}


package views;

import java.awt.Graphics;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controllers.AuthController;
import controllers.MemberHandler;
import views.base.BaseInternalView;
import views.base.BaseView;

/**
 * Main Form
 *
 * @author kevinsudut <kevinsuryaw@gmail.com>
 */
public final class ManagerView extends BaseView {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private JMenuBar menuBar;
  private JMenu mFile, mManage;
  private JMenuItem miLogout, miViewBorrowingHistory, miViewEmployees, miViewMembership;
  private JDesktopPane desktopPane;
  
  private ViewEmployee viewEmployee;
  private BaseInternalView viewMembership;
  private BaseInternalView viewBorrowHistoryManager;
  
  public ManagerView() {
    super("Manager", Boolean.TRUE);
  }

  @Override
  public void initializeComponent() {
    menuBar = new JMenuBar();

    mFile = new JMenu("File");
    mManage = new JMenu("Manage");

    miLogout = new JMenuItem("Logout");
    miViewBorrowingHistory = new JMenuItem("View Borrowing History");
    miViewEmployees = new JMenuItem("View Employees");
    miViewMembership = new JMenuItem("View Membership");

    desktopPane =
      new JDesktopPane() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        protected void paintComponent(Graphics g) {
          super.paintComponent(g);
          try {
            final File PATH = new File("assets/library.jpg");
            Image image = ImageIO.read(PATH);

            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      };
  }

  @Override
  public void addComponent() {
    mFile.add(miLogout);
    mManage.add(miViewBorrowingHistory);
    mManage.add(miViewEmployees);
    mManage.add(miViewMembership);

    menuBar.add(mFile);
    menuBar.add(mManage);

    setJMenuBar(menuBar);

    setContentPane(desktopPane);
  }

  @Override
  public void addListener() {
    miLogout.addActionListener(
      new AbstractAction() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	dispose();
        	new AuthController().showLoginForm().showForm();
        }
      }
    );

    miViewBorrowingHistory.addActionListener(
      new AbstractAction() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	if(viewBorrowHistoryManager == null || viewBorrowHistoryManager.isClosed()) {
        		viewBorrowHistoryManager = new ViewBorrowHistoryAdminAndManager();
        		desktopPane.add(viewBorrowHistoryManager);
        		viewBorrowHistoryManager.showForm();
        	}
        
        }
      }
    );
    
    miViewEmployees.addActionListener(
    	      new AbstractAction() {
    	        /**
    	         *
    	         */
    	        private static final long serialVersionUID = 1L;

    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	        	if(viewEmployee == null || viewEmployee.isClosed()) {
    	        		viewEmployee = new ViewEmployee();
    	        		desktopPane.add(viewEmployee);
    	        		viewEmployee.showForm();
    	        	}
    	        }
    	      }
    );
    
    miViewMembership.addActionListener(
    	      new AbstractAction() {
    	        /**
    	         *
    	         */
    	        private static final long serialVersionUID = 1L;

    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	        	if(viewMembership == null || viewMembership.isClosed()) {
    	        		viewMembership = new MemberHandler().showMembershipForm();
    	        		desktopPane.add(viewMembership);
    	        		viewMembership.showForm();
    	        	}
    	        }
    	      }
    );
    
  }
}
