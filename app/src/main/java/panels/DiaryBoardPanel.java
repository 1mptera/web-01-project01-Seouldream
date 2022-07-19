package panels;

import buttons.*;
import models.*;

import javax.swing.*;
import java.awt.*;
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
 // private Account account;

  public DiaryBoardPanel(List<Journal> publicJournals) {
    this.publicJournals = publicJournals;
    // this.account = account;

    this.setLayout(new BorderLayout());

    JButton letsGoWriteButton = new JButton("일기 쓰러 가기");

    this.add(letsGoWriteButton, BorderLayout.PAGE_START);

    showContentPanel(publicJournals);

    letsGoWriteButton.addActionListener(letsGoWriteButtonEvent -> {
      JButton saveButton = new JButton("저장하기");
      openWritingWindow(saveButton);

      saveButton.addActionListener(saveButtonEvent -> {

        Journal journal = new Journal(title, writingContent);

        title = titleTextField.getText();
        writingContent = writingTextArea.getText();

        publicJournals.add(journal);
        writingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        writingFrame.setVisible(false);

        showContentPanel(publicJournals);

      });
    });
  }

  private void showContentPanel(List<Journal> publicJournals) {
    if (contentPanel != null) {
      contentPanel.removeAll();
    }
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

  /*  writingContentButton.addActionListener(event -> {
      JButton deleteButton = new JButton("삭제하기");
      openWritingWindow(deleteButton);
      titleTextField.setText(title);
      writingTextArea.setText(writingContent);


      framePanel.add(deleteModifyPanel, BorderLayout.PAGE_END);
      writingFrame.setVisible(true);

      deleteButton.addActionListener(event2 -> {
        journal.delete();
        writingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        writingFrame.setVisible(false);
        writingListPanel = new WritingListPanel();
        //패널 뉴 -> 버튼들 (패널1)-> 지운다 ,상태바꾼다 -> 셋비지블 보여라 (패널1)->
        refresh();
      });
      modifyButton.addActionListener(event3 -> {
        journal.modify();
        titleTextField.setEditable(true);
        writingTextArea.setEditable(true);
        deleteModifyPanel.setLayout(new GridLayout(1, 3));
        JButton modifySaveButton = new JButton("저장하기");
        deleteModifyPanel.add(modifySaveButton);
        writingFrame.setVisible(true);

        modifySaveButton.addActionListener(event4 -> {
          title = titleTextField.getText();
          titleTextField.setText(title);
          writingContent = writingTextArea.getText();
          writingTextArea.setText(writingContent);
          titleTextField.setEditable(false);
          writingTextArea.setEditable(false);
          //라이팅 생성

          writingListPanel = new WritingListPanel(DiaryBoardPanel.this.publicJournals, writingContentButton);

          refresh();
        });

      });
    });
  }


}
*//*});

    });
  }
  private void refresh() {
    this.add(writingListPanel);
    writingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    writingFrame.setVisible(false);
    writingListPanel.setVisible((false));
    writingListPanel.setVisible(true);
  }
  private void refreshDisplay() {
    writingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    writingFrame.setVisible(false);
    writingListPanel.setVisible((false));
    writingListPanel.setVisible(true);
  }
}*//*
 */

