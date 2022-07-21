package models;
import java.util.*;

public class Journal {
  public static final String PUBLISHED = "PUBLISHED";
  public static final String DELETED = "DELETED";
  public static final String MODIFIED = "MODIFIED";
  public static String ON = "ON";
  public static String OFF = "OFF";

  private String id;
  private String title;
  private String content;
  private String state;
  private String switchState;

  public Journal(String title, String content) {
    this.id = String.valueOf(UUID.randomUUID());
    this.title = title;
    this.content = content;
    this.state = Journal.PUBLISHED;
    this.switchState = Journal.OFF;
  }

  public Journal(String id, String title, String content, String state) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.state = state;
    this.switchState = Journal.OFF;
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

  public void switchOn() {
    switchState = Journal.ON;
  }

  public void switchOff() {
    switchState = Journal.OFF;
  }

  public String switchState() {
    return switchState;
  }


  @Override
  public String toString() {
    return "(" + title + ": " + state + ")";
  }

  public String toCsvRow() {
    return title + "," + content + "," + state;
  }
}