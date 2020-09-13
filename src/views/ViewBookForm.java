package views;

import components.ButtonInternalClose;

import components.LabelTitle;
import components.Table;
import controllers.BookHandler;
import models.Book;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import views.base.BaseInternalView;

public final class ViewBookForm extends BaseInternalView {
	/**
	 * This class is self-explanatory
	 * There is no any other logic
	 * This class contains only methods for displaying the UI
	 */

	private static final long serialVersionUID = 1L;

	private LabelTitle title;
	private Table table;
	private ButtonInternalClose close;

	public ViewBookForm() {
		/**
		 * This constructor is self-explanatory
		 */
		super("View Book", 1000, 350);
	}

	@Override
	public void initializeComponent() {
		/*
		 * This method is used to create all components used inside the form
		 */
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
		title = new LabelTitle("Books");
		close = new ButtonInternalClose();
  }

	@Override
	public void addComponent() {
		/**
		 * This method is used to add all created components to the form
		 * Related method: initializeComponent()
		 */
		JPanel pnlCenter = new JPanel(new BorderLayout(8, 8));
		pnlCenter.add(table.getScrollPane(), BorderLayout.CENTER);

		JPanel panel = new JPanel(new BorderLayout(8, 8));
		panel.add(title.getLabel(), BorderLayout.NORTH);
		panel.add(pnlCenter, BorderLayout.CENTER);
		panel.add(close.getButton(), BorderLayout.SOUTH);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		add(panel);
	}

	@Override
	public void addListener() {
		/*
		 * this method is self-explanatory
		 */
		table.addMouseListener(
				new MouseInputAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						super.mouseClicked(e);
					}
				}
			);
		close.addListener(this);
	}
	
}
