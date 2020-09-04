package components;

import java.awt.Font;
import javax.swing.JLabel;

/**
 * Label Title
 * 
 * @author kevinsudut <kevinsuryaw@gmail.com>
 */
public class LabelTitle {
  private JLabel label;

  public LabelTitle(String text) {
    label = new JLabel(text, JLabel.CENTER);
    label.setFont(new Font("Times New Roman", Font.BOLD, 18));
  }

  public JLabel getLabel() {
    return label;
  }
}
