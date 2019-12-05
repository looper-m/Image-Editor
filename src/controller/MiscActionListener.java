package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * This {@code ActionListener} class listens for events from all of the GUI view components other
 * than file handling and image processing operations.
 */
public class MiscActionListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand().toLowerCase();

    if (command.equals("info")) {
      String infoMessage =
              "Fun with Images! Version 3.0\n\n"
                      + "Was real fun working on this GUI albeit sandwiched with frustrating turd"
                      + " code.\n"
                      + "I mean, God, I had not one idea that i had to type so much fucking code "
                      + "for so \nlittle shit on the screen! But now that its done, I am a happy "
                      + " potato..\n"
                      + "P.S. Thank you Mr. Shesh! :)";
      JOptionPane.showMessageDialog(null, infoMessage, "About",
              JOptionPane.INFORMATION_MESSAGE);
    } else if (command.equals("exit")) {
      System.exit(0);
    }
  }
}
