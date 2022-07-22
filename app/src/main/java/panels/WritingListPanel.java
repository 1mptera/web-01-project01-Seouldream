package panels;

import buttons.*;
import models.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

public class WritingListPanel extends JPanel {
  private JFrame writingFrame;
  private JTextField titleTextField;
  private JTextArea writingTextArea;


  private List<Journal> journals;
  private List<Comment> publicComments;

  public WritingListPanel(Account account,List<Journal> journals, List<Comment> publicComments) throws IOException {
    this.journals = journals;
    this.publicComments = publicComments;

    this.setLayout(new GridLayout(0, 1));

    for (Journal journal : journals) {
      if (journal.state().equals("DELETED")) {
        continue;
      }
      JButton button = new OpenJournalButton(account,journal,journals,publicComments);

      this.add(button);
    }
  }

  public WritingListPanel(Account account,List<Journal> journals,List<Comment> privateComments,String onlyForMe) throws IOException {
    this.journals = journals;

    this.setLayout(new GridLayout(0, 1));

    for (Journal journal : journals) {
      if (journal.state().equals("DELETED")) {
        continue;
      }
      JButton button = new OpenJournalButton(account,journal,privateComments,"onlyForMe");

      this.add(button);
    }
  }
}
