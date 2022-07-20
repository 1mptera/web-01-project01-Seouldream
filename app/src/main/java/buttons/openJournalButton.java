package buttons;

import models.*;
import panels.*;
import utils.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

public class OpenJournalButton extends JButton {
  private String title;
  private String writingContent;
  private Journal journal;
  private List<Journal> journals;
  private String state;
  private JFrame writingFrame;
  private JTextField titleTextField;
  private JTextArea writingTextArea;
  private List<Comment> comments;


  public OpenJournalButton(Journal journal, List<Journal> journals
      , WritingListPanel writingListPanel) throws IOException {
    this.journal = journal;
    this.journals = journals;
    this.state = Journal.PUBLISHED;
    this.setText(journal.title());

    Fileloader fileloader = new Fileloader();
    String commentsFile = "comments.csv";

    comments = fileloader.loadComments(commentsFile);

    this.addActionListener(event -> {
      writingFrame = new JFrame("오늘의 일기");
      writingFrame.setSize(400, 500);
      writingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      writingFrame.setLocationRelativeTo(this);

      JButton deleteButton = new DeleteButton(writingFrame,writingListPanel,journal,journals,comments);

      openWritingWindow(journal,deleteButton,comments);



    });
  }

  public OpenJournalButton(Journal journal, List<Journal> journals
      , WritingListPanel writingListPanel, String onlyForMe) throws IOException {
    this.journal = journal;
    this.state = Journal.PUBLISHED;
    this.setText(journal.title());

    Fileloader fileloader = new Fileloader();
    String commentsFile = "commentsForMyself.csv";

    comments = fileloader.loadComments(commentsFile);

    this.addActionListener(event -> {
      JButton modifyButton = new ModifyButton(journal, journals, comments);

    });
  }

    public void openWritingWindow(Journal journal, JButton button, List<Comment> comments) {

      JPanel framePanel = new JPanel();
      titleTextField = new JTextField(10);
      JPanel contentPanel = new JPanel();
      writingTextArea = new JTextArea(30, 10);
      JButton goLeaveACommentButton = new GoLeaveACommentButton(journal, comments);

      framePanel.setLayout(new BorderLayout());
      contentPanel.setLayout(new BorderLayout());

      framePanel.add(titleTextField, BorderLayout.PAGE_START);
      framePanel.add(contentPanel, BorderLayout.CENTER);
      contentPanel.add(writingTextArea, BorderLayout.CENTER);
      contentPanel.add(goLeaveACommentButton, BorderLayout.PAGE_END);
      framePanel.add(button, BorderLayout.PAGE_END);

      titleTextField.setText(journal.title());
      writingTextArea.setText(journal.content());
      titleTextField.setEditable(false);
      writingTextArea.setEditable((false));

      writingFrame.add(framePanel);
      writingFrame.setVisible(true);
    }




/*  private void showContentPanel(List<Journal> privateJournals) throws IOException {
    if (contentPanel != null) {
      contentPanel.removeAll();
    }
    contentPanel = new WritingListPanel(privateJournals, "onlyForME");

    this.add(contentPanel);

    contentPanel.setVisible((false));
    contentPanel.setVisible(true);
  }*/


  public String state() {
    if (!journal.state().equals(state)) {
      return state = journal.state();
    }
    return null;
  }
}
