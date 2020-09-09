package views;

import components.ButtonInternalClose;
import components.LabelTitle;
import components.Message;
import components.PanelForm;
import components.Table;
import controllers.BookHandler;
import controllers.BorrowBookHandler;
import controllers.EmployeeHandler;
import models.Book;
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
import javax.swing.JOptionPane;
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
public final class BorrowBookForm extends BaseInternalView {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private LabelTitle title;
  private Table table, tableCart;
  private JTabbedPane tabbedPane;
  private PanelForm panelAdd, panelRemove, panelBorrow;
  private JLabel lblInsertName, lblSelectInsertName;
  private JLabel lblRemoveName, lblSelectRemoveName;
  private JLabel lblDeleteCode;
  private JLabel lblSelectUpdateCode, lblSelectDeleteCode;
  private JTextField txtInsertCode, txtInsertName;
  private JTextField txtUpdateName;
  private JComboBox<String> cbInsertCredit;
  private JComboBox<String> cbUpdateCredit;
  private JButton btnInsert, btnRemove, btnBorrowBook;
  private ButtonInternalClose close;

  public BorrowBookForm() {
    super("Borrow Book", 1300, 350);
  }

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
	    
	    BorrowBookHandler borrowHandler = new BorrowBookHandler();
	    List<Book> theBooks = borrowHandler.getAvailableBook();
	    
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
    List<Book> theCarts = borrowHandlerCart.getAvailableBook();
    
    int numberCart = 1;
    Vector<Object> forEachRowCart;
    for (Book e : theCarts) {
    	forEachRowCart = new Vector<>();
    	forEachRowCart.add(numberCart++);
		forEachRowCart.add(e.getId());
		forEachRowCart.add(e.getGenre_id());
		forEachRowCart.add(e.getTitle());
		forEachRowCart.add(e.getIsbn());
		forEachRowCart.add(e.getQuantity());
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
      new Component[] { lblInsertName,  },
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

//    lblBorrowBook = new JLabel("Course Code");
//    lblSelectBorrowBook = new JLabel("Please Choose Course Code");
    
    btnBorrowBook = new JButton("Borrow Book");
    btnBorrowBook.setEnabled(Boolean.FALSE);
    
    Component[][] borrow = {
    	      new Component[] {  },
    	      new Component[] {  },
    	 };

    panelBorrow = new PanelForm(borrow, btnBorrowBook, new Dimension(350, 350));
    
//    btnFiredEmployee = new JButton("Fired Employee");
    close = new ButtonInternalClose();
  }

  @Override
  public void addComponent() {
    tabbedPane.add("Add to Cart", panelAdd.getPanel());
    tabbedPane.add("Remove Cart", panelRemove.getPanel());
    tabbedPane.add("Borrow Book Cart", panelBorrow.getPanel());

    JPanel pnlCenter = new JPanel(new BorderLayout(8, 8));
    pnlCenter.add(table.getScrollPane(), BorderLayout.WEST);
    pnlCenter.add(tableCart.getScrollPane(), BorderLayout.CENTER);
    pnlCenter.add(tabbedPane, BorderLayout.EAST);
    
    JPanel pnlSouth = new JPanel(new BorderLayout(4, 4));
//    pnlSouth.add(btnFiredEmployee, BorderLayout.NORTH);
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
          
          lblSelectInsertName.setText(table.getValueAt(row,0));
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
    	          
    	          lblSelectRemoveName.setText(tableCart.getValueAt(row,1));
    	          btnRemove.setEnabled(true);
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
        	int result = Message.confirm("Are you sure want to add this book to cart ?", "Add To Cart");
        	if(result==JOptionPane.YES_OPTION) {
        		refreshForm();
        	}
        }
      }
    );

    btnRemove.addActionListener(
      new AbstractAction() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	int result = Message.confirm("Are you sure want to remove this book from cart ?", "Remove From Cart");
        	if(result==JOptionPane.YES_OPTION) {
        		refreshForm();
        	}

        }
      }
    );

    btnBorrowBook.addActionListener(
      new AbstractAction() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {

        }
      }
    );

    close.addListener(this);
  }
  
  private void refreshForm() {
	  	lblSelectInsertName.setText("Please Choose Book");
	  	btnInsert.setEnabled(false);
	  	
		lblSelectRemoveName.setText("Please Choose Book Cart");
		btnRemove.setEnabled(false);
  }
}