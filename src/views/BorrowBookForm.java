package views;

import components.ButtonInternalClose;

import components.LabelTitle;
import components.Message;
import components.PanelForm;
import components.Table;
import controllers.BookHandler;
import controllers.BorrowBookHandler;
import controllers.GenreHandler;
import models.Book;
import models.Genre;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import views.base.BaseInternalView;

public final class BorrowBookForm extends BaseInternalView {
	
  private static final long serialVersionUID = 1L;

  private LabelTitle title;
  private Table table, tableCart;
  private JTabbedPane tabbedPane;
  private PanelForm panelAdd, panelRemove;
  private JLabel lblInsertName, lblSelectInsertName;
  private JLabel lblRemoveName, lblSelectRemoveName;
  private JButton btnInsert, btnRemove, btnBorrowBook;
  private ButtonInternalClose close;

  public BorrowBookForm() {
    super("Borrow Book", 1300, 350);
  }

  private int numberCart = 1;

  private GenreHandler genreHandler = new GenreHandler();
  private List<Genre> theGenres = genreHandler.getAll();
  private BorrowBookHandler borrowHandler = new BorrowBookHandler();
  
  @Override
  public void initializeComponent() {
	  /*
	   * Table Book Available
	   */
	  Vector<Object> tHeader = new Vector<>();
	    tHeader.add("Id");
	    tHeader.add("Genre");
	    tHeader.add("Title");
	    tHeader.add("ISBN");
	    tHeader.add("Quantity");
	    
	    Vector<Vector<Object>> tRows = new Vector<>();
	    
	    List<Book> theBooks = borrowHandler.getAvailableBook();
	    
	    Vector<Object> forEachRow;
	    for (Book b : theBooks) {
	    	forEachRow = new Vector<>();
			forEachRow.add(b.getId());
			String genreType = showRoleName(b);
			forEachRow.add(genreType);
			forEachRow.add(b.getTitle());
			forEachRow.add(b.getIsbn());
			forEachRow.add(b.getQuantity());
			tRows.add(forEachRow);
		}
    
    table = new Table(tHeader, tRows);
    
    /*
     * Table Cart Storage
     */
    Vector<Object> tHeaderCart = new Vector<>();
    	tHeaderCart.add("Cart");
    	tHeaderCart.add("Id");
    	tHeaderCart.add("Genre");
    	tHeaderCart.add("Title");
    	tHeaderCart.add("ISBN");
    	tHeaderCart.add("Quantity");
    
    Vector<Vector<Object>> tRowsCart = new Vector<>();
    
    BorrowBookHandler borrowHandlerCart = new BorrowBookHandler();
    List<Book> theCarts = borrowHandlerCart.getCart();
    
    for (Book e : theCarts) {
    	Vector<Object> forEachRowCart = addRow(e, numberCart);
		tRowsCart.add(forEachRowCart);
	}

    tableCart = new Table(tHeaderCart, tRowsCart);
    

    title = new LabelTitle("Borrow Book");

    tabbedPane = new JTabbedPane();

    /**
     * Initialize Component for Insert Form
     */
    lblInsertName = new JLabel("Book Name");
    lblSelectInsertName = new JLabel("Please Choose Book");

    btnInsert = new JButton("Add to Cart");
    btnInsert.setEnabled(Boolean.FALSE);

    Component[][] insert = {
      new Component[] { lblInsertName  },
      new Component[] { lblSelectInsertName },
    };

    panelAdd = new PanelForm(insert, btnInsert, new Dimension(350, 350));

    /**
     * Initialize Component for Update Form
     */
    lblRemoveName = new JLabel("Book Name");
    lblSelectRemoveName = new JLabel("Please Choose Book Cart");
    
    btnRemove = new JButton("Remove Cart");
    btnRemove.setEnabled(Boolean.FALSE);

    Component[][] remove = {
      new Component[] { lblRemoveName },
      new Component[] { lblSelectRemoveName },
    };

    panelRemove = new PanelForm(remove, btnRemove, new Dimension(350, 350));

    /**
     * Initialize Component for Delete Form
     */
    btnBorrowBook = new JButton("Borrow book");
    close = new ButtonInternalClose();
  }

  private Vector<Object> addRow(Book book,int numberCart) {
		Vector<Object> forEachRowCart = new Vector<Object>();
		forEachRowCart.add(numberCart++);
		forEachRowCart.add(book.getId());
		String genreType = showRoleName(book);
		forEachRowCart.add(genreType);
		forEachRowCart.add(book.getTitle());
		forEachRowCart.add(book.getIsbn());
		forEachRowCart.add(book.getQuantity());
		return forEachRowCart;
	}
  
