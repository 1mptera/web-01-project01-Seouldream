package panels;
import com.mommoo.flat.layout.linear.constraints.*;
import models.*;
import org.checkerframework.checker.units.qual.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class CommentsPanel extends JPanel {
  private Journal journal;
  private List<Comment> comments;


  public CommentsPanel(Journal journal, List<Comment> comments) {
    this.journal = journal;
    this.comments = comments;


    for (Comment comment : comments) {
      if (comment.state().equals("DELETED") ||
          !Objects.equals(comment.id(), journal.id())) {
        continue;
      }
      this.add(new CommentPanel(comment),
          new LinearConstraints().setLinearSpace(LinearSpace.MATCH_PARENT));
    }

    this.setVisible(true);
  }
}
