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
import controllers.BookHandler;
import controllers.BorrowTransactionHandler;
import controllers.MemberHandler;
import views.base.BaseInternalView;
import views.base.BaseView;

public final class AdministratorView extends BaseView {

	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar;
	private JMenu mFile, mManage;
	private JMenuItem miLogout, miViewBook, miViewMembership, miViewPendingBorrowBook, miViewBorrowHistory;
	private JDesktopPane desktopPane;

	private BaseInternalView viewMembership;
	private BaseInternalView viewBook;
	private BaseInternalView viewPendingBorrowBook;
	private BaseInternalView viewBorrowHistory;
  
	public AdministratorView() {
		super("Administrator Staff", Boolean.TRUE);
	}

	@Override
	public void initializeComponent() {
		menuBar = new JMenuBar();

		mFile = new JMenu("File");
		mManage = new JMenu("Manage");

		miLogout = new JMenuItem("Logout");
		miViewBook = new JMenuItem("View Book");
    	miViewMembership = new JMenuItem("View Membership");
    	miViewPendingBorrowBook = new JMenuItem("View Pending Borrow Book");
    	miViewBorrowHistory = new JMenuItem("View Borrow History");

    	desktopPane = new JDesktopPane() {
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
		mManage.add(miViewMembership);
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
					dispose();
		        	new AuthController().showLoginForm().showForm();
				}
			}
		);

		miViewBook.addActionListener(
			new AbstractAction() {
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
    
		miViewMembership.addActionListener(
			new AbstractAction() {
    	        private static final long serialVersionUID = 1L;

    	        @Override
    	        public void actionPerformed(ActionEvent e) {
					if(viewMembership == null || viewMembership.isClosed()) {
    	        		viewMembership = new MemberHandler().showViewMembershipForm();
    	        		desktopPane.add(viewMembership);
    	        		viewMembership.showForm();
    	        	}
    	        }
    	    }
		);
    
		miViewPendingBorrowBook.addActionListener(
    	    new AbstractAction() {
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
