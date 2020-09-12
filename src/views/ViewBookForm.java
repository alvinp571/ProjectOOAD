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
//import javax.swing.JPanel;
//import javax.swing.JTextArea;
//import javax.swing.border.EmptyBorder;
//
//import components.LabelTitle;
//import controllers.BookHandler;
//import controllers.GenreHandler;
//import models.Book;
//import views.base.BaseView;
//
//public class ViewBookForm extends BaseView{
//	private static final long serialVersionUID = 1L;
//
//	  private LabelTitle title;
//	  private JButton btnCreateGenre,btnClose;
//	  private JTextArea txtId,txtGenreId,txtTitle,txtIsbn,txtQuantity;
//
//	  public ViewBookForm() {
//	    super("Book List", 350, 225);
//	  }
//
//	  @Override
//	  public void initializeComponent() {
//	    title = new LabelTitle("Book List");
//	    btnCreateGenre = new JButton("Create Genre");
//	    btnClose = new JButton("Close");
//	    txtId = new JTextArea("");
//	    txtGenreId = new JTextArea("");
//	    txtTitle = new JTextArea("");
//	    txtIsbn = new JTextArea("");
//	    txtQuantity = new JTextArea("");
//	    
//	    BookHandler bookHandler = new BookHandler();
//	    List<Book> theBooks = bookHandler.getAll();
//	    String id = "";
//	    String genreId = "";
//	    String title = "";
//	    String isbn = "";
//	    String quantity = "";
//	    for (Book book : theBooks) {
//			id = id + book.getId() + "\n";
//			genreId = genreId + book.getGenre_id()+ "\n";
//			title = title + book.getTitle() + "\n";
//			isbn = isbn + book.getIsbn() + "\n";
//			quantity = quantity + book.getQuantity().toString() +  "\n";
//		}
//	    
//	    txtId.setText(id);
//	    txtGenreId.setText(genreId);
//	    txtTitle.setText(title);
//	    txtIsbn.setText(isbn);
//	    txtQuantity.setText(quantity);
//	    
//	  }
//
//	  @Override
//	  public void addComponent() {
//	    
//	    JPanel pnlFormInput1 = new JPanel(new GridLayout(1,5,2,2));
//	    pnlFormInput1.add(txtId);
//	    pnlFormInput1.add(txtGenreId);
//	    pnlFormInput1.add(txtTitle);
//	    pnlFormInput1.add(txtIsbn);
//	    pnlFormInput1.add(txtQuantity);
//	    
//
//	    JPanel pnlButton = new JPanel(new GridLayout(1, 1, 8, 8));
////	    pnlButton.add(btnLogin);
////	    pnlButton.add(btnCreateGenre);
//	    pnlButton.add(btnClose);
//
//	    JPanel panel = new JPanel(new BorderLayout(8, 8));
//	    panel.add(title.getLabel(), BorderLayout.NORTH);
//	    panel.add(pnlFormInput1, BorderLayout.CENTER);
//	    panel.add(pnlButton, BorderLayout.SOUTH);
//	    panel.setBorder(new EmptyBorder(10, 10, 10, 10));
//
//	    add(panel);
//	  }
//
//	  @Override
//	  public void addListener() {
//	    btnCreateGenre.addActionListener(
//	      new AbstractAction() {
//	        /**
//	         *
//	         */
//	        private static final long serialVersionUID = 1L;
//
//	        @Override
//	        public void actionPerformed(ActionEvent e) {
//	        	new GenreHandler().showCreateGenre().showForm();
//	        }
//	      }
//	    );
//	    btnClose.addActionListener(
//	  	      new AbstractAction() {
//	  	        /**
//	  	         *
//	  	         */
//	  	        private static final long serialVersionUID = 1L;
//
//	  	        @Override
//	  	        public void actionPerformed(ActionEvent e) {
//	  	        	dispose();
//	  	        }
//	  	      }
//	  	    );
//	}
//}


package views;

import components.ButtonInternalClose;

import components.LabelTitle;
import components.Table;
import controllers.BookHandler;
import models.Book;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import views.base.BaseInternalView;

/**
 * Manage Course Form
 *
 * @author kevinsudut <kevinsuryaw@gmail.com>
 */
public final class ViewBookForm extends BaseInternalView {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private LabelTitle title;
  private Table table;
  private ButtonInternalClose close;

  public ViewBookForm() {
    super("View Book", 1000, 350);
  }

  @Override
  public void initializeComponent() {
    Vector<Object> tHeader = new Vector<>();
    tHeader.add("Id");
    tHeader.add("Genre");
    tHeader.add("Title");
    tHeader.add("ISBN");
    tHeader.add("Quantity");
    
    Vector<Vector<Object>> tRows = new Vector<>();
    
    BookHandler bookHandler = new BookHandler();
    List<Book> theBooks = bookHandler.getAll();
    
    Vector<Object> forEachRow;
    for (Book e : theBooks) {
    	forEachRow = new Vector<>();
		forEachRow.add(e.getId());
		forEachRow.add(e.getGenre_id());
		forEachRow.add(e.getTitle());
		forEachRow.add(e.getIsbn());
		forEachRow.add(e.getQuantity());
		tRows.add(forEachRow);
	}
    
    table = new Table(tHeader, tRows);

    title = new LabelTitle("Books");

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
