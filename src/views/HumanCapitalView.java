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

import controllers.AuthController;
import views.base.BaseView;

public final class HumanCapitalView extends BaseView {

  private static final long serialVersionUID = 1L;

  private JMenuBar menuBar;
  private JMenu mFile, mManage;
  private JMenuItem miLogout, miViewEmployees;
  private JDesktopPane desktopPane;
  
  private ViewEmployee viewEmployee;

  public HumanCapitalView() {
    super("Human and Resource Staff", Boolean.TRUE);
  }

  @Override
  public void initializeComponent() {
    menuBar = new JMenuBar();

    mFile = new JMenu("File");
    mManage = new JMenu("Manage");

    miLogout = new JMenuItem("Logout");
    miViewEmployees = new JMenuItem("View Employees");

    desktopPane =
      new JDesktopPane() {

        private static final long serialVersionUID = 1L;

        @Override
        protected void paintComponent(Graphics g) {
          super.paintComponent(g);
          try {
            final File PATH = new File("assets/library.jpg");
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
    mManage.add(miViewEmployees);

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
        	dispose();
        	new AuthController().showLoginForm().showForm();
        }
      }
    );

    miViewEmployees.addActionListener(
      new AbstractAction() {

        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	if(viewEmployee == null || viewEmployee.isClosed()) {
        		viewEmployee = new ViewEmployee();
        		desktopPane.add(viewEmployee);
        		viewEmployee.showForm();
        	}
        
        }
      }
    );
  }
}
