package views;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import views.base.BaseView;

public final class MainForm extends BaseView {

  private static final long serialVersionUID = 1L;

  private JMenuBar menuBar;
  private JMenu mFile, mManage;
  private JMenuItem miLogout, miCourse;
  private JDesktopPane desktopPane;

  public MainForm() {
    super("Sweebook Library", Boolean.TRUE);
  }

  @Override
  public void initializeComponent() {
    menuBar = new JMenuBar();

    mFile = new JMenu("File");
    mManage = new JMenu("Manage");

    miLogout = new JMenuItem("Logout");
    miCourse = new JMenuItem("Course");

    desktopPane =
      new JDesktopPane() {

        private static final long serialVersionUID = 1L;

        @Override
        protected void paintComponent(Graphics g) {
          super.paintComponent(g);
          try {
            final File PATH = new File("assets/background.png");
            Image image = ImageIO.read(PATH);

            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      };
  }

  @Override
  public void addComponent() {
    mFile.add(miLogout);
    mManage.add(miCourse);

    menuBar.add(mFile);
    menuBar.add(mManage);

    setJMenuBar(menuBar);

    setContentPane(desktopPane);
  }

  @Override
  public void addListener() {
    miLogout.addActionListener(
      new AbstractAction() {

        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
          // TODO Auto-generated method stub

        }
      }
    );

    miCourse.addActionListener(
      new AbstractAction() {

        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
          // TODO Auto-generated method stub
        
        }
      }
    );
  }
}
