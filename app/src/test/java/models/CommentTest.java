package models;

import buttons.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {
  @Test
  void creation() {
    Journal journal = new Journal("title","content");
    Comment comment = new Comment(journal,"김아무개","글을 제대로 씁시다.");

    }
  @Test
  void idCheck() {
    Journal journal = new Journal("title", "content");
    Comment comment = new Comment(journal, "김아무개", "글을 제대로 씁시다.");


    assertEquals(journal.id(), comment.id());
  }
    @Test
    void nickName() {
      Journal journal = new Journal("title","content");
      Comment comment = new Comment(journal,"김아무개","글을 제대로 씁시다.");

      assertEquals("김아무개",comment.nickName());
    }

  @Test
  void journal() {
    Journal journal = new Journal("title","content");
    Comment comment = new Comment(journal,"김아무개","글을 제대로 씁시다.");

    assertEquals(journal,comment.journal());
  }
  @Test
  void state() {
    Journal journal = new Journal("title","content");
    Comment comment = new Comment(journal,"김아무개","글을 제대로 씁시다.");
    assertEquals("PUBLISHED",comment.state());
    comment.delete();
    assertEquals("DELETED",comment.state());
    comment.publish();
    assertEquals("PUBLISHED",comment.state());
    comment.modify();
    assertEquals("MODIFIED",comment.state());
  }
}