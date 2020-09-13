//package views;
//
//import java.awt.BorderLayout;
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
//import controllers.GenreHandler;
//import models.Genre;
//import views.base.BaseView;
//
//public class ViewGenre extends BaseView{
//	private static final long serialVersionUID = 1L;
//
//	  private LabelTitle title;
//	  private JButton btnCreateGenre,btnClose;
//	  private JTextArea txtShowEmployee1,txtShowEmployee2;
//
//	  public ViewGenre() {
//	    super("Genre List", 350, 225);
//	  }
//
//	  @Override
//	  public void initializeComponent() {
//	    title = new LabelTitle("View Genre");
//	    btnCreateGenre = new JButton("Create Genre");
//	    btnClose = new JButton("Close");
//	    txtShowEmployee1 = new JTextArea("");
//	    txtShowEmployee2 = new JTextArea("");
//	    
//	    GenreHandler genreHandler = new GenreHandler();
//	    List <Genre> theGenres = genreHandler.getAll();
//	    String id = "";
//	    String type = "";
//	    for (Genre genre : theGenres) {
//			id = id + genre.getId() + "\n";
//			type = type + genre.getType() + "\n";
//		}
//	    
//	    txtShowEmployee1.setText(id);
//	    txtShowEmployee2.setText(type);;
//	   
//	  }
//
//	  @Override
//	  public void addComponent() {
//	    
//	    JPanel pnlFormInput1 = new JPanel(new BorderLayout(4,4));
//	    pnlFormInput1.add(txtShowEmployee1,BorderLayout.WEST);
//	    pnlFormInput1.add(txtShowEmployee2,BorderLayout.CENTER);
//
//	    JPanel pnlButton = new JPanel(new GridLayout(2, 1, 8, 8));
////	    pnlButton.add(btnLogin);
//	    pnlButton.add(btnCreateGenre);
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
import components.Message;
import components.PanelForm;
import components.Table;
import controllers.GenreHandler;
import models.Genre;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.JButton;
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
public final class ViewGenre extends BaseInternalView {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private LabelTitle title;
  private Table table;
  private JTabbedPane tabbedPane;
  private PanelForm panelAdd;
  private JLabel lblInsertType;
  private JTextField txtInsertType;
  private JButton btnInsert;
  private ButtonInternalClose close;

  public ViewGenre() {
    super("View Genre", 1000, 350);
  }

  @Override
  public void initializeComponent() {
    Vector<Object> tHeader = new Vector<>();
    tHeader.add("Id");
    tHeader.add("Type");
    
    Vector<Vector<Object>> tRows = new Vector<>();
    
    GenreHandler genreHandler = new GenreHandler();
    List<Genre> theGenre = genreHandler.getAll();
    
    for (Genre g : theGenre) {
    	Vector<Object> forEachRow = addRow(g);
		tRows.add(forEachRow);
	}
    
    table = new Table(tHeader, tRows);

    title = new LabelTitle("Genres");

    tabbedPane = new JTabbedPane();

    /**
     * Initialize Component for Insert Form
     */
    
    lblInsertType = new JLabel("Genre Type");
    txtInsertType = new JTextField();
    btnInsert = new JButton("Insert");

    Component[][] insert = {
      new Component[] { lblInsertType },
      new Component[] { txtInsertType },
    };

    
    panelAdd = new PanelForm(insert, btnInsert, new Dimension(350, 350));

    close = new ButtonInternalClose();
  }

private Vector<Object> addRow(Genre g) {
	Vector<Object> forEachRow = new Vector<>();
	forEachRow.add(g.getId());
	forEachRow.add(g.getType());
	return forEachRow;
}

  @Override
  public void addComponent() {
    tabbedPane.add("Add Genre", panelAdd.getPanel());

    JPanel pnlCenter = new JPanel(new BorderLayout(8, 8));
    pnlCenter.add(table.getScrollPane(), BorderLayout.CENTER);
    pnlCenter.add(tabbedPane, BorderLayout.EAST);

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

    btnInsert.addActionListener(
      new AbstractAction() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	HashMap<String,String> inputs = new HashMap<String, String>();
        	inputs.put("type",txtInsertType.getText());
        	
        	GenreHandler genreHandler = new GenreHandler();
        	Genre g = genreHandler.insert(inputs);
        	if(g!=null) {
        		table.addNewRow(addRow(g));
        		txtInsertType.setText("");
        		Message.success("Success insert a new Genre !");
        	}
        }
      }
    );
    
    close.addListener(this);
  }
}