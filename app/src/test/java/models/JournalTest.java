package models;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class JournalTest {
  Journal journal;

  @BeforeEach
  void setup() {
    journal = new Journal("title", "content");
  }

  @Test
  void creation() {
    assertNotNull(journal.id());
    assertEquals("title", journal.title());
    assertEquals("content", journal.content());
    assertEquals(Journal.PUBLISHED, journal.state());
  }

  @Test
  void changeTitle() {
    String modifiedTitle = "MODIFIED_TITLE";

    journal.changeTitle(modifiedTitle);

    assertEquals(modifiedTitle, journal.title());
  }

  @Test
  void changeContent() {
    String modifiedContent = "MODIFIED_CONTENT";

    journal.changeContent(modifiedContent);

    assertEquals(modifiedContent, journal.content());
  }

  @Test
  void delete() {
    assertEquals(Journal.PUBLISHED, journal.state());

    journal.delete();

    assertEquals(Journal.DELETED, journal.state());
  }
}