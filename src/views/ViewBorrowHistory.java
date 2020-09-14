package views;

import components.ButtonInternalClose;



import components.LabelTitle;
import components.Message;
import components.PanelForm;
import components.Table;
import controllers.BookHandler;
import controllers.BorrowTransactionHandler;
import helper.Session;
import models.Book;
import models.Borrow;
import models.BorrowItem;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;


import views.base.BaseInternalView;

public final class ViewBorrowHistory extends BaseInternalView {

  private static final long serialVersionUID = 1L;

  private LabelTitle title;
  private Table table,tableBookDetail;
  private JTabbedPane tabbedPane;
  private PanelForm panelFilter, panelBorrowItem, panelReturnBook;
  private JLabel lblFilterMonth, lblFilterYear, lblViewDetail, lblReturnBook;
  private JLabel lblSelectViewDetail, lblSelectReturnBook;
  private JTextField txtFilterMonth, txtFilterYear;
  private JButton btnFilter, btnBorrowItem, btnReturnBook;
  private ButtonInternalClose close;
  private JOptionPane PayFine;

  public ViewBorrowHistory() {
    super("View Borrow History", 1300, 350);
  }

  private BorrowTransactionHandler bTH = new BorrowTransactionHandler();
  private BookHandler bH = new BookHandler();
  
  @Override
  public void initializeComponent() {
	  Vector<Object> tHeader = new Vector<>();
	    tHeader.add("Borrow Id");
	    tHeader.add("Member Id");
	    tHeader.add("Status");
	    
	    Vector<Vector<Object>> tRows = new Vector<>();
	    
	    Date date = null;
	    List<Borrow> theBorrows = bTH.getAcceptStatus(date);
	    
	    for (Borrow b : theBorrows) {
	    	Vector<Object> forEachRow = new Vector<>();
			forEachRow.add(b.getId());
			forEachRow.add(b.getMemberId());
			forEachRow.add(b.getStatus());
			tRows.add(forEachRow);
		}
    
    table = new Table(tHeader, tRows);
    
    Vector<Object> tHeaderDetail = new Vector<>();
    tHeaderDetail.add("Borrow Id");
    tHeaderDetail.add("Book Id");

    Vector<Vector<Object>> tRowsDetail = new Vector<>();

    tableBookDetail = new Table(tHeaderDetail, tRowsDetail);

    title = new LabelTitle("Borrow Book History");

    tabbedPane = new JTabbedPane();
    
    lblFilterMonth = new JLabel("Month");
    lblFilterYear = new JLabel("Year");
    txtFilterMonth = new JTextField();
    txtFilterYear = new JTextField();
    
    btnFilter = new JButton("Filter By Month and Year");
    btnFilter.setEnabled(Boolean.TRUE);
    
    Component[][] filter = {
          new Component[] { lblFilterMonth, lblFilterYear },
          new Component[] { txtFilterMonth, txtFilterYear },
    };
    
    
    panelFilter = new PanelForm(filter, btnFilter, new Dimension(350, 350));
    
    lblViewDetail = new JLabel("Transaction ID");
    lblSelectViewDetail = new JLabel("Please Choose Transaction ID");
    
    btnBorrowItem = new JButton("View Borrow Item");
    btnBorrowItem.setEnabled(Boolean.FALSE);
    
    Component[][] borrowItem = {
    		new Component[] { lblViewDetail },
    		new Component[] { lblSelectViewDetail },
    };
    
    panelBorrowItem = new PanelForm(borrowItem, btnBorrowItem, new Dimension(350, 350));
    
    lblReturnBook = new JLabel("Borrow Book Item");
    lblSelectReturnBook = new JLabel("Please Choose Borrow Book Item");
    
    btnReturnBook = new JButton("Return Book");
    btnReturnBook.setEnabled(Boolean.FALSE);
    
    Component[][] returnbook = {
          new Component[] { lblReturnBook },
          new Component[] { lblSelectReturnBook },
    };
    
    panelReturnBook = new PanelForm(returnbook, btnReturnBook, new Dimension(350, 350));
    
    close = new ButtonInternalClose();
  }