  public String showRoleName(Book b) {
		String genreType = "";
		for (Genre genre : theGenres) {
			if(b.getGenre_id().equals(genre.getId())) {
				genreType = genre.getType();
				break;
			}
		}
		return genreType;
	}
  
  @Override
  public void addComponent() {
    tabbedPane.add("Add to Cart", panelAdd.getPanel());
    tabbedPane.add("Remove Cart", panelRemove.getPanel());

    JPanel pnlCenter = new JPanel(new BorderLayout(8, 8));
    pnlCenter.add(table.getScrollPane(), BorderLayout.WEST);
    pnlCenter.add(tableCart.getScrollPane(), BorderLayout.CENTER);
    pnlCenter.add(tabbedPane, BorderLayout.EAST);
    
    JPanel pnlSouth = new JPanel(new BorderLayout(4, 4));
    pnlSouth.add(btnBorrowBook, BorderLayout.NORTH);
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
          
          lblSelectInsertName.setText(table.getValueAt(row,3));
          btnInsert.setEnabled(true);
          
        }
      }
    );

    tableCart.addMouseListener(
    	      new MouseInputAdapter() {

    	        @Override
    	        public void mouseClicked(MouseEvent e) {
    	          super.mouseClicked(e);
    	          
    	          int row = tableCart.getSelectedRow();
    	          
    	          lblSelectRemoveName.setText(tableCart.getValueAt(row,4));
    	          btnRemove.setEnabled(true);
    	        }
    	      }
    	    );
    
    btnInsert.addActionListener(
      new AbstractAction() {

        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	int result = Message.confirm("Are you sure want to add this book to cart ?", "Add To Cart");
        	if(result==JOptionPane.YES_OPTION) {
        		BookHandler bH = new BookHandler();
        		Book b = bH.getByIsbn(lblSelectInsertName.getText());        		
        		if(borrowHandler.addToCart(b)) {
            		b = bH.getByIsbn(lblSelectInsertName.getText());   
        			int row = searchISBN(b.getIsbn());
        			if(row >=0) {
        				String genreType = showRoleName(b);
        				table.updateRow(row,b.getId(),genreType,b.getTitle(),b.getIsbn(),b.getQuantity().toString());
        				tableCart.addNewRow(addRow(b,numberCart));
        				Message.success("Success add book to cart !");        				
        			}
        		}else {
        			Message.error("Error put book to cart !");
        		}
        		refreshForm();
        	}
        }
      }
    );

    btnRemove.addActionListener(
      new AbstractAction() {

        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	int result = Message.confirm("Are you sure want to borrow all the book from cart ?", "Borrow From Cart");
        	if(result==JOptionPane.YES_OPTION) {
        		BookHandler bH = new BookHandler();
        		Book b = bH.getByIsbn(lblSelectRemoveName.getText());
        		if(borrowHandler.removeCart(b)) {
            		b = bH.getByIsbn(lblSelectRemoveName.getText()); 
        			int row = searchISBN(b.getIsbn());
        			if(row >=0) {
        				String genreType = showRoleName(b);
        				table.updateRow(row,b.getId(),genreType,b.getTitle(),b.getIsbn(),b.getQuantity().toString());
        				tableCart.removeRow(tableCart.getSelectedRow());
        				Message.success("Success remove book from cart !");
        			}
        		}else {
        			Message.error("Error remove book from cart !");
        		}
        		refreshForm();
        	}

        }
      }
    );

    btnBorrowBook.addActionListener(
      new AbstractAction() {

        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
    		if(borrowHandler.borrowBook()) {
    			tableCart.removeAll();
    			Message.success("Borrow book success !");
    		}
    		refreshForm();
        }
      }
    );

    close.addListener(this);
  }
  
  private int searchISBN(String isbn) {
	  int column = 3;
	  for (int  row = 0;  row < table.getRowCount(); row++) {
		if(table.getValueAt(row, column).equals(isbn)) {
			return row;
		}
	  }
	  return -1;
  }
  
  private void refreshForm() {
	  	lblSelectInsertName.setText("Please Choose Book");
	  	btnInsert.setEnabled(false);
	  	
		lblSelectRemoveName.setText("Please Choose Book Cart");
		btnRemove.setEnabled(false);
  }
  
}