package panels;

import buttons.*;
import com.mommoo.flat.button.*;
import models.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

public class MessengerPanel extends JPanel {
  private JPanel contentPanel = new JPanel();

  private Account account;
  private String receiver;
  private List<Message> messages;

  public MessengerPanel(Account account,String receiver,List<Message> messages) throws IOException {
    this.account = account;
    this.receiver = receiver;
    this.messages = messages;

    this.setLayout(new BorderLayout());
    this.add(contentPanel, BorderLayout.CENTER);

    //showContentPanel(publicJournals);

    FlatButton sendMessageButton = new FlatButton("쪽지 보내기");

    this.add(sendMessageButton, BorderLayout.PAGE_START);

    showContentPanel();

    sendMessageButton.addActionListener(letsGoWriteButtonEvent -> {

      openWritingWindow();

    });
  }

  private void showContentPanel() throws IOException {
    if (contentPanel != null) {
      contentPanel.removeAll();
    }

    contentPanel = new MessengerListPanel(account,messages,receiver);

    this.add(contentPanel);

    contentPanel.setVisible((false));
    contentPanel.setVisible(true);
  }

  public void openWritingWindow() {
    JFrame messageFrame = new JFrame("오늘의 일기");
    messageFrame.setSize(400, 500);
    messageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    messageFrame.setLocationRelativeTo(this);

    JPanel framePanel = new JPanel();
    JTextField receiverTextField = new JTextField(8);
    JTextArea messageTextArea = new JTextArea(20, 8);

    framePanel.setLayout(new BorderLayout());

    framePanel.add(receiverTextField, BorderLayout.PAGE_START);
    framePanel.add(messageTextArea, BorderLayout.CENTER);

    String receiver = receiverTextField.getText();
    SaveButton saveButton = new SaveButton(account,messageFrame,receiver,messageTextArea,messages);
    framePanel.add(saveButton, BorderLayout.PAGE_END);
    messageFrame.add(framePanel);
    messageFrame.setVisible(true);
  }
}
