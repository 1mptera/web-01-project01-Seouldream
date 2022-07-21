package panels;

import com.mommoo.flat.component.*;
import com.mommoo.flat.text.label.*;
import com.mommoo.flat.text.textfield.*;
import models.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class CommentPanel extends FlatPanel {
  private Journal journal;
  private Comment comment;


  public CommentPanel(Comment comment) {
    this.comment = comment;

    this.setSize(40,5);
    this.setBackground(Color.ORANGE);
    JLabel label = new JLabel(comment.nickName());
    JTextField textField = new JTextField(30);
    textField.setText(comment.content());
    textField.setEditable(false);

    label.setSize(10,5);
    textField.setSize(30,5);

    this.add(label);

    this.add(textField);

    this.setVisible(true);
  }
}
