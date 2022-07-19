package buttons;

import models.*;

import javax.swing.*;

public class openJournalButton extends JButton {
  private Journal journal;
  private String state;

  public openJournalButton(Journal journal) {
    this.journal = journal;
    this.state = Journal.PUBLISHED;
    this.setText(journal.title());
  }

  public String state() {
    if (!journal.state().equals(state)) {
      return state = journal.state();
    } return null;
  }
}
