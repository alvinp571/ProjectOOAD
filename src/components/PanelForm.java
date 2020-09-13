package components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelForm {
	private JPanel panel;
	private Component[][] components;
	private JButton button;
	private Dimension dimension;

	public PanelForm(Component[][] components, JButton button, Dimension dimension) {
		this.components = components;
		this.button = button;
		this.dimension = dimension;
		createPanel();
	}
	
	private void createPanel() {
		JPanel pnlFormLabel = new JPanel(new GridLayout(components[0].length, 1, 8, 8));
		for (Component l : components[0]) {
			pnlFormLabel.add(l);
		}
		
		JPanel pnlFormInput = new JPanel(new GridLayout(components[1].length, 1, 8, 8));
		for (Component c : components[1]) {
			pnlFormInput.add(c);
		}

		panel = new JPanel(new BorderLayout(8, 8));
		panel.add(pnlFormLabel, BorderLayout.WEST);
		panel.add(pnlFormInput, BorderLayout.CENTER);
		panel.add(button, BorderLayout.SOUTH);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setPreferredSize(dimension);
	}

	public JPanel getPanel() {
		return panel;
	}
	
}
