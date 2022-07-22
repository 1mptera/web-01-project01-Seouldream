package buttons;

import com.mommoo.flat.button.*;
import models.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SaveButton extends FlatButton {
  private JFrame writingFrame;
  private JTextField titleTextField;
  private JTextArea writingTextArea;
  private JPanel contentPanel;
  private List<Comment> publicComments;


  public SaveButton(Account account, JFrame writingFrame, JTextField titleTextField,JTextArea writingTextArea, List<Journal> journals) {
    this.writingFrame = writingFrame;
    this.setText("저장하기");

    this.addActionListener(saveButtonEvent -> {
     String title =  titleTextField.getText();
     String writingContent = writingTextArea.getText();
     Journal publicJournal = new Journal(account,title, writingContent);
     publicJournal.switchOn();

     journals.add(publicJournal);
     writingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
     writingFrame.setVisible(false);
    });
  }

  public SaveButton(Account account,JFrame messageFrame,String receiver
      ,JTextArea messageTextArea,List<Message> messages) {
    this.setText("저장하기");

    this.addActionListener(saveButtonEvent -> {
      String text = messageTextArea.getText();
      Message message = new Message(account,receiver,text);
      message.switchOn();

      messages.add(message);
      writingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      writingFrame.setVisible(false);
    });
  }

  public SaveButton(Account account,JFrame writingFrame,Journal journal,
        JTextField titleTextField,JTextArea writingTextArea,String onlyForMe) {
    this.writingFrame = writingFrame;
    this.setText("수정내용 저장하기");

    this.addActionListener(saveButtonEvent -> {
      journal.switchOn();
      journal.modify();
      writingFrame.removeAll();
      writingFrame.setVisible(false);
      // 기존창을 지운다
      //새창을 만든다
      openWritingWindow(journal,titleTextField,writingTextArea);

    });
  }

  public void openWritingWindow(Journal journal,
                         JTextField titleTextField,JTextArea writingTextArea){
    writingFrame = new JFrame("오늘의 일기");
    writingFrame.setSize(400, 500);
    writingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    writingFrame.setLocationRelativeTo(this);

    JPanel framePanel = new JPanel();
    JPanel contentPanel = new JPanel();
    framePanel.setLayout(new BorderLayout());
    contentPanel.setLayout(new BorderLayout());
    framePanel.add(titleTextField, BorderLayout.PAGE_START);
    framePanel.add(contentPanel, BorderLayout.CENTER);
    contentPanel.add(writingTextArea, BorderLayout.CENTER);

    String title = titleTextField.getText();
    String writingContent = writingTextArea.getText();

    titleTextField.setEditable(false);
    writingTextArea.setEditable((false));
    journal.setTitle(title);
    journal.setContent(writingContent);

    framePanel.add(this, BorderLayout.PAGE_END);

    writingFrame.add(framePanel);
    writingFrame.setVisible(true);
  }

}