  @Override
  public void addComponent() {
	 tabbedPane.add("View Book Item", panelBorrowItem.getPanel());
	 tabbedPane.add("Filter By Month & Year", panelFilter.getPanel());
	 if(Session.showRoleName().equals("Membership")) {
	  tabbedPane.add("Return Book", panelReturnBook.getPanel());
	 }

    JPanel pnlCenter = new JPanel(new BorderLayout(8, 8));
    pnlCenter.add(table.getScrollPane(), BorderLayout.WEST);
    pnlCenter.add(tableBookDetail.getScrollPane(), BorderLayout.CENTER);
    pnlCenter.add(tabbedPane, BorderLayout.EAST);
    
    JPanel pnlSouth = new JPanel(new BorderLayout(4, 4));
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
          
          lblSelectViewDetail.setText(table.getValueAt(row,0));
          btnBorrowItem.setEnabled(true);
        }
      }
    );

    tableBookDetail.addMouseListener(
    	      new MouseInputAdapter() {

    	        @Override
    	        public void mouseClicked(MouseEvent e) {
    	          super.mouseClicked(e);
    	          
    	          int row = tableBookDetail.getSelectedRow();
    	          
    	          lblSelectReturnBook.setText(tableBookDetail.getValueAt(row,1));
    	          btnReturnBook.setEnabled(true);
    	        }
    	      }
    	    );
    
    btnFilter.addActionListener(
      new AbstractAction() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	int result = Message.confirm("Are you sure want to filter transaction by this month and year?", "Filter Transaction");
        	if(result==JOptionPane.YES_OPTION) {
        		String year = txtFilterYear.getText();
        		String month = txtFilterMonth.getText();
        		boolean validMnY = validateFilter(month, year);

        		if (validMnY) {
        			Integer month1 = Integer.parseInt(month);
        			System.out.println("Now: "+ month+ ","+year);
        			String all = "";
        			if(month1<10) {
        				all = "01" + "-0" + month + "-" + year + " 00:00:00";
        			}else {
        				all = "01" + "-" + month + "-" + year + " 00:00:00";
        			}
        			SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        			Date date = null;
        			try {
        				date = SDF.parse(all);
        			} catch (java.text.ParseException e2) {
        				e2.printStackTrace();
        			}
        			
        			table.removeAll();
        			
        			List<Borrow> theBorrows2 = bTH.getAcceptStatus(date);
        			for (Borrow b : theBorrows2) {
        				Vector<Object> forEachRow = new Vector<>();
        				forEachRow.add(b.getId());
        				forEachRow.add(b.getMemberId());
        				forEachRow.add(b.getStatus());
        				table.addNewRow(forEachRow);
        			}
        			
        			refreshForm();
        		}
        	}
        }
      }
    );
    
    btnBorrowItem.addActionListener(
    	      new AbstractAction() {
    	        /**
    	         *
    	         */
    	        private static final long serialVersionUID = 1L;

    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	        	//reset table
	        		tableBookDetail.removeAll();
	        		
	        		List<BorrowItem> theBorrowItems = bTH.getBookItem(lblSelectViewDetail.getText());
	        	    
	        	    for (BorrowItem b : theBorrowItems) {
	        	    	Vector<Object> forEachRow = new Vector<>();
	        			forEachRow.add(b.getBorrow_id());
	        			forEachRow.add(b.getBook_id());
	        			tableBookDetail.addNewRow(forEachRow);
	        		}	
    	        	
    	        	refreshForm();
    	        }
    	      }
    	    );
    
    btnReturnBook.addActionListener(
    	      new AbstractAction() {
    	        /**
    	         *
    	         */
    	        private static final long serialVersionUID = 1L;

    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	        	int result = Message.confirm("Are you sure want to return this book?", "Return Book");
    	        	if(result==JOptionPane.YES_OPTION) {
    	        		int row = tableBookDetail.getSelectedRow();
    	        		
    	        		HashMap<String, String> inputs = new HashMap<String, String>();
    	        		inputs.put("borrow_id", tableBookDetail.getValueAt(row, 0));
    	        		inputs.put("book_id", lblSelectReturnBook.getText());
    	        		
    	        		BorrowItem bi = bTH.returnBook(inputs);
    	        		
    	        		if (bi == null) {
    	        			Message.error("Book Already Return");
    	        		}
    	        		else {
    	        			Book book = new Book();
    	        			book = bH.getById(bi.getBook_id());
    	        			
    	        			HashMap<String, String> updateBook = new HashMap<String, String>();
    	        			updateBook.put("id", book.getId());
    	        			updateBook.put("genre_id", book.getGenre_id());
    	        			updateBook.put("title", book.getTitle());
    	        			updateBook.put("isbn", book.getIsbn());
    	        			updateBook.put("quantity", "1");
    	        			
    	        			book = bH.update(updateBook);
    	        			
    	        			//calculate fine
    	        			Long fine = bTH.calculateFine(bi);
    	        			
    	        			if (fine > 0) {
    	        				while(true) {
    	        					String inputMoneyString = PayFine.showInputDialog("You need to pay fine : Rp. " + fine);
    	        					long inputMoney = Long.parseLong(inputMoneyString);
    	        						
    	        					long validateMoney = fine - inputMoney;
    	        					
    	        					// validate
    	        					// money >= fine
    	        					if (validateMoney <= 0) {
    	        						Message.success("Thanks for paying fine, Total Change : Rp. " + (-validateMoney));
    	        						break;
    	        					}
    	        					// money < fine
    	        					else {
    	        						Message.error("Money is not enough!");
    	        					}
    	        				}
    	        			}
    	        			else {
    	        				Message.success("Book returned, no fine");
    	        			}    	        			
    	        		}
    	        		
    	        		
    	        	}
    	        }
    	      }
    	    );

    close.addListener(this);
  }
  	
  	private boolean validateFilter(String month, String year) {
  		if (month.equals("") || year.equals("")) {
  			Message.error("All fields must be filled");
  			return false;
  		}
  		else {
  			Integer monthInt = Integer.parseInt(month);
  			
  			if (monthInt < 1 || monthInt > 12) {
  				Message.error("Month must between 1 - 12");
  				return false;
  			}
  			else if (year.length() != 4) {
  				Message.error("Year must between 1000 - 9999");
  				return false;
  			}
  			
  			Message.success("Filter Success");
  			return true;
  		}
  	}
  	
  	private void refreshForm() {
		/*
		 * This method is self-explanatory
		 * Refresh means reset the form to its initial condition
		 */
		
	  	lblSelectReturnBook.setText("Please Choose Transaction ID");
	  	btnReturnBook.setEnabled(false);
	  
		lblSelectViewDetail.setText("Please Choose Transaction ID");
		btnBorrowItem.setEnabled(false);

		txtFilterMonth.setText("");
		txtFilterYear.setText("");
	}
}