package models;

public class Account {
  private String accountID;
  private String password;
  private String nickName;

  public Account(String accountID, String password, String nickName) {
    this.accountID = accountID;
    this.password = password;
    this.nickName = nickName;
  }

  public String ID() {
    return accountID;
  }

  public String password() {
    return password;
  }

  public String nickName() {
    return nickName;
  }

  public String toCsvRow() {
    return accountID + "," + password + ","  + nickName;
  }
}
