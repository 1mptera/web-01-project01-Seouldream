package buttons;

import com.mommoo.flat.button.*;
import models.*;
import panels.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

public class SaveButton extends FlatButton {
  private JFrame writingFrame;
  private Journal journal;
  private JTextField titleTextField;
  private JTextArea writingTextArea;
  private JPanel contentPanel;

  public SaveButton(JFrame writingFrame, Journal journal, List<Journal> journals, List<Comment> comments) {
    this.writingFrame = writingFrame;
    this.journal = journal;


    this.setText("저장하기");

    this.addActionListener(saveButtonEvent -> {
      journal.delete();
      this.journal.modify();
      writingFrame.removeAll();
      writingFrame.setVisible(false);

      FlatButton modifyButton = new FlatButton("수정하기");

      openWritingWindow(journal, modifyButton, comments);

      String title = titleTextField.getText();
      String writingContent = writingTextArea.getText();

      this.journal = new Journal(title,writingContent);
      journals.add(this.journal);

      writingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      try {
        showContentPanel(journals);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

    });
  }

  public void openWritingWindow(Journal journal, FlatButton modifyButton, List<Comment> comments) {
    writingFrame = new JFrame("오늘의 일기");
    writingFrame.setSize(400, 500);
    writingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    writingFrame.setLocationRelativeTo(this);

    JPanel framePanel = new JPanel();
    titleTextField = new JTextField(10);
    JPanel contentPanel = new JPanel();
    writingTextArea = new JTextArea(30, 10);
    FlatButton goLeaveACommentButton = new GoLeaveACommentButton(journal, comments);

    framePanel.setLayout(new BorderLayout());
    contentPanel.setLayout(new BorderLayout());

    framePanel.add(titleTextField, BorderLayout.PAGE_START);
    framePanel.add(contentPanel, BorderLayout.CENTER);
    contentPanel.add(writingTextArea, BorderLayout.CENTER);
    contentPanel.add(goLeaveACommentButton, BorderLayout.PAGE_END);
    framePanel.add(this, BorderLayout.PAGE_END);

    titleTextField.setText(journal.title());
    writingTextArea.setText(journal.content());
    titleTextField.setEditable(false);
    writingTextArea.setEditable((false));

    writingFrame.add(framePanel);
    writingFrame.setVisible(true);
  }

  private void showContentPanel(List<Journal> privateJournals) throws IOException {
    if (contentPanel != null) {
      contentPanel.removeAll();
    }
    contentPanel = new WritingListPanel(privateJournals, "onlyForME");

    this.add(contentPanel);

    contentPanel.setVisible((false));
    contentPanel.setVisible(true);
  }

}

