import javax.swing.SwingUtilities;
import views.LoginForm;

public final class Main implements Runnable {

  public Main() {
    SwingUtilities.invokeLater(this);
  }

  @Override
  public void run() {
    new LoginForm().showForm();
  }

  public static void main(String[] args) throws Exception {
    new Main();
  }
}
