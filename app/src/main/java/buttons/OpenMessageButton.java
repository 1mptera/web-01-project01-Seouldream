package buttons;

import com.mommoo.flat.button.*;
import models.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class OpenMessageButton extends JButton {
  private String state;
  private Account account;
  private String receiver;
  private Message message;
  private List<Message> messages;
  private JFrame writingFrame;
  private JTextField titleTextField;
  private JTextArea writingTextArea;
  private JPanel framePanel;
  private JPanel contentPanel;

  public OpenMessageButton(Account account, String receiver, Message message, List<Message> messages){
      this.account = account;
    this.receiver = receiver;
    this.message = message;
    this.messages = messages;
    this.state = Journal.PUBLISHED;
    this.setText(message.title() + "\t대화상대: " +receiver );
    this.setBackground(Color.getHSBColor(1,2,3));
    this.setForeground(Color.BLACK);
      //  JPanel diaryBoardPanel = (JPanel)getTopLevelAncestor();

      this.addActionListener(event -> {

        openWritingFrame(message);

        FlatButton deleteButton = new DeleteButton(message);

        framePanel.add(deleteButton, BorderLayout.PAGE_END);

        writingFrame.add(framePanel);
        writingFrame.setVisible(true);

      });
    }

  private void openWritingFrame(Message message) {
    writingFrame = new JFrame("오늘의 일기");
    writingFrame.setSize(400, 500);
    writingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    writingFrame.setLocationRelativeTo(this);

    titleTextField = new JTextField(10);
    writingTextArea = new JTextArea(30, 10);
    framePanel = new JPanel();
    contentPanel = new JPanel();

    framePanel.setLayout(new BorderLayout());
    contentPanel.setLayout(new BorderLayout());

    framePanel.add(titleTextField, BorderLayout.PAGE_START);
    framePanel.add(contentPanel, BorderLayout.CENTER);
    contentPanel.add(writingTextArea, BorderLayout.CENTER);

    titleTextField.setText(receiver);
    writingTextArea.setText(message.texts());
    titleTextField.setEditable(false);
    writingTextArea.setEditable((false));
  }

  public String state() {
    if (!message.state().equals(state)) {
      return state = message.state();
    }
    return null;
  }
  }

