package models;

public class Message {
  public static final String PUBLISHED = "PUBLISHED";
  public static final String DELETED = "DELETED";
  public static final String MODIFIED = "MODIFIED";
  public static String ON = "ON";
  public static String OFF = "OFF";
  private String switchState;
  private Account receiver;
  private Account account;
  private String texts;
  private String state;
  private String title;

  public Message(Account account,String receiver, String texts) {
    this.account = account;
    this.title = account.ID();
    this.texts = texts;
    this.switchState = Message.OFF;
  }

  public String state() {
    return state;
  }

  public void switchOn() {
    switchState = Message.ON;
  }

  public String title() {
    return title;
  }

  public String texts() {
    return texts;
  }

  public void delete() {
    state = Message.DELETED;
  }

  public void switchOff() {
    switchState = Message.OFF;
  }

  public String switchState() {
    return switchState;
  }
}

