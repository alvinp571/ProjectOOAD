package views;

import java.awt.BorderLayout;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.LabelTitle;
import controllers.GenreHandler;
import views.base.BaseView;

public class PurchasingView extends BaseView{
	private static final long serialVersionUID = 1L;

	  private LabelTitle title;
	  private JButton btnViewGenre, btnRestockBook,btnViewBook,btnDeleteBook;

	  public PurchasingView() {
	    super("Purchasing staff Form", 350, 225);
	  }

	  @Override
	  public void initializeComponent() {
	    title = new LabelTitle("Purchasing staff Form");
	    btnViewGenre = new JButton("View Genre");
	    btnRestockBook = new JButton("Restock Book");
	    btnViewBook = new JButton("View Book");
	    btnDeleteBook = new JButton("Delete Book");
	    
	  }

	  @Override
	  public void addComponent() {
//	    JPanel pnlFormLabel = new JPanel(new GridLayout(2, 1, 8, 8));
//	    pnlFormLabel.add(lblUsername);
//	    pnlFormLabel.add(lblPassword);

//	    JPanel pnlFormInput = new JPanel(new GridLayout(2, 1, 8, 8));
//	    pnlFormInput.add(txtUsername);
//	    pnlFormInput.add(txtPassword);
//
//	    JPanel pnlForm = new JPanel(new BorderLayout(8, 8));
//	    pnlForm.add(pnlFormLabel, BorderLayout.WEST);
//	    pnlForm.add(pnlFormInput, BorderLayout.CENTER);

	    JPanel pnlButton = new JPanel(new GridLayout(4, 1, 8, 8));
	    pnlButton.add(btnViewGenre);
	    pnlButton.add(btnRestockBook);
	    pnlButton.add(btnViewBook);
	    pnlButton.add(btnDeleteBook);
	    

	    JPanel panel = new JPanel(new BorderLayout(8, 8));
	    panel.add(title.getLabel(), BorderLayout.NORTH);
//	    panel.add(pnlForm, BorderLayout.CENTER);
	    panel.add(pnlButton, BorderLayout.SOUTH);
	    panel.setBorder(new EmptyBorder(10, 10, 10, 10));

	    add(panel);
	  }

	@Override
	public void addListener() {
		btnViewGenre.addActionListener(
				new AbstractAction() {
					private static final long serialVersionUID = 1L;
					
					@Override
					public void actionPerformed(ActionEvent e) {
						new GenreHandler().showGenre().showForm();
					}
				}
				
			);
		
	}
}
