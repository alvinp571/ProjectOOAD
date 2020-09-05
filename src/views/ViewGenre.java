package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import components.LabelTitle;
import controllers.GenreHandler;
import models.Genre;
import views.base.BaseView;

public class ViewGenre extends BaseView{
	private static final long serialVersionUID = 1L;

	  private LabelTitle title;
	  private JButton btnCreateGenre,btnClose;
	  private JTextArea txtShowEmployee1,txtShowEmployee2;

	  public ViewGenre() {
	    super("Genre List", 350, 225);
	  }

	  @Override
	  public void initializeComponent() {
	    title = new LabelTitle("View Genre");
	    btnCreateGenre = new JButton("Create Genre");
	    btnClose = new JButton("Close");
	    txtShowEmployee1 = new JTextArea("");
	    txtShowEmployee2 = new JTextArea("");
	    
	    GenreHandler genreHandler = new GenreHandler();
	    List <Genre> theGenres = genreHandler.getAll();
	    String id = "";
	    String type = "";
	    for (Genre genre : theGenres) {
			id = id + genre.getId() + "\n";
			type = type + genre.getType() + "\n";
		}
	    
	    txtShowEmployee1.setText(id);
	    txtShowEmployee2.setText(type);;
	   
	  }

	  @Override
	  public void addComponent() {
	    
	    JPanel pnlFormInput1 = new JPanel(new BorderLayout(4,4));
	    pnlFormInput1.add(txtShowEmployee1,BorderLayout.WEST);
	    pnlFormInput1.add(txtShowEmployee2,BorderLayout.CENTER);

	    JPanel pnlButton = new JPanel(new GridLayout(2, 1, 8, 8));
//	    pnlButton.add(btnLogin);
	    pnlButton.add(btnCreateGenre);
	    pnlButton.add(btnClose);

	    JPanel panel = new JPanel(new BorderLayout(8, 8));
	    panel.add(title.getLabel(), BorderLayout.NORTH);
	    panel.add(pnlFormInput1, BorderLayout.CENTER);
	    panel.add(pnlButton, BorderLayout.SOUTH);
	    panel.setBorder(new EmptyBorder(10, 10, 10, 10));

	    add(panel);
	  }

	  @Override
	  public void addListener() {
	    btnCreateGenre.addActionListener(
	      new AbstractAction() {
	        /**
	         *
	         */
	        private static final long serialVersionUID = 1L;

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	new GenreHandler().showCreateGenre().showForm();
	        }
	      }
	    );
	    btnClose.addActionListener(
	  	      new AbstractAction() {
	  	        /**
	  	         *
	  	         */
	  	        private static final long serialVersionUID = 1L;

	  	        @Override
	  	        public void actionPerformed(ActionEvent e) {
	  	        	dispose();
	  	        }
	  	      }
	  	    );
	}
}
