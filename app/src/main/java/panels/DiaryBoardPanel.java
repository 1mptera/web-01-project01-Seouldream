package panels;
import buttons.*;
import com.mommoo.flat.button.*;
import com.mommoo.flat.frame.*;
import models.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class DiaryBoardPanel extends JPanel {
  private List<Journal> publicJournals;
  private JPanel contentPanel = new JPanel();
  private JFrame writingFrame;
  private JTextField titleTextField;
  private JTextArea writingTextArea;
  private String title;
  private String writingContent;
 // private Account account;

  public DiaryBoardPanel(List<Journal> publicJournals) throws IOException {
    this.publicJournals = publicJournals;

    this.setLayout(new BorderLayout());
    this.add(contentPanel,BorderLayout.CENTER);

    showContentPanel(publicJournals);

    FlatButton letsGoWriteButton = new FlatButton("일기 쓰러 가기");

    this.add(letsGoWriteButton, BorderLayout.PAGE_START);

    showContentPanel(publicJournals);

    letsGoWriteButton.addActionListener(letsGoWriteButtonEvent -> {

      openWritingWindow();

    });
  }

  private void showContentPanel(List<Journal> publicJournals) throws IOException {
    if (contentPanel != null) {
      contentPanel.removeAll();
    }

    contentPanel = new WritingListPanel(publicJournals);

    this.add(contentPanel);

    contentPanel.setVisible((false));
    contentPanel.setVisible(true);
  }

  public void openWritingWindow() {
    writingFrame = new JFrame("오늘의 일기");
    writingFrame.setSize(400, 500);
    writingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    writingFrame.setLocationRelativeTo(this);

    JPanel framePanel = new JPanel();
    titleTextField = new JTextField(10);
    writingTextArea = new JTextArea(30, 10);

    framePanel.setLayout(new BorderLayout());

    framePanel.add(titleTextField, BorderLayout.PAGE_START);
    framePanel.add(writingTextArea, BorderLayout.CENTER);

    SaveButton saveButton = new SaveButton(writingFrame,titleTextField,writingTextArea,publicJournals);
    framePanel.add(saveButton, BorderLayout.PAGE_END);
    saveButton.setVisible(true);
    writingFrame.add(framePanel);
    writingFrame.setVisible(true);
  }
}

