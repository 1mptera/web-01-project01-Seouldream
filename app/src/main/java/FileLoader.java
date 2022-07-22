import models.*;

import java.io.*;
import java.util.*;

public class FileLoader {
  private String[] words;


  public List<Journal> loadWritings(String fileName) throws FileNotFoundException {
    List<Journal> journals = new ArrayList<>();
    File file = new File(fileName);
    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      Journal journal = parseLine(line);
      journals.add(journal);
    }

    return journals;
  }

  private Journal parseLine(String line) {
    String[] words = line.split(",");
    String accountID = words[0];
    String password = words[1];
    String nickName = words[2];
    String title = words[3];
    String content = words[4];
    String state = words[5];
    for(Account account : MyDiaryLoginMain.accountsList) {
      if(account.ID().equals(accountID) && account.password().equals(password)
        && account.nickName().equals(nickName) )
        return new Journal(account,title, content);
    }
  return null;}

  public void diaryWriter(List<Journal> journals, String newFileName) throws IOException {
    FileWriter fileWriter = new FileWriter(newFileName);

    for (Journal journal : journals) {
      String line = journal.toCsvRow();
      fileWriter.write(line + "\n");
    }
    fileWriter.close();
  }

  public List<Comment> loadComments(String fileName) throws FileNotFoundException {
    List<Comment> comments = new ArrayList<>();
    File file = new File(fileName);
    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      Comment comment = parseCommentLine(line);
      comments.add(comment);
    }
    return comments;
  }

  private Comment parseCommentLine(String line) {
    words = line.split(",");
    String id = words[0];
    String nickName = words[1];
    String content = words[2];
    String state = words[3];
    return new Comment(id, nickName, content);
  }

  public void commentsWriter(List<Comment> comments, String newFileName) throws IOException {
    FileWriter fileWriter = new FileWriter(newFileName);

    for (Comment comment : comments) {
      String line = comment.toCsvRow();
      fileWriter.write(line + "\n");
    }
    fileWriter.close();
  }

  public List<Account> loadAccounts(String fileName) throws FileNotFoundException {
    List<Account> accounts = new ArrayList<>();
    File file = new File(fileName);
    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      Account account = parseAccountLine(line);
      accounts.add(account);
    }
    return accounts;
  }

  private Account parseAccountLine(String line) {
    String[] words = line.split(",");
    String accountID = words[0];
    String password = words[1];
    String nickName = words[2];
    return new Account(accountID, password, nickName);
  }

  public void accountsWriter(List<Account> accountsList, String newFileName) throws IOException {
    FileWriter fileWriter = new FileWriter(newFileName);

    for (Account account : accountsList) {
      String line = account.toCsvRow();
      fileWriter.write(line + "\n");
    }
    fileWriter.close();
  }
}


