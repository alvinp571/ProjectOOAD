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
import controllers.BorrowTransactionHandler;
import controllers.EmployeeHandler;
import controllers.MemberHandler;
import views.base.BaseInternalView;
import views.base.BaseView;

public final class ManagerView extends BaseView {

  private static final long serialVersionUID = 1L;

  private JMenuBar menuBar;
  private JMenu mFile, mManage;
  private JMenuItem miLogout, miViewBorrowingHistory, miViewEmployees, miViewMembership;
  private JDesktopPane desktopPane;
  
  private BaseInternalView viewEmployee;
  private BaseInternalView viewMembership;
  private BaseInternalView viewBorrowHistory;
  
  public ManagerView() {
    super("Manager", Boolean.TRUE);
  }

  @Override
  public void initializeComponent() {
    menuBar = new JMenuBar();

    mFile = new JMenu("File");
    mManage = new JMenu("Manage");

    miLogout = new JMenuItem("Logout");
    miViewBorrowingHistory = new JMenuItem("View Borrow History");
    miViewEmployees = new JMenuItem("View Employees");
    miViewMembership = new JMenuItem("View Membership");

    desktopPane =
      new JDesktopPane() {

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

        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	if(viewBorrowHistory == null || viewBorrowHistory.isClosed()) {
	        	viewBorrowHistory = new BorrowTransactionHandler().showBorrowHistoryForm();
        		desktopPane.add(viewBorrowHistory);
        		viewBorrowHistory.showForm();
        	}
        }
      }
    );
    
    miViewEmployees.addActionListener(
    	      new AbstractAction() {

    	        private static final long serialVersionUID = 1L;

    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	        	if(viewEmployee == null || viewEmployee.isClosed()) {
    	        		viewEmployee = new EmployeeHandler().showViewEmployeeForm();
    	        		desktopPane.add(viewEmployee);
    	        		viewEmployee.showForm();
    	        	}
    	        }
    	      }
    );
    
    miViewMembership.addActionListener(
    	      new AbstractAction() {

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
