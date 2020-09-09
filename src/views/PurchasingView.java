//package views;
//
//import java.awt.BorderLayout;
//
//
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//
//import javax.swing.AbstractAction;
//import javax.swing.JButton;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//
//import components.LabelTitle;
//import controllers.BookHandler;
//import controllers.GenreHandler;
//import views.base.BaseView;
//
//public class PurchasingView extends BaseView{
//	private static final long serialVersionUID = 1L;
//
//	  private LabelTitle title;
//	  private JButton btnViewGenre, btnRestockBook,btnViewBook,btnDeleteBook;
//
//	  public PurchasingView() {
//	    super("Purchasing staff Form", 350, 225);
//	  }
//
//	  @Override
//	  public void initializeComponent() {
//	    title = new LabelTitle("Purchasing staff Form");
//	    btnViewGenre = new JButton("View Genre");
//	    btnRestockBook = new JButton("Restock Book");
//	    btnViewBook = new JButton("View Book");
//	    btnDeleteBook = new JButton("Delete Book");
//	    
//	  }
//
//	  @Override
//	  public void addComponent() {
////	    JPanel pnlFormLabel = new JPanel(new GridLayout(2, 1, 8, 8));
////	    pnlFormLabel.add(lblUsername);
////	    pnlFormLabel.add(lblPassword);
//
////	    JPanel pnlFormInput = new JPanel(new GridLayout(2, 1, 8, 8));
////	    pnlFormInput.add(txtUsername);
////	    pnlFormInput.add(txtPassword);
////
////	    JPanel pnlForm = new JPanel(new BorderLayout(8, 8));
////	    pnlForm.add(pnlFormLabel, BorderLayout.WEST);
////	    pnlForm.add(pnlFormInput, BorderLayout.CENTER);
//
//	    JPanel pnlButton = new JPanel(new GridLayout(4, 1, 8, 8));
//	    pnlButton.add(btnViewGenre);
//	    pnlButton.add(btnRestockBook);
//	    pnlButton.add(btnViewBook);
//	    pnlButton.add(btnDeleteBook);
//	    
//
//	    JPanel panel = new JPanel(new BorderLayout(8, 8));
//	    panel.add(title.getLabel(), BorderLayout.NORTH);
////	    panel.add(pnlForm, BorderLayout.CENTER);
//	    panel.add(pnlButton, BorderLayout.SOUTH);
//	    panel.setBorder(new EmptyBorder(10, 10, 10, 10));
//
//	    add(panel);
//	  }
//
//	@Override
//	public void addListener() {
//		btnViewGenre.addActionListener(
//				new AbstractAction() {
//					private static final long serialVersionUID = 1L;
//					
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						new GenreHandler().showGenre().showForm();
//					}
//				}
//				
//			);
//		btnViewBook.addActionListener(
//				new AbstractAction() {
//					private static final long serialVersionUID = 1L;
//					
//					
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						new BookHandler().showViewBookForm().showForm();
//					}
//				}
//			);
//	}
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
import controllers.BookHandler;
import controllers.GenreHandler;
import views.base.BaseInternalView;
import views.base.BaseView;

/**
 * Main Form
 *
 * @author kevinsudut <kevinsuryaw@gmail.com>
 */
public final class PurchasingView extends BaseView {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private JMenuBar menuBar;
  private JMenu mFile, mManage;
  private JMenuItem miLogout, miViewGenre, miRestockBook, miViewBook, miDeleteBook,miManageBook;
  private JDesktopPane desktopPane;

  private BaseInternalView viewGenre;
  private BaseInternalView viewBook;
  private BaseInternalView restockBook;
  private BaseInternalView deleteBook;
  
  public PurchasingView() {
    super("Purchasing Staff", Boolean.TRUE);
  }

  @Override
  public void initializeComponent() {
    menuBar = new JMenuBar();

    mFile = new JMenu("File");
    mManage = new JMenu("Manage");

    miLogout = new JMenuItem("Logout");
    miViewGenre = new JMenuItem("View Genre");
    miRestockBook = new JMenuItem("Restock Book");
    miViewBook = new JMenuItem("View Book");
    miDeleteBook = new JMenuItem("Delete Book");
    miManageBook = new JMenuItem("Manage Book");

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
    mManage.add(miViewGenre);
//    mManage.add(miRestockBook);
    mManage.add(miViewBook);
    mManage.add(miManageBook);
//    mManage.add(miDeleteBook);

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

    miViewGenre.addActionListener(
      new AbstractAction() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	if(viewGenre == null || viewGenre.isClosed()) {
        		viewGenre = new GenreHandler().showGenre();
        		desktopPane.add(viewGenre);
        		viewGenre.showForm();
        	}
        
        }
      }
    );
    miManageBook.addActionListener(
    		new AbstractAction() {
    			/**
	   	         *
	   	         */
	   	        private static final long serialVersionUID = 1L;
    			
				@Override
				public void actionPerformed(ActionEvent e) {
					if(restockBook == null || restockBook.isClosed()) {
    	        		//sementara karena belum ada controller
    	        		restockBook = new ManageBook();
    	        		desktopPane.add(restockBook);
    	        		restockBook.showForm();
    	        	}
				}
			}
    		
    	);
    
    miRestockBook.addActionListener(
    	      new AbstractAction() {
    	        /**
    	         *
    	         */
    	        private static final long serialVersionUID = 1L;

    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	        	if(restockBook == null || restockBook.isClosed()) {
    	        		//sementara karena belum ada controller
    	        		restockBook = new ManageBook();
    	        		desktopPane.add(restockBook);
    	        		restockBook.showForm();
    	        	}
    	        
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
   
    miDeleteBook.addActionListener(
    	      new AbstractAction() {
    	        /**
    	         *
    	         */
    	        private static final long serialVersionUID = 1L;

    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	        	if(deleteBook == null || deleteBook.isClosed()) {
    	        		//sementara karena belum ada controller
//    	        		deleteBook = new DeleteBook();
//    	        		desktopPane.add(deleteBook);
//    	        		deleteBook.showForm();
    	        	}
    	        }
    	      }
    	    );
    
  }
}
