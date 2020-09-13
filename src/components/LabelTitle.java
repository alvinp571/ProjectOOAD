package components;

import java.awt.Font;
import javax.swing.JLabel;

public class LabelTitle {
	private JLabel label;
	
	public LabelTitle(String text) {
		label = new JLabel(text, JLabel.CENTER);
		label.setFont(new Font("Roboto", Font.BOLD, 18));
 	}
	
	public JLabel getLabel() {
		return label;
	}
	
}
