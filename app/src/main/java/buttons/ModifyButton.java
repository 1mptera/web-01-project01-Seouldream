package buttons;

import com.mommoo.flat.button.*;
import models.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ModifyButton extends FlatButton {
  private JFrame writingFrame;


  public ModifyButton(JFrame writingFrame,
       JTextField titleTextField, JTextArea writingTextArea,Journal journal,List<Comment> comments) {
    this.writingFrame = writingFrame;
    this.setText("수정하기");

    this.addActionListener(modifyButtonEvent -> {
      if(writingFrame != null) {
      writingFrame.removeAll();
      writingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      writingFrame.setVisible(false); }

      openWritingWindow(journal,titleTextField,writingTextArea,comments);

    });
  }

  public void openWritingWindow(Journal journal,
    JTextField titleTextField,JTextArea writingTextArea, List<Comment> comments) {
    writingFrame = new JFrame("오늘의 일기");
    writingFrame.setSize(400, 500);
    writingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    writingFrame.setLocationRelativeTo(this);


    JPanel framePanel = new JPanel();
    JPanel contentPanel = new JPanel();
    titleTextField = new JTextField(10);
    writingTextArea = new JTextArea(30, 10);
    titleTextField.setText(journal.title());
    writingTextArea.setText(journal.content());

    framePanel.setLayout(new BorderLayout());
    contentPanel.setLayout(new BorderLayout());

    JButton goLeaveACommentButton = new GoLeaveACommentButton(journal, comments);
    JButton saveButton = new SaveButton(writingFrame,journal,titleTextField,writingTextArea,"onlyForMe");

    framePanel.add(titleTextField, BorderLayout.PAGE_START);
    framePanel.add(contentPanel, BorderLayout.CENTER);
    contentPanel.add(writingTextArea, BorderLayout.CENTER);
    contentPanel.add(goLeaveACommentButton, BorderLayout.PAGE_END);
    framePanel.add(saveButton, BorderLayout.PAGE_END);

    writingFrame.add(framePanel);
    writingFrame.setVisible(true);
  }
}

