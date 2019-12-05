package controller;

import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

import javax.swing.UnsupportedLookAndFeelException;

import view.GeneratorView;
import view.GeneratorViewGUI;

/**
 * This class represents the interactive GUI view as the discrete controller class.
 */
public class GUIController {
  GeneratorView view;
  Stack<BufferedImage> undoStack = new Stack<>();
  Stack<BufferedImage> redoStack = new Stack<>();

  GUIController(GeneratorView view) {
    this.view = view;
    this.view.addActionListenerForFile(new FileActionListener(this));
    this.view.addActionListenerForOperations(new OperationsActionListener(this));
    this.view.addActionListenerForMiscellaneous(new MiscActionListener());
  }

  /**
   * The main method that is auto invoked.
   * <p>
   * 1. It executes batch commands from the script file provided (or) 2. It sets up the interactive
   * GUI view and hands it off to {@code this} GUIController class.
   *
   * @param args args[0] with type of invocation: batch -script or -interactive; args[1] with script
   *             file name.
   */
  public static void main(String[] args) throws ClassNotFoundException,
          UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
    if (args[0].toLowerCase().equals("-script")) {
      try {
        new BatchController().execute(new FileReader(args[1]));
      } catch (IOException e) {
        throw new IllegalArgumentException("Bad input!");
      }
    } else if (args[0].toLowerCase().equals("-interactive")) {
      GeneratorView view = new GeneratorViewGUI();
      new GUIController(view);
    } else {
      System.out.println("Usage (either one):\n  -script <script_file>\n  -interactive\n");
      System.out.println("Bad argument! Exiting..");
      System.exit(1);
    }
  }
}
