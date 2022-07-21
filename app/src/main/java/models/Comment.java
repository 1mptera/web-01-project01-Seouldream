package models;

public class Comment {
  public static final String PUBLISHED = "PUBLISHED";
  public static final String DELETED = "DELETED";
  public static final String MODIFIED = "MODIFIED";

  private String id;
  private String nickName;
  private String content;
  private String state;
  private Journal journal;
  private String journalID;

  public Comment(Journal journal, String nickName, String content) {
    this.journal = journal;
    this.nickName = nickName;
    this.content = content;
    this.id = journal.id();
    this.state = Comment.PUBLISHED;
  }
  public Comment(String journalID,String nickName,String content) {
    this.id = journalID;
    this.nickName = nickName;
    this.content = content;
    this.state = Comment.PUBLISHED;
  }

  public Journal journal() {
    return journal;
  }

  public String id() {
    return id;
  }

  public String nickName() {
    return nickName;
  }

  public String content() {
    return content;
  }

  public String state() {
    return state;
  }

  public void delete() {
    state = Comment.DELETED;
  }

  public void publish() {
    state = Comment.PUBLISHED;
  }
  public void modify() {
    state = Comment.MODIFIED;
  }

  public String toCsvRow() {
    return id + "," + nickName + "," + content + "," + state;
  }
}
