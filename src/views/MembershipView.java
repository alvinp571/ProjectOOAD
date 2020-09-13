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

import controllers.BookHandler;
import controllers.BorrowBookHandler;
import controllers.BorrowTransactionHandler;
import views.base.BaseInternalView;
import views.base.BaseView;

public final class MembershipView extends BaseView {

  private static final long serialVersionUID = 1L;

  private JMenuBar menuBar;
  private JMenu mFile, mManage;
  private JMenuItem miLogout, miViewBook, miBorrowBook, miViewPendingBorrowBook, miViewBorrowHistory;
  private JDesktopPane desktopPane;
  
  private BaseInternalView viewBook;
  private BaseInternalView borrowBook;
  private BaseInternalView viewPendingBorrowBook;
  private BaseInternalView viewBorrowHistory;

  public MembershipView() {
    super("Membership", Boolean.TRUE);
  }

  @Override
  public void initializeComponent() {
    menuBar = new JMenuBar();

    mFile = new JMenu("File");
    mManage = new JMenu("Manage");

    miLogout = new JMenuItem("Logout");
    miViewBook = new JMenuItem("View Book");
    miBorrowBook = new JMenuItem("Borrow Book");
    miViewPendingBorrowBook = new JMenuItem("View Pending Borrow Book");
    miViewBorrowHistory = new JMenuItem("View Borrow History");


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
    mManage.add(miViewBook);
    mManage.add(miBorrowBook);
    mManage.add(miViewPendingBorrowBook);
    mManage.add(miViewBorrowHistory);

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
          // TODO Auto-generated method stub

        }
      }
    );

    miViewBook.addActionListener(
      new AbstractAction() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	if(viewBook == null || viewBook.isClosed()) {
        		viewBook = new BookHandler().showViewBookForm();
        		desktopPane.add(viewBook);
        		viewBook.showForm();
        	}
        }
      }
    );
    
    miBorrowBook.addActionListener(
    	      new AbstractAction() {
    	        /**
    	         *
    	         */
    	        private static final long serialVersionUID = 1L;

    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	        	if(borrowBook == null || borrowBook.isClosed()) {
    	        		//sementara karena belum ada controller
    	        		borrowBook = new BorrowBookForm();
    	        		desktopPane.add(borrowBook);
    	        		borrowBook.showForm();
    	        	}
    	        }
    	      }
    	    );
    
    miViewPendingBorrowBook.addActionListener(
    	      new AbstractAction() {
    	        /**
    	         *
    	         */
    	        private static final long serialVersionUID = 1L;

    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	        	if(viewPendingBorrowBook == null || viewPendingBorrowBook.isClosed()) {
    	        		viewPendingBorrowBook = new BorrowTransactionHandler().showBorrowForm();
    	        		desktopPane.add(viewPendingBorrowBook);
    	        		viewPendingBorrowBook.showForm();
    	        	}
    	        }
    	      }
    	    );
    
    miViewBorrowHistory.addActionListener(
    	      new AbstractAction() {
    	        /**
    	         *
    	         */
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
    
  }
}

