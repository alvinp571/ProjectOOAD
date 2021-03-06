package views;

import components.LabelTitle;

import controllers.AuthController;
import controllers.MemberHandler;
import models.Member;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import views.base.BaseView;

public final class CreateMembershipForm extends BaseView {

  private static final long serialVersionUID = 1L;

  private LabelTitle title;
  private JLabel lblName, lblUsername, lblPassword, lblConfirmPassword,lblGender,lblAddress;
  private JTextField txtName, txtUsername, txtGender,txtAddress;
  private JPasswordField txtPassword, txtConfirmPassword;
  private JButton btnRegister, btnClose;

  private MemberHandler memberHandler = new MemberHandler();
  private AuthController authController = new AuthController();
  
  public CreateMembershipForm() {
    super("Register Form", 350, 325);
  }

  @Override
  public void initializeComponent() {
    title = new LabelTitle("Register Members");
    lblName = new JLabel("Name");
    lblUsername = new JLabel("Username");
    lblPassword = new JLabel("Password");
    lblConfirmPassword = new JLabel("Confirm Password");
    lblGender = new JLabel("Gender");
    lblAddress = new JLabel("Address");
    txtName = new JTextField();
    txtGender = new JTextField();
    txtUsername = new JTextField();
    txtAddress = new JTextField();
    txtPassword = new JPasswordField();
    txtConfirmPassword = new JPasswordField();
    btnRegister = new JButton("Register");
    btnClose = new JButton("Close");
  }

  @Override
  public void addComponent() {
    JPanel pnlFormLabel = new JPanel(new GridLayout(6, 1, 8, 8));
    pnlFormLabel.add(lblName);
    pnlFormLabel.add(lblUsername);
    

    pnlFormLabel.add(lblPassword);
    pnlFormLabel.add(lblConfirmPassword);
    pnlFormLabel.add(lblGender);
    pnlFormLabel.add(lblAddress);
    
    
    JPanel pnlFormInput = new JPanel(new GridLayout(6, 1, 8, 8));
    pnlFormInput.add(txtName);
    pnlFormInput.add(txtUsername);
    pnlFormInput.add(txtPassword);
    pnlFormInput.add(txtConfirmPassword);
    pnlFormInput.add(txtGender);
    pnlFormInput.add(txtAddress);

    JPanel pnlForm = new JPanel(new BorderLayout(8, 8));
    pnlForm.add(pnlFormLabel, BorderLayout.WEST);
    pnlForm.add(pnlFormInput, BorderLayout.CENTER);

    JPanel pnlButton = new JPanel(new GridLayout(2, 1, 8, 8));
    pnlButton.add(btnRegister);
    pnlButton.add(btnClose);

    JPanel panel = new JPanel(new BorderLayout(8, 8));
    panel.add(title.getLabel(), BorderLayout.NORTH);
    panel.add(pnlForm, BorderLayout.CENTER);
    panel.add(pnlButton, BorderLayout.SOUTH);
    panel.setBorder(new EmptyBorder(10, 10, 10, 10));

    add(panel);
  }

  @Override
  public void addListener() {
    btnRegister.addActionListener(
      new AbstractAction() {

        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	HashMap<String, String> inputs = new HashMap<String, String>();
            inputs.put("name",txtName.getText());
            inputs.put("username",txtUsername.getText());
            inputs.put("password",new String(txtPassword.getPassword()));
            inputs.put("confirm_password",new String(txtConfirmPassword.getPassword()));
            inputs.put("gender", txtGender.getText());
            inputs.put("address", txtAddress.getText());
            
            Member member = new Member();
            member = memberHandler.CreateMembership(inputs);
            if(member!=null) {
            	authController.showLoginForm().showForm();
            	dispose();
            }
            
        }
      }
    );

    btnClose.addActionListener(
      new AbstractAction() {

        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
          authController.showLoginForm().showForm();
          dispose();
        }
      }
    );
  }
}
