package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Stack;

import javax.swing.*;

import model.GeneratorModel;
import view.GeneratorView;
import view.GeneratorViewGUI;

public class GUIController {
  GeneratorModel model;
  GeneratorView view;
  Stack<BufferedImage> history = new Stack<>();

  public GUIController( GeneratorView view) {
//    this.model = model;
    this.view = view;
    this.view.addActionListenerForFile(new FileActionListener(this));
    this.view.addActionListenerForOperations(new OperationsActionListener(this));
  }

  public static void main(String[] args) throws ClassNotFoundException,
          UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException,
          IOException {
    GeneratorView view = new GeneratorViewGUI();
    GUIController control = new GUIController(view);
  }
}
