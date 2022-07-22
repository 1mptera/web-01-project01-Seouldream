package panels;

import buttons.*;
import models.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

public class MessengerListPanel extends JPanel {
  private Account account;
  private List<Message> messages;

  public MessengerListPanel(Account account, List<Message> messages,String receiver) {
    this.account = account;
    this.messages = messages;

    this.setLayout(new GridLayout(0, 1));

    for (Message message : messages) {
      if (message.state().equals("DELETED")) {
        continue;
      }
      JButton button = new OpenMessageButton(account,receiver,message,messages);

      this.add(button);
    }
  }

}
