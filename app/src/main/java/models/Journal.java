package models;
import java.util.*;

public class Journal {
  public static final String PUBLISHED = "PUBLISHED";
  public static final String DELETED = "DELETED";
  public static final String MODIFIED = "MODIFIED";

  private String id;
  private String title;
  private String content;
  private String state;

  public Journal(String title, String content) {
    this.id = String.valueOf(UUID.randomUUID());
    this.title = title;
    this.content = content;
    this.state = Journal.PUBLISHED;
  }

  public Journal(String id, String title, String content, String state) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.state = state;
  }

  public String id() {
    return id;
  }

  public String title() {
    return title;
  }

  public String content() {
    return content;
  }

  public String state() {
    return state;
  }

  public void changeTitle(String title) {
    this.title = title;
  }

  public void changeContent(String content) {
    this.content = content;
  }

  public void publish() {
    state = Journal.PUBLISHED;
  }

  public void delete() {
    state = Journal.DELETED;
  }

  public void modify() {
    state = Journal.MODIFIED;
  }

  @Override
  public String toString() {
    return "(" + title + ": " + state + ")";
  }

  public String toCsvRow() {
    return title + "," + content + "," + state;
  }
}