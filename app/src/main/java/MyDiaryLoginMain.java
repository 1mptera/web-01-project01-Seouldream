import models.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class MyDiaryLoginMain {
  private String test;
  private Account account = new Account(test);
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
      MyDiaryLoginMain applicaton = new MyDiaryLoginMain();
      applicaton.run();
    }

     private void run() throws FileNotFoundException {

        JFrame temporaryMainFrame = new MainFrame(account);
        // setLoginFrame(); // - 지우지 말 것
        // setButtonsLayout();// - 지우지 말 것
        //loginFrame.setVisible(true);
        //List<Writing> writings = loadWritings();
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
            if (ID.equals("test") && password.equals("test1")) {
              JOptionPane.showMessageDialog(null, "로그인 성공",
                  "로그인 확인!",JOptionPane.DEFAULT_OPTION);

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
          public void actionPerformed(ActionEvent e) {}
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
        gridBagConstraints.weightx=1.0;
        gridBagConstraints.weighty=1.0;
        loginPanel.setLayout(gridBagLayout);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        loginFrame.add(loginPanel);
        setIDButtons();
        setPasswordButtons();
        setLoginButtons();
        setCreateIDButton();
      }
      private void setCreateIDButton() {
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=3;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.gridheight=1;
        loginPanel.add(createIDButton,gridBagConstraints);
      }
      private void setLoginButtons() {
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=2;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        loginPanel.add(idPasswordSearchButton,gridBagConstraints);
        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=2;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        loginPanel.add(loginButton,gridBagConstraints);
      }
      private void setPasswordButtons() {
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=1;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
        loginPanel.add(passwordLabel,gridBagConstraints);
        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=1;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        loginPanel.add(passwordText,gridBagConstraints);
      }
      private void setIDButtons() {
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=0;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        idLabel.setHorizontalAlignment(JLabel.CENTER);
        loginPanel.add(idLabel,gridBagConstraints);
        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=0;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        loginPanel.add(idText,gridBagConstraints);
      }
    }