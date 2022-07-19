package panels;

import buttons.*;
import models.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WritingListPanel extends JPanel {
  private JFrame writingFrame;
  private JTextField titleTextField;
  private JTextArea writingTextArea;


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

      button.addActionListener(event -> {
        JButton deleteButton = new JButton("삭제하기");

        openWritingWindow(journal, deleteButton);

        deleteButton.addActionListener(deleteButtonEvent -> {
          journal.delete();
          writingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          writingFrame.setVisible(false);

          this.removeAll();
          this.setVisible(false);
          this.add(new WritingListPanel(journals));
          this.setVisible(true);
        });
      });
    }


  }

  public void openWritingWindow(Journal journal, JButton button) {
    writingFrame = new JFrame("오늘의 일기");
    writingFrame.setSize(400, 500);
    writingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    writingFrame.setLocationRelativeTo(this);

    JPanel framePanel = new JPanel();
    titleTextField = new JTextField(10);
    writingTextArea = new JTextArea(30, 10);

    titleTextField.setText(journal.title());
    writingTextArea.setText(journal.content());
    titleTextField.setEditable(false);
    writingTextArea.setEditable((false));

    framePanel.setLayout(new BorderLayout());

    framePanel.add(titleTextField, BorderLayout.PAGE_START);
    framePanel.add(writingTextArea, BorderLayout.CENTER);
    framePanel.add(button, BorderLayout.PAGE_END);
    writingFrame.add(framePanel);
    writingFrame.setVisible(true);
  }
}


