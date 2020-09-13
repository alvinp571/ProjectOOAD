package components;

import java.awt.Color;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;

public class Table {
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scroll;

	public Table(Vector<Object> tHeader, Vector<Vector<Object>> tRows) {
		model = new DefaultTableModel(tRows, tHeader);

		table =	new JTable(model) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return Boolean.FALSE;
			}
		};
		
		table.getTableHeader().setReorderingAllowed(Boolean.FALSE);
      
		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
	}

	public JScrollPane getScrollPane() {
		return scroll;
	}

	public void addNewRow(Vector<Object> row) {
		model.addRow(row);
	}

	public void updateRow(Integer row, String... values) {
		for (Integer i = 0; i < values.length; i++) {
			model.setValueAt(values[i], row, i);
		}
	}

	public void removeRow(Integer row) {
		model.removeRow(row);
	}

	public void removeAll() {
		while (getRowCount() > 0) {
			removeRow(getRowCount() - 1);
		}
	}

	public Integer getSelectedRow() {
		return table.getSelectedRow();
	}

	public void clearSelection() {
		table.clearSelection();
	}

	public Integer getRowCount() {
	  return model.getRowCount();
  	}

  	public String getValueAt(Integer row, Integer column) {
  		return model.getValueAt(row, column).toString();
  	}

  	public void addMouseListener(MouseInputAdapter adapter) {
  		table.addMouseListener(adapter);
  	}
  	
}
