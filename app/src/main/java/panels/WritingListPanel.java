package panels;

import buttons.*;
import models.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WritingListPanel extends JPanel {


  private List<Journal> journals;

  public WritingListPanel(List<Journal> journals) {
    this.journals = journals;

    this.setLayout(new GridLayout(0, 1));

    for (Journal journal : journals) {
      if (journal.state().equals("DELETED")) {
        continue;
    }
      JButton button = new openJournalButton(journal);

      this.add(button);
    }

  }


}
