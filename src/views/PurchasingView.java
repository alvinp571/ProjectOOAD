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

public final class PurchasingView extends BaseView {

  private static final long serialVersionUID = 1L;

  private JMenuBar menuBar;
  private JMenu mFile, mManage;
  private JMenuItem miLogout, miViewGenre, miViewBook,miManageBook;
  private JDesktopPane desktopPane;

  private BaseInternalView viewGenre;
  private BaseInternalView viewBook;
  private BaseInternalView restockBook;
  
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
    miViewBook = new JMenuItem("View Book");
    miManageBook = new JMenuItem("Manage Book");

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
    mManage.add(miViewGenre);
    mManage.add(miViewBook);
    mManage.add(miManageBook);

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

    miViewGenre.addActionListener(
      new AbstractAction() {

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
 
    miManageBook.addActionListener(
    		new AbstractAction() {

	   	        private static final long serialVersionUID = 1L;
    			
				@Override
				public void actionPerformed(ActionEvent e) {
					if(restockBook == null || restockBook.isClosed()) {
    	        		//sementara karena belum ada controller
    	        		restockBook = new BookHandler().showManageBookForm();
    	        		desktopPane.add(restockBook);
    	        		restockBook.showForm();
    	        	}
				}
			}
    		
    	);
  }
}
