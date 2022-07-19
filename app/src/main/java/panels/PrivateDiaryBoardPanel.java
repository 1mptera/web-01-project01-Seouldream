package panels;
import models.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PrivateDiaryBoardPanel extends JPanel {
  private List<Journal> privateJournals;
  private JPanel contentPanel;
  private JFrame writingFrame;
  private JTextField titleTextField;
  private JTextArea writingTextArea;
  private String title;
  private String writingContent;

  public PrivateDiaryBoardPanel(List<Journal> privateJournals) {
    this.privateJournals = privateJournals;

    this.setLayout(new BorderLayout());

    JButton letsGoWriteButton = new JButton("나만의 비밀일기 쓰러가기");

    this.add(letsGoWriteButton, BorderLayout.PAGE_START);

    showContentPanel(privateJournals);

    letsGoWriteButton.addActionListener(letsGoWriteButtonEvent -> {
      JButton saveButton = new JButton("저장하기");
      openWritingWindow(saveButton);

      saveButton.addActionListener(saveButtonEvent -> {
        title = titleTextField.getText();
        writingContent = writingTextArea.getText();

        Journal journal = new Journal(title, writingContent);


        privateJournals.add(journal);
        writingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        writingFrame.setVisible(false);

        showContentPanel(privateJournals);

      });
    });
  }

  private void showContentPanel(List<Journal> privateJournals) {
    if (contentPanel != null) {
      contentPanel.removeAll();
    }
    contentPanel = new WritingListPanel(privateJournals);

    this.add(contentPanel);

    contentPanel.setVisible((false));
    contentPanel.setVisible(true);
  }

  private void openWritingWindow(JButton button) {
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
}

