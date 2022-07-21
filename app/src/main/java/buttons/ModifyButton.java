package buttons;

import com.mommoo.flat.button.*;
import models.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ModifyButton extends FlatButton {
  private JFrame writingFrame;
  private JTextField titleTextField;
  private JTextArea writingTextArea;

  public ModifyButton(Journal journal, List<Journal> journals,
                      List<Comment> comments) {
    this.setText("수정하기");

    JButton modifyButton = this;

    openWritingWindow(journal,modifyButton,comments);

    this.addActionListener(modifyButtonEvent -> {
      writingFrame.removeAll();
      writingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      writingFrame.setVisible(false);

      JButton saveButton = new SaveButton(writingFrame,journal,journals,"onlyForMe");

      openWritingWindow(journal, saveButton, comments);
      titleTextField.setEditable(true);
      writingTextArea.setEditable(true);

    });
  }

  public void openWritingWindow(Journal journal, JButton button, List<Comment> comments) {
    writingFrame = new JFrame("오늘의 일기");
    writingFrame.setSize(400, 500);
    writingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    writingFrame.setLocationRelativeTo(this);

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
    titleTextField.setEditable(true);
    writingTextArea.setEditable((true));

    writingFrame.add(framePanel);
    writingFrame.setVisible(true);
  }
}

