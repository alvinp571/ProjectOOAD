package views;

import components.ButtonInternalClose;
import components.LabelTitle;
import components.PanelForm;
import components.Table;
import controllers.BookHandler;
import controllers.BorrowBookHandler;
import controllers.BorrowTransactionHandler;
import controllers.EmployeeHandler;
import helper.Session;
import models.Book;
import models.Borrow;
import models.BorrowItem;
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
public final class ViewPendingBorrowBookAdmin extends BaseInternalView {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private LabelTitle title;
  private Table table,tableBookDetail;
  private JTabbedPane tabbedPane;
  private PanelForm panelAccept;
  private JLabel lblAcceptID;
  private JLabel lblSelectAcceptID;
  private JButton btnAccept;
  private ButtonInternalClose close;

  public ViewPendingBorrowBookAdmin() {
    super("View Pending Borrow Book", 1000, 350);
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
    
//    Vector<Object> tHeaderDetail = new Vector<>();
//    tHeader.add("Borrow Id");
//    tHeader.add("Book Id");
//    
//    Vector<Vector<Object>> tRowsDetail = new Vector<>();
//    List<Borrow> theBorrowItems = bTH.getBookItem(id);
    
//    for (Borrow b : theBorrows) {
//    	Vector<Object> forEachRow = new Vector<>();
//		forEachRow.add(b.getId());
//		forEachRow.add(b.getMemberId());
//		forEachRow.add(b.getStatus());
//		tRows.add(forEachRow);
//	}

    tableBookDetail = new Table(tHeader, tRows);

    title = new LabelTitle("Pending Borrow Book");

    tabbedPane = new JTabbedPane();

    /**
     * Initialize Component for Insert Form
     */
//    
//    lblInsertCode = new JLabel("Course Code");
//    lblInsertName = new JLabel("Course Name");
//    lblInsertCredit = new JLabel("Course Credit");
//    txtInsertCode = new JTextField();
//    txtInsertName = new JTextField();
//    cbInsertCredit =
//      new JComboBox<>(
//        new String[] {
//          "Choose Course Credit",
//          "1",
//          "2",
//          "4",
//          "5",
//          "2/1",
//          "2/2",
//          "2/4",
//          "4/2",
//        }
//      );
//    btnInsert = new JButton("Insert");
//
//    Component[][] insert = {
//      new Component[] { lblInsertCode, lblInsertName, lblInsertCredit },
//      new Component[] { txtInsertCode, txtInsertName, cbInsertCredit },
//    };
//
//    panelAdd = new PanelForm(insert, btnInsert, new Dimension(350, 350));
//
//    /**
//     * Initialize Component for Update Form
//     */
//
//    lblUpdateCode = new JLabel("Course Code");
//    lblUpdateName = new JLabel("Course Name");
//    lblUpdateCredit = new JLabel("Course Credit");
//    lblSelectUpdateCode = new JLabel("Please Choose Course Code");
//    txtUpdateName = new JTextField();
//    txtUpdateName.setEnabled(Boolean.FALSE);
//    cbUpdateCredit =
//      new JComboBox<>(
//        new String[] {
//          "Choose Course Credit",
//          "1",
//          "2",
//          "4",
//          "5",
//          "2/1",
//          "2/2",
//          "2/4",
//          "4/2",
//        }
//      );
//    cbUpdateCredit.setEnabled(Boolean.FALSE);
//    btnUpdate = new JButton("Update");
//    btnUpdate.setEnabled(Boolean.FALSE);
//
//    Component[][] update = {
//      new Component[] { lblUpdateCode, lblUpdateName, lblUpdateCredit },
//      new Component[] { lblSelectUpdateCode, txtUpdateName, cbUpdateCredit },
//    };
//
//    panelAccept = new PanelForm(update, btnUpdate, new Dimension(350, 350));
//
//    /**
//     * Initialize Component for Delete Form
//     */
//
//    lblDeleteCode = new JLabel("Course Code");
//    lblSelectDeleteCode = new JLabel("Please Choose Course Code");
//    btnDelete = new JButton("Delete");
//    btnDelete.setEnabled(Boolean.FALSE);
//
//    Component[][] delete = {
//      new Component[] { lblDeleteCode },
//      new Component[] { lblSelectDeleteCode },
//    };
//
//    panelFired = new PanelForm(delete, btnDelete, new Dimension(350, 350));
    
    btnAccept = new JButton("Accept Borrow");
    close = new ButtonInternalClose();
  }

  @Override
  public void addComponent() {
	  if(Session.showRoleName().equals("Administrator")) {
		  tabbedPane.add("Accept Pending Request", panelAccept.getPanel());
	  }
//    tabbedPane.add("Accept Employee", panelAccept.getPanel());
//    tabbedPane.add("Fired Employee", panelFired.getPanel());

    JPanel pnlCenter = new JPanel(new BorderLayout(8, 8));
    pnlCenter.add(table.getScrollPane(), BorderLayout.CENTER);
    pnlCenter.add(tabbedPane, BorderLayout.EAST);
    
    JPanel pnlSouth = new JPanel(new BorderLayout(4, 4));
    pnlSouth.add(btnAccept,BorderLayout.NORTH);
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
        	
        }
      }
    );

    close.addListener(this);
  }
}