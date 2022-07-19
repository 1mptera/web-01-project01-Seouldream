package panels;

import models.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class MyDiaryPanel extends JPanel {
  private List<Journal> journals;

  public MyDiaryPanel() {
    setLayout(new GridLayout(0, 1));

    loadJournals();

    displayJournals();

    setVisible(true);
  }

  private void loadJournals() {
    journals = new ArrayList<>();

    // Change to fileLoader
    for (int i = 0; i < 4; i += 1) {
      String title = "title" + i;
      String content = "content" + i;
      journals.add(new Journal(title, content));
    }
  }

  private void displayJournals() {
    for (Journal journal : journals) {
      if (journal.state().equals(Journal.DELETED)) {
        continue;
      }

      // TODO: bun li hae ju gi
//      JournalPanel journalPanel = new JournalPanel(journal);
      String post = journal.toString();
      JButton journalPanel = new JButton(post);

      journalPanel.addActionListener(event -> {
        journal.delete();

        this.remove(journalPanel);
        this.setVisible(false);
        this.setVisible(true);
      });

      this.add(journalPanel);
      this.setVisible(false);
      this.setVisible(true);
    }
  }
}

