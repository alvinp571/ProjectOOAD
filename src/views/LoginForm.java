package views;

import components.LabelTitle;
import controllers.AuthController;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import views.base.BaseView;

public final class LoginForm extends BaseView {

  private static final long serialVersionUID = 1L;

  private LabelTitle title;
  private JLabel lblUsername, lblPassword;
  private JTextField txtUsername;
  private JPasswordField txtPassword;
  private JButton btnLogin, btnRegister;

  public LoginForm() {
    super("Login Form", 350, 225);
  }

  @Override
  public void initializeComponent() {
    title = new LabelTitle("Sweebook Library System");
    lblUsername = new JLabel("Username");
    lblPassword = new JLabel("Password");
    txtUsername = new JTextField();
    txtPassword = new JPasswordField();
    btnLogin = new JButton("Login");
    btnRegister = new JButton("Register");
  }

  @Override
  public void addComponent() {
    JPanel pnlFormLabel = new JPanel(new GridLayout(2, 1, 8, 8));
    pnlFormLabel.add(lblUsername);
    pnlFormLabel.add(lblPassword);

    JPanel pnlFormInput = new JPanel(new GridLayout(2, 1, 8, 8));
    pnlFormInput.add(txtUsername);
    pnlFormInput.add(txtPassword);

    JPanel pnlForm = new JPanel(new BorderLayout(8, 8));
    pnlForm.add(pnlFormLabel, BorderLayout.WEST);
    pnlForm.add(pnlFormInput, BorderLayout.CENTER);

    JPanel pnlButton = new JPanel(new GridLayout(2, 1, 8, 8));
    pnlButton.add(btnLogin);
    pnlButton.add(btnRegister);

    JPanel panel = new JPanel(new BorderLayout(8, 8));
    panel.add(title.getLabel(), BorderLayout.NORTH);
    panel.add(pnlForm, BorderLayout.CENTER);
    panel.add(pnlButton, BorderLayout.SOUTH);
    panel.setBorder(new EmptyBorder(10, 10, 10, 10));

    add(panel);
  }

  @Override
  public void addListener() {
    btnLogin.addActionListener(
      new AbstractAction() {

        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	String username = txtUsername.getText();
        	if(new AuthController().login(username,new String(txtPassword.getPassword()))){
        		new AuthController().showWhichForm(username).showForm();
        		dispose();
        	}
        }
      }
    );

    btnRegister.addActionListener(
      new AbstractAction() {

        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	new AuthController().showRegisterForm().showForm();
        	dispose();
        }
      }
    );
  }
}
