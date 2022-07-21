package panels;
import buttons.*;
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
  private JPanel contentPanel;
  private JFrame writingFrame;
  private JTextField titleTextField;
  private JTextArea writingTextArea;
  private String title;
  private String writingContent;
  private JPanel contentContainerPanel = new JPanel();
 // private Account account;

  public DiaryBoardPanel(List<Journal> publicJournals) throws IOException {
    this.publicJournals = publicJournals;


    for(Journal journal : publicJournals) {
      if(journal.state().equals(Journal.DELETED)) {`

    }
    if(publicJournals.size())



  }

      showContentPanel(publicJournals);



    this.setLayout(new BorderLayout());
    this.add(contentContainerPanel,BorderLayout.CENTER);

    showContentPanel(publicJournals);


    JButton letsGoWriteButton = new JButton("일기 쓰러 가기");

    this.add(letsGoWriteButton, BorderLayout.PAGE_START);

    showContentPanel(publicJournals);

    letsGoWriteButton.addActionListener(letsGoWriteButtonEvent -> {
      JButton saveButton = new JButton("저장하기");
      openWritingWindow(saveButton);

      saveButton.addActionListener(saveButtonEvent -> {

        title = titleTextField.getText();
        writingContent = writingTextArea.getText();

        Journal publicJournal = new Journal(title, writingContent);

        publicJournals.add(publicJournal);
        writingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        writingFrame.setVisible(false);

        try {
          showContentPanel(publicJournals);
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }

      });
    });
  }


  private void showContentPanel(List<Journal> publicJournals) throws IOException {
    if (contentPanel != null) {
      contentPanel.removeAll();
    }
    contentPanel = new JPanel();
    contentContainerPanel.add(contentPanel);
    contentPanel = new WritingListPanel(publicJournals);

    this.add(contentPanel);

    contentPanel.setVisible((false));
    contentPanel.setVisible(true);
  }

  public void openWritingWindow(JButton button) {
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
    framePanel.add(button, BorderLayout.PAGE_END);
    writingFrame.add(framePanel);
    writingFrame.setVisible(true);
  }
}

