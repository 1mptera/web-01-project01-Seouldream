package utils;

import models.*;

import java.io.*;
import java.util.*;

public class Fileloader {
  private String[] words;

  public List<Journal> loadWritings(String fileName) throws FileNotFoundException {
    List<Journal> journals = new ArrayList<>();
    File file = new File(fileName);
    Scanner scanner = new Scanner(file);
    while(scanner.hasNextLine()) {
      String line = scanner.nextLine();

      Journal journal = parseLine(line);
      journals.add(journal);
    }

    return journals;
  }

  public List<Comment> loadComments(String fileName) throws FileNotFoundException {
    List<Comment> comments = new ArrayList<>();
    File file = new File(fileName);
    Scanner scanner = new Scanner(file);
    while(scanner.hasNextLine()) {
      String line = scanner.nextLine();
     Comment comment = parseCommentLine(line);
     comments.add(comment);
    }

    return comments;
  }

  public void diaryWriter(List<Journal> journals,String newFileName) throws IOException {
    FileWriter fileWriter = new FileWriter(newFileName);

    for(Journal journal : journals) {
      String line = journal.toCsvRow();
      fileWriter.write(line + "\n");
    }
    fileWriter.close();

  }

  public void commentsWriter(List<Comment> comments,String newFileName) throws IOException {
    FileWriter fileWriter = new FileWriter(newFileName);

    for(Comment  comment : comments) {
      String line = comment.toCsvRow();
      fileWriter.write(line + "\n");
    }
    fileWriter.close();

  }

  private Journal parseLine(String line) {
    String[] words = line.split(",");
    String title = words[0];
    String content = words[1];
    String state = words[2];
    return new Journal(title,content);
  }

  private Comment parseCommentLine(String line) {
    words = line.split(",");
    String id = words[0];
    String nickName = words[1];
    String content = words[2];
    String state = words[3];
    return new Comment(id,nickName,content);
  }

  }


