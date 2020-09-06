package views;

import components.ButtonInternalClose;
import components.LabelTitle;
import components.PanelForm;
import components.Table;
import controllers.BookHandler;
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
public final class BorrowBook extends BaseInternalView {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private LabelTitle title;
  private Table table;
  private JTabbedPane tabbedPane;
  private PanelForm panelAdd, panelAccept, panelFired;
  private JLabel lblInsertCode, lblInsertName, lblInsertCredit;
  private JLabel lblUpdateCode, lblUpdateName, lblUpdateCredit;
  private JLabel lblDeleteCode;
  private JLabel lblSelectUpdateCode, lblSelectDeleteCode;
  private JTextField txtInsertCode, txtInsertName;
  private JTextField txtUpdateName;
  private JComboBox<String> cbInsertCredit;
  private JComboBox<String> cbUpdateCredit;
  private JButton btnInsert, btnUpdate, btnDelete, btnFiredEmployee;
  private ButtonInternalClose close;

  public BorrowBook() {
    super("Borrow Book", 1000, 350);
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

    title = new LabelTitle("Borrow Book");

    tabbedPane = new JTabbedPane();

    /**
     * Initialize Component for Insert Form
     */
    
    lblInsertCode = new JLabel("Course Code");
    lblInsertName = new JLabel("Course Name");
    lblInsertCredit = new JLabel("Course Credit");
    txtInsertCode = new JTextField();
    txtInsertName = new JTextField();
    cbInsertCredit =
      new JComboBox<>(
        new String[] {
          "Choose Course Credit",
          "1",
          "2",
          "4",
          "5",
          "2/1",
          "2/2",
          "2/4",
          "4/2",
        }
      );
    btnInsert = new JButton("Insert");

    Component[][] insert = {
      new Component[] { lblInsertCode, lblInsertName, lblInsertCredit },
      new Component[] { txtInsertCode, txtInsertName, cbInsertCredit },
    };

    panelAdd = new PanelForm(insert, btnInsert, new Dimension(350, 350));

    /**
     * Initialize Component for Update Form
     */

    lblUpdateCode = new JLabel("Course Code");
    lblUpdateName = new JLabel("Course Name");
    lblUpdateCredit = new JLabel("Course Credit");
    lblSelectUpdateCode = new JLabel("Please Choose Course Code");
    txtUpdateName = new JTextField();
    txtUpdateName.setEnabled(Boolean.FALSE);
    cbUpdateCredit =
      new JComboBox<>(
        new String[] {
          "Choose Course Credit",
          "1",
          "2",
          "4",
          "5",
          "2/1",
          "2/2",
          "2/4",
          "4/2",
        }
      );
    cbUpdateCredit.setEnabled(Boolean.FALSE);
    btnUpdate = new JButton("Update");
    btnUpdate.setEnabled(Boolean.FALSE);

    Component[][] update = {
      new Component[] { lblUpdateCode, lblUpdateName, lblUpdateCredit },
      new Component[] { lblSelectUpdateCode, txtUpdateName, cbUpdateCredit },
    };

    panelAccept = new PanelForm(update, btnUpdate, new Dimension(350, 350));

    /**
     * Initialize Component for Delete Form
     */

    lblDeleteCode = new JLabel("Course Code");
    lblSelectDeleteCode = new JLabel("Please Choose Course Code");
    btnDelete = new JButton("Delete");
    btnDelete.setEnabled(Boolean.FALSE);

    Component[][] delete = {
      new Component[] { lblDeleteCode },
      new Component[] { lblSelectDeleteCode },
    };

    panelFired = new PanelForm(delete, btnDelete, new Dimension(350, 350));
    
//    btnFiredEmployee = new JButton("Fired Employee");
    close = new ButtonInternalClose();
  }

  @Override
  public void addComponent() {
    tabbedPane.add("Remove Cart", panelAdd.getPanel());
//    tabbedPane.add("Accept Employee", panelAccept.getPanel());
//    tabbedPane.add("Fired Employee", panelFired.getPanel());

    JPanel pnlCenter = new JPanel(new BorderLayout(8, 8));
    pnlCenter.add(table.getScrollPane(), BorderLayout.CENTER);
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
          // TODO Auto-generated method stub

        }
      }
    );

    btnUpdate.addActionListener(
      new AbstractAction() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
          // TODO Auto-generated method stub

        }
      }
    );

    btnDelete.addActionListener(
      new AbstractAction() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
          // TODO Auto-generated method stub

        }
      }
    );

    close.addListener(this);
  }
}