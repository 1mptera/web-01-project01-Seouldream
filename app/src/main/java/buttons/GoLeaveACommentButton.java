package buttons;

import models.*;
import panels.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GoLeaveACommentButton extends JButton {
  private Journal journal;
  private String state;
  private JFrame writingFrame;
  private JTextField titleTextField;
  private JPanel contentPanel;


  private List<Comment> comments;
  public GoLeaveACommentButton(Journal journal, List<Comment> comments) {
    this.setText("댓글 보러 가기");

    this.addActionListener(event -> {
      openCommentsWindow(journal, comments);
    });
  }

  public void openCommentsWindow(Journal journal, List<Comment> comments) {
    writingFrame = new JFrame("오늘의 댓글");
    writingFrame.setSize(500, 600);
    writingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    writingFrame.setLocationRelativeTo(this);
    writingFrame.setLayout(new BorderLayout());

    contentPanel = new CommentsPanel(journal,comments);
    JPanel commentRegisterPanel = new JPanel();
    JTextField textField = new JTextField(10);
    JButton commentRegisterButton = new JButton("나도 공감 한마디!");

    commentRegisterPanel.add(textField);
    commentRegisterPanel.add(commentRegisterButton);
    writingFrame.add(contentPanel,BorderLayout.CENTER);
    writingFrame.add(commentRegisterPanel,BorderLayout.PAGE_END);

    commentRegisterButton.addActionListener(event -> {
      String id = journal.id();
      String nickName = "귀염둥이 inu";
      String content = textField.getText();
      Comment comment = new Comment(id, nickName, content);
      comments.add(comment);

      contentPanel.removeAll();
      contentPanel.setVisible(false);
      contentPanel = new CommentsPanel(journal,comments);
      writingFrame.add(contentPanel);
      writingFrame.setVisible(true);
    });

    contentPanel.removeAll();
    contentPanel.setVisible(false);
    contentPanel = new CommentsPanel(journal,comments);
    writingFrame.add(contentPanel);
    writingFrame.setVisible(true);
  }

}
