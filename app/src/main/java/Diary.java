import panels.*;

import javax.swing.*;
import java.awt.*;

public class Diary {
  private JFrame frame;

  private JPanel menu;
  private JPanel contentPanel;

  public static void main(String[] args) {
    Diary application = new Diary();

    application.run();
  }

  public void run() {
    frame = new JFrame("Show me the diary");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 500);

    menu = new JPanel();
    menu.setLayout(new FlowLayout());


    JButton myDiaryButton = new JButton("my diary");

    JButton messengerButton = new JButton("messenger");

    menu.add(createYourDiaryButton());

    menu.add(myDiaryButton);
    menu.add(messengerButton);

    frame.add(menu, BorderLayout.PAGE_START);

    frame.setVisible(true);
  }

  private JButton createYourDiaryButton() {
    JButton yourDiaryButton = new JButton("Show your diary");
    yourDiaryButton.addActionListener(event -> {
      if (contentPanel != null) {
        frame.remove(contentPanel);
      }

      contentPanel = new MyDiaryPanel();

      frame.add(contentPanel);

      frame.setVisible(true);
    });

    return yourDiaryButton;
  }

}
