package utils;

import models.*;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Fileloader {

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

  public void diaryWriter(List<Journal> journals,String newFileName) throws IOException {
    FileWriter fileWriter = new FileWriter(newFileName);

    for(Journal journal : journals) {
      String line = journal.toCsvRow();
      fileWriter.write(line + "\n");
    }
    fileWriter.close();

  }

  private Journal parseLine(String line) {
   /* if(line.isBlank()) {
      String title = "";
      String content ="";
      String state = Journal.DELETED;
      return new Journal(title,content);
    }*/
    String[] words = line.split(",");
    String title = words[0];
    String content = words[1];
    String state = words[2];
    return new Journal(title,content);
  }
}

