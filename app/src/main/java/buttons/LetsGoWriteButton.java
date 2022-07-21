package buttons;

import com.mommoo.flat.button.*;
import models.*;
import panels.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

public class LetsGoWriteButton extends FlatButton {
  JFrame writingFrame;
  JTextField titleTextField;
  JTextArea writingTextArea;
  private List<Journal> journals;
  private JPanel contentPanel;

  public LetsGoWriteButton(List<Journal> journals,JPanel contentPanel) {
    this.journals = journals;
    this.contentPanel = contentPanel;
    this.setText("나만의 비밀일기 쓰러가기");

    this.addActionListener(letsGoWriteButtonEvent -> {
      FlatButton saveButton = new FlatButton("저장하기");
      openWritingWindow(saveButton);

      saveButton.addActionListener(saveButtonEvent -> {
        String title = titleTextField.getText();
        String writingContent = writingTextArea.getText();

        Journal journal = new Journal(title, writingContent);

        journals.add(journal);
        writingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        writingFrame.setVisible(false);

        try {
          showContentPanel(journals);
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      });
    });
  }

  private void openWritingWindow(FlatButton button) {
    writingFrame = new JFrame("나만 알고싶은 비밀 일기");
    writingFrame.setSize(400, 500);
    writingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    writingFrame.setLocationRelativeTo(this);

    JPanel framePanel = new JPanel();
    titleTextField = new JTextField(10);
    writingTextArea = new JTextArea(30, 10);

    framePanel.setLayout(new BorderLayout());

    framePanel.add(titleTextField, BorderLayout.PAGE_START);
    framePanel.add(writingTextArea, BorderLayout.CENTER);
    framePanel.add(button, BorderLayout.PAGE_END);
    writingFrame.add(framePanel);
    writingFrame.setVisible(true);
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
