package views.base;

import static env.Env.getEnv;

import javax.swing.JFrame;

import env.Environment;

/**
 * Base View
 *
 * @author kevinsudut <kevinsuryaw@gmail.com>
 */
public abstract class BaseView extends JFrame implements IView {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private Boolean isMaximize;

  public BaseView(String title, Integer width, Integer height) {
    isMaximize = Boolean.FALSE;
    setSize(width, height);
    initializeForm(title);
  }

  public BaseView(String title, Boolean maximize) {
    isMaximize = maximize;
    setExtendedState(MAXIMIZED_BOTH);
    initializeForm(title);
  }

  @Override
  public void initializeForm(String title) {
    setTitle(title);
    setDefaultCloseOperation(closeOperation());
    setLocation();
  }

  @Override
  public Integer closeOperation() {
    return EXIT_ON_CLOSE;
  }

  @Override
  public void setLocation() {
    setLocationRelativeTo(null);
  }

  @Override
  public void render() {
    initializeComponent();
    addComponent();
    addListener();
  }

  @Override
  public void showForm() {
    render();
    setVisible(Boolean.TRUE);
    setProperty();
  }

  private void setProperty() {
    if (isMaximize) {
      System.setProperty(
        getEnv(Environment.MAIN_WIDTH),
        Integer.toString(getWidth())
      );
      System.setProperty(
        getEnv(Environment.MAIN_HEIGHT),
        Integer.toString(getHeight())
      );
    }
  }
}
