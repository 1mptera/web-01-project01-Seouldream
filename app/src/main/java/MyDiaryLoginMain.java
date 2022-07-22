import models.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import javax.swing.*;

public class MyDiaryLoginMain {
  public static List<Account> accountsList;
  private String test;
  private String test1;
  private JFrame loginFrame;
  private JPanel loginPanel = new JPanel();
  private JLabel idLabel = new JLabel("아이디 ");
  private JLabel passwordLabel = new JLabel("비밀번호 ");
  private JTextField idText = new JTextField();
  private JPasswordField passwordText = new JPasswordField();
  private JButton loginButton = new JButton("로그인");
  private JButton idPasswordSearchButton = new JButton("아이디/비밀번호 찾기");
  private JButton createIDButton = new JButton("아이디 생성히기");
  private GridBagLayout gridBagLayout;
  private GridBagConstraints gridBagConstraints;
  private String ID;
  private String password;


  public static void main(String[] args) throws FileNotFoundException {
    MyDiaryLoginMain application = new MyDiaryLoginMain();
    application.run();
  }

  private void run() throws FileNotFoundException {





    FileLoader fileloader = new FileLoader();
    String accountsListFile = "accountsList.csv";
    accountsList = fileloader.loadAccounts("accountsList.csv");

    //JFrame temporaryMainFrame = new MainFrame(account);
    setLoginFrame();
    setButtonsLayout();
    loginFrame.setVisible(true);

    loginButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ID = idText.getText().trim();
        password = passwordText.getText().trim();
        if (ID.length() == 0 || password.length() == 0) {
          JOptionPane.showMessageDialog(null,
              "아이디 또는 비밀번호를 입력하세요.", "알림",
              JOptionPane.DEFAULT_OPTION);
          return;
        }
        for (Account account : accountsList) {
          if (account.ID().equals(ID) && account.password().equals(password)) {
            JOptionPane.showMessageDialog(null, "로그인 성공",
                "로그인 확인!", JOptionPane.DEFAULT_OPTION);

            try {
              JFrame mainFrame = new MainFrame(account);
            } catch (FileNotFoundException ex) {
              throw new RuntimeException(ex);
            } catch (IOException ex) {
              throw new RuntimeException(ex);
            }
            loginFrame.setDefaultCloseOperation(loginFrame.DO_NOTHING_ON_CLOSE);
            loginFrame.setVisible(false);
            return;
          }
        }
        JOptionPane.showMessageDialog(null, "로그인 실패",
            "로그인 확인!", JOptionPane.DEFAULT_OPTION);
      }
    });
    idPasswordSearchButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "아이디 비밀번호 찾기",
            "아이디/비밀번호 찾기", JOptionPane.DEFAULT_OPTION);
      }
    });

    createIDButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JDialog createAccountDialog = new JDialog(loginFrame);
        setAccountComponents(createAccountDialog);

        createAccountDialog.setVisible(true);
      }
    });
  }

  private void setAccountComponents(JDialog createAccountDialog) {

    createAccountDialog.setSize(300, 200);
    createAccountDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    createAccountDialog.setLayout(new BorderLayout());
    createAccountDialog.setLocationRelativeTo(null);

    JPanel panel = new JPanel();
    JButton button = new JButton("아이디 생성하기");
    panel.setLayout(new GridLayout(4, 2));


    JLabel IDlabel = new JLabel("아이디");
    JTextField IDtextField = new JTextField(7);

    JLabel passwordLabel = new JLabel("비밀번호");
    JTextField passwordTextField = new JTextField(7);

    JLabel passwordCheckLabel = new JLabel("비밀번호 확인");
    JPasswordField passwordCheckTextField = new JPasswordField(7);

    JLabel nickNameLabel = new JLabel("닉네임 설정");
    JTextField nickNameTextField = new JTextField(7);

    panel.add(IDlabel);
    panel.add(IDtextField);
    panel.add(passwordLabel);
    panel.add(passwordTextField);
    panel.add(passwordCheckLabel);
    panel.add(passwordCheckTextField);
    panel.add(nickNameLabel);
    panel.add(nickNameTextField);

    IDlabel.setHorizontalAlignment(JLabel.CENTER);
    passwordLabel.setHorizontalAlignment(JLabel.CENTER);
    passwordCheckLabel.setHorizontalAlignment(JLabel.CENTER);
    nickNameLabel.setHorizontalAlignment(JLabel.CENTER);

    createAccountDialog.add(panel, BorderLayout.CENTER);
    createAccountDialog.add(button, BorderLayout.PAGE_END);

    button.addActionListener(event -> {
      String accountID = IDtextField.getText();
      String inputPassword = passwordTextField.getText();
      String checkPassword = passwordCheckTextField.getText();
      String nickName = nickNameTextField.getText();
      if (inputPassword.equals(checkPassword)){
        Account account = new Account(accountID, inputPassword, nickName);
        accountsList.add(account);
        createAccountDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        createAccountDialog.setVisible(false);
      }
      if (!inputPassword.equals(checkPassword)) {
        JOptionPane.showMessageDialog(null,
            "             일치하지 않는 비밀번호.",
            "아이디 생성 오류", JOptionPane.DEFAULT_OPTION);
      }
    });

  }

  private void setLoginFrame() {
    loginFrame = new JFrame(("로그인 페이지"));
    loginFrame.setSize(350, 150);
    loginFrame.setLayout(new BorderLayout());
    loginFrame.setLocationRelativeTo(null);
    loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void setButtonsLayout() {
    gridBagLayout = new GridBagLayout();
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    loginPanel.setLayout(gridBagLayout);
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    loginFrame.add(loginPanel);
    setIDButtons();
    setPasswordButtons();
    setLoginButtons();
    setCreateIDButton();
  }

  private void setCreateIDButton() {
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.gridheight = 1;
    loginPanel.add(createIDButton, gridBagConstraints);
  }

  private void setLoginButtons() {
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = 1;
    gridBagConstraints.gridheight = 1;
    loginPanel.add(idPasswordSearchButton, gridBagConstraints);
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = 1;
    gridBagConstraints.gridheight = 1;
    loginPanel.add(loginButton, gridBagConstraints);
  }

  private void setPasswordButtons() {
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 1;
    gridBagConstraints.gridheight = 1;
    passwordLabel.setHorizontalAlignment(JLabel.CENTER);
    loginPanel.add(passwordLabel, gridBagConstraints);
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 1;
    gridBagConstraints.gridheight = 1;
    loginPanel.add(passwordText, gridBagConstraints);
  }

  private void setIDButtons() {
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 1;
    gridBagConstraints.gridheight = 1;
    idLabel.setHorizontalAlignment(JLabel.CENTER);
    loginPanel.add(idLabel, gridBagConstraints);
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 1;
    gridBagConstraints.gridheight = 1;
    loginPanel.add(idText, gridBagConstraints);
  }


}