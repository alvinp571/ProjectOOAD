package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import components.LabelTitle;
import controllers.GenreHandler;
import views.base.BaseView;

public class CreateGenre extends BaseView{
	private static final long serialVersionUID = 1L;

	  private LabelTitle title;
	  private JLabel lblName;
	  private JTextField txtName;
	  private JButton btnCreate, btnClose;

	  public CreateGenre() {
	    super("Create Genre Form", 350, 325);
	  }

	  @Override
	  public void initializeComponent() {
	    title = new LabelTitle("Register New Account");
	    lblName = new JLabel("Name");
	    txtName = new JTextField();
	    btnCreate = new JButton("Create New Genre");
	    btnClose = new JButton("Close");
	  }

	  @Override
	  public void addComponent() {
	    JPanel pnlFormLabel = new JPanel(new GridLayout(1, 1, 8, 8));
	    pnlFormLabel.add(lblName);
	    
	    
	    JPanel pnlFormInput = new JPanel(new GridLayout(1, 1, 8, 8));
	    pnlFormInput.add(txtName);
	   
	    JPanel pnlForm = new JPanel(new BorderLayout(8, 8));
	    pnlForm.add(pnlFormLabel, BorderLayout.WEST);
	    pnlForm.add(pnlFormInput, BorderLayout.CENTER);

	    JPanel pnlButton = new JPanel(new GridLayout(2, 1, 8, 8));
	    pnlButton.add(btnCreate);
	    pnlButton.add(btnClose);

	    JPanel panel = new JPanel(new BorderLayout(8, 8));
	    panel.add(title.getLabel(), BorderLayout.NORTH);
	    panel.add(pnlForm, BorderLayout.CENTER);
	    panel.add(pnlButton, BorderLayout.SOUTH);
	    panel.setBorder(new EmptyBorder(10, 10, 10, 10));

	    add(panel);
	  }

	  @Override
	  public void addListener() {
	    btnCreate.addActionListener(
	      new AbstractAction() {

	        private static final long serialVersionUID = 1L;

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	HashMap<String, String> inputs = new HashMap<String, String>();
	            inputs.put("name",txtName.getText());
	            GenreHandler genreHandler = new GenreHandler();
	            if(genreHandler.register(inputs)) {
	            	genreHandler.showGenre().showForm();
	            	dispose();
	            }
	            
	        }
	      }
	    );

	    btnClose.addActionListener(
	      new AbstractAction() {

	        private static final long serialVersionUID = 1L;

	        @Override
	        public void actionPerformed(ActionEvent e) {
	          dispose();
	        }
	      }
	    );
	  }
	  
}
