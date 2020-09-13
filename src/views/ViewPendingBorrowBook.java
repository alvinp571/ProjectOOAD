package views;

import components.ButtonInternalClose;

import components.LabelTitle;
import components.Message;
import components.PanelForm;
import components.Table;
import controllers.BorrowTransactionHandler;
import helper.Session;
import models.Borrow;
import models.BorrowItem;

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

public final class ViewPendingBorrowBook extends BaseInternalView {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private LabelTitle title;
  private Table table,tableBookDetail;
  private JTabbedPane tabbedPane;
  private PanelForm panelAccept, panelBorrowItem;
  private JLabel lblAcceptID, lblBorrowItem;
  private JLabel lblSelectAcceptID, lblSelectBorrowItem;
  private JButton btnAccept, btnBorrowItem;
  private ButtonInternalClose close;

  public ViewPendingBorrowBook() {
    super("View Pending Borrow Book", 1300, 350);
  }

  private BorrowTransactionHandler bTH = new BorrowTransactionHandler();
  
  @Override
  public void initializeComponent() {
	  Vector<Object> tHeader = new Vector<>();
	    tHeader.add("Borrow Id");
	    tHeader.add("Member Id");
	    tHeader.add("Status");
	    
	    Vector<Vector<Object>> tRows = new Vector<>();
	    
	    
	    boolean isOnlyCurrentMember = false;
	    if(Session.showRoleName().equals("Membership")) {
	    	isOnlyCurrentMember = true;
	    }
	    List<Borrow> theBorrows = bTH.getPendingStatus(isOnlyCurrentMember);
	    
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

    title = new LabelTitle("Pending Borrow Book");

    tabbedPane = new JTabbedPane();

    /**
     * Initialize Component for Insert Form
     */

    lblBorrowItem = new JLabel("Transaction ID");
    lblSelectBorrowItem = new JLabel("Please Choose Transaction ID");
    
    btnBorrowItem = new JButton("View Borrow Item");
    btnBorrowItem.setEnabled(Boolean.FALSE);
    
    Component[][] borrowItem = {
          new Component[] { lblBorrowItem },
          new Component[] { lblSelectBorrowItem },
    };
    
    panelBorrowItem = new PanelForm(borrowItem, btnBorrowItem, new Dimension(350, 350));
    
    lblAcceptID = new JLabel("Transaction ID");
    lblSelectAcceptID = new JLabel("Please Choose Transaction ID");
    
    btnAccept = new JButton("Accept Pending Request");
    btnAccept.setEnabled(Boolean.FALSE);
    
    Component[][] accept = {
          new Component[] { lblAcceptID },
          new Component[] { lblSelectAcceptID },
    };
    
    panelAccept = new PanelForm(accept, btnAccept, new Dimension(350, 350));
    
    close = new ButtonInternalClose();
  }

  @Override
  public void addComponent() {
	  if(Session.showRoleName().equals("Administrator")) {
		  tabbedPane.add("Accept Pending Request", panelAccept.getPanel());
	  }
    tabbedPane.add("View Book Item", panelBorrowItem.getPanel());

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
          
          lblSelectAcceptID.setText(table.getValueAt(row,0));
          btnAccept.setEnabled(true);
          
          lblSelectBorrowItem.setText(table.getValueAt(row,0));
          btnBorrowItem.setEnabled(true);
        }
      }
    );

    btnAccept.addActionListener(
      new AbstractAction() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	int result = Message.confirm("Are you sure want to accept this pending request?", "Accept Pending Request");
        	if(result==JOptionPane.YES_OPTION) {
        		boolean acceptValidation = bTH.acceptBorrowRequest(lblSelectAcceptID.getText());
        		
        		if (acceptValidation) {
        			table.removeRow(table.getSelectedRow());
        		} else {
        			Message.error("Error");
        		}
        		refreshForm();
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
	        		
	        		List<BorrowItem> theBorrowItems = bTH.getBookItem(lblSelectBorrowItem.getText());
	        	    
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

    close.addListener(this);
  }
  
  private void refreshForm() {
		/*
		 * This method is self-explanatory
		 * Refresh means reset the form to its initial condition
		 */
		
	  	lblSelectBorrowItem.setText("Please Choose Transaction ID");
	  	btnBorrowItem.setEnabled(false);
	  
		lblSelectAcceptID.setText("Please Choose Transaction ID");
		btnAccept.setEnabled(false);
	}
}