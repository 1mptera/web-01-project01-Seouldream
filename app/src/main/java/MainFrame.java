import models.*;
import panels.*;
import utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class MainFrame extends JFrame {
  private Account account;
  private JPanel contentPanel;
  private Fileloader fileloader;
  private List<Journal> publicJournals = new ArrayList<>();
  private List<Journal> privateJournals = new ArrayList<>();

  MainFrame(Account account) throws FileNotFoundException {
    fileloader = new Fileloader();
    String publicJournalFile = "input.csv";
    String myJournalFile = "private.csv";
    publicJournals = fileloader.loadWritings(publicJournals,publicJournalFile);
    privateJournals = fileloader.loadWritings(privateJournals,myJournalFile);
    this.account = account;

    setMainFrameSetting();

    setMenuPanel();

    saveDiary(publicJournals,"input.csv");
    saveDiary(privateJournals,"private.csv");

    this.setVisible(true);
    }


    private void setMainFrameSetting() {
      this.setSize(500,550);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLocationRelativeTo(null);
      this.setLayout(new BorderLayout());
    }

    private void setMenuPanel() {
      JPanel menuPanel = new JPanel();

        this.add(menuPanel,BorderLayout.PAGE_START);
        menuPanel.setLayout(new GridLayout(1,3));
        menuPanel.add(createDiaryBoardButton());
        menuPanel.add(createPrivateDiaryBoard());
        menuPanel.add(createMessengerPanel());
      }


        private JButton createDiaryBoardButton() {
          JButton button = new JButton("너의 일기가 보고싶어!");
          button.addActionListener(event -> {
            if(contentPanel != null) {
              contentPanel.removeAll();
            }
            contentPanel = new DiaryBoardPanel(publicJournals);

            showContentPanel();
          });
          return button;}

        private JButton createPrivateDiaryBoard() {
          JButton button = new JButton("나만의 일기장");
          button.addActionListener(event -> {
            if(contentPanel != null) {
              contentPanel.removeAll();
            }
            contentPanel = new PivateDiaryBoardPanel(privateJournals);

            showContentPanel();
          });
          return button;
        }

        private JButton createMessengerPanel() {
          JButton button = new JButton("메신저함");
          button.addActionListener(event -> {
            JPanel messengerPanel = new MessengerPanel();
          });
          return button;
        }

        public void showContentPanel() {
          this.add(contentPanel,BorderLayout.CENTER);
          contentPanel.setVisible(false);
          contentPanel.setVisible(true);
          this.setVisible(true);
        }

        private void saveDiary(List<Journal> journals,String newFileName) {

          this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
              try{
                fileloader.diaryWriter(journals,newFileName);
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
            }
          });
        }
      }