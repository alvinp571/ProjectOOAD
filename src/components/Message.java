package components;

import javax.swing.JOptionPane;

/**
 * Message
 * 
 * @author kevinsudut <kevinsuryaw@gmail.com>
 */
public class Message {

  public static void error(String message) {
    JOptionPane.showMessageDialog(
      null,
      message,
      "Error Message",
      JOptionPane.ERROR_MESSAGE
    );
  }

  public static void success(String message) {
    JOptionPane.showMessageDialog(
      null,
      message,
      "Success Message",
      JOptionPane.INFORMATION_MESSAGE
    );
  }

  public static Integer confirm(String message, String title) {
    return JOptionPane.showConfirmDialog(
      null,
      message,
      title,
      JOptionPane.YES_NO_OPTION,
      JOptionPane.QUESTION_MESSAGE
    );
  }
}
