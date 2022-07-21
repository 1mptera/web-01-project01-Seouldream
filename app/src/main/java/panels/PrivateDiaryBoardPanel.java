package panels;
import buttons.*;
import models.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

public class PrivateDiaryBoardPanel extends JPanel {
  private List<Journal> privateJournals;
  private JPanel contentPanel;

  public PrivateDiaryBoardPanel(List<Journal> privateJournals) throws IOException {
    this.privateJournals = privateJournals;

    this.setLayout(new BorderLayout());

    JButton letsGoWriteButton = new LetsGoWriteButton(privateJournals,contentPanel);

    this.add(letsGoWriteButton, BorderLayout.PAGE_START);

    showContentPanel(privateJournals);

  }

  private void showContentPanel(List<Journal> privateJournals) throws IOException {
    if (contentPanel != null) {
      contentPanel.removeAll();
    }
    contentPanel = new WritingListPanel(privateJournals,"onlyForME");

    this.add(contentPanel);

    contentPanel.setVisible((false));
    contentPanel.setVisible(true);
  }


}

