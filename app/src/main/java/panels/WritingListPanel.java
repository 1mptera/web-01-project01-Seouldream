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

  public WritingListPanel(List<Journal> journals) throws IOException {
    this.journals = journals;

    this.setLayout(new GridLayout(0, 1));

    for (Journal journal : journals) {
      if (journal.state().equals("DELETED")) {
        continue;
      }
      JButton button = new OpenJournalButton(journal,journals);

      this.add(button);
    }
  }

  public WritingListPanel(List<Journal> journals,String onlyForMe) throws IOException {
    this.journals = journals;

    this.setLayout(new GridLayout(0, 1));

    for (Journal journal : journals) {
      if (journal.state().equals("DELETED")) {
        continue;
      }
      JButton button = new OpenJournalButton(journal,journals,this,"onlyForMe");

      this.add(button);
    }
  }
}
