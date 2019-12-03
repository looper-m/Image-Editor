package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Stack;

import javax.swing.UnsupportedLookAndFeelException;

import model.GeneratorModel;
import view.GeneratorView;
import view.GeneratorViewGUI;

public class GUIController {
  GeneratorModel model;
  GeneratorView view;
  Stack<BufferedImage> undoStack = new Stack<>();
  Stack<BufferedImage> redoStack = new Stack<>();

  GUIController(GeneratorView view) {
    this.view = view;
    this.view.addActionListenerForFile(new FileActionListener(this));
    this.view.addActionListenerForOperations(new OperationsActionListener(this));
    this.view.addActionListenerForMiscellaneous(new MiscActionListener());
  }

  public static void main(String[] args) throws ClassNotFoundException,
          UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
    GeneratorView view = new GeneratorViewGUI();
    new GUIController(view);
  }
}
