package controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.command.BlurCommand;
import controller.command.CheckerBoardCommand;
import controller.command.DitherCommand;
import controller.command.FranceFlagCommand;
import controller.command.GreeceFlagCommand;
import controller.command.GreyscaleCommand;
import controller.command.MosaicCommand;
import controller.command.RainbowCommand;
import controller.command.SepiaCommand;
import controller.command.SharpenCommand;
import controller.command.SwitzerlandFlagCommand;

/**
 * This {@code ActionListener} class listens for events from image processing components of the GUI
 * view.
 */
public class OperationsActionListener implements ActionListener {
  private GUIController control;
  private String paramsData = "";

  OperationsActionListener(GUIController control) {
    this.control = control;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand().toLowerCase();

    if (command.equals("undo")) {
      if (!control.undoStack.empty()) {
        control.redoStack.push(control.view.getImageOnCanvas());
        control.view.setImageOnCanvas(control.undoStack.pop());
      }
      return;
    } else if (command.equals("redo")) {
      if (!control.redoStack.empty()) {
        control.undoStack.push(control.view.getImageOnCanvas());
        control.view.setImageOnCanvas(control.redoStack.pop());
      }
      return;
    }

    Set<String> imageCommandNames = new HashSet<>(Arrays.asList("sharpen", "blur", "greyscale",
            "sepia", "mosaic", "dither"));
    if (control.view.getImageOnCanvas() == null && imageCommandNames.contains(command)) {
      JOptionPane.showMessageDialog(null, "No image on canvas!", "Error",
              JOptionPane.ERROR_MESSAGE);
      return;
    }

    Map<String, Function<String, GeneratorCommand>> knownCommands = new HashMap<>();
    knownCommands.put("sharpen", SharpenCommand::new);
    knownCommands.put("blur", BlurCommand::new);
    knownCommands.put("greyscale", GreyscaleCommand::new);
    knownCommands.put("sepia", SepiaCommand::new);
    knownCommands.put("france", FranceFlagCommand::new);
    knownCommands.put("greece", GreeceFlagCommand::new);
    knownCommands.put("mosaic", MosaicCommand::new);
    knownCommands.put("dither", DitherCommand::new);
    knownCommands.put("switzerland", SwitzerlandFlagCommand::new);
    knownCommands.put("checkerboard", CheckerBoardCommand::new);
    knownCommands.put("rainbow", RainbowCommand::new);

    Function<String, GeneratorCommand> generatorCommand = knownCommands.getOrDefault(command,
            null);
    if (generatorCommand == null) {
      JOptionPane.showMessageDialog(null, "Invalid command!", "Fatal Error",
              JOptionPane.ERROR_MESSAGE);
      return;
    }
    if (command.equals("mosaic")
            && !getMosaicParams()) {
      return;
    } else if (command.equals("france")
            && !getFlagParams(3)) {
      return;
    } else if (command.equals("greece")
            && !getFlagParams(27)) {
      return;
    } else if (command.equals("switzerland")
            && !getFlagParams(32)) {
      return;
    } else if (command.equals("rainbow")
            && !getRainbowParams()) {
      return;
    } else if (command.equals("checkerboard")
            && !getCheckerParams()) {
      return;
    }
    control.redoStack.removeAllElements();
    BufferedImage currentImage = control.view.getImageOnCanvas();
    control.undoStack.push(currentImage);

    GeneratorCommand c = generatorCommand.apply(paramsData);
    control.view.setImageOnCanvas(c.executeCommand(currentImage));
  }

  private boolean getMosaicParams() {
    String result = JOptionPane.showInputDialog("Enter seed amount:");
    if (result == null) {
      return false;
    }
    int value;
    try {
      value = Integer.parseInt(result);
      if (value <= 0) {
        throw new NumberFormatException();
      }
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Invalid seed value!", "Error",
              JOptionPane.ERROR_MESSAGE);
      return false;
    }
    paramsData = "seeds:" + value;
    return true;
  }

  private boolean getFlagParams(int proportion) {
    String result =
            JOptionPane.showInputDialog("Enter flag width: (Must be divisible by " + proportion + ")");
    if (result == null) {
      return false;
    }
    int value;
    try {
      value = Integer.parseInt(result);
      if (value <= 0 || value % proportion != 0) {
        throw new NumberFormatException();
      }
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Invalid width parameter!", "Error",
              JOptionPane.ERROR_MESSAGE);
      return false;
    }
    paramsData = "width:" + value;
    return true;
  }

  private boolean getRainbowParams() {
    int width;
    int height;
    String orientation;

    JTextField widthField = new JTextField(10);
    JTextField heightField = new JTextField(10);
    widthField.setText("700");
    heightField.setText("700");

    JRadioButton horizontal = new JRadioButton("horizontal");
    horizontal.setActionCommand("horizontal");
    JRadioButton vertical = new JRadioButton("vertical");
    vertical.setActionCommand("vertical");
    ButtonGroup orientationGroup = new ButtonGroup();
    horizontal.doClick();
    orientationGroup.add(horizontal);
    orientationGroup.add(vertical);

    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
    inputPanel.add(new JLabel("Enter parameters:"));
    inputPanel.add(Box.createVerticalStrut(10));
    inputPanel.add(new JLabel("Width:"));
    inputPanel.add(widthField);
    inputPanel.add(Box.createVerticalStrut(5));
    inputPanel.add(new JLabel("Height:"));
    inputPanel.add(heightField);
    inputPanel.add(Box.createVerticalStrut(10));
    inputPanel.add(horizontal);
    JLabel infoH = new JLabel("height must be divisible by 7");
    infoH.setFont(new Font("Helvetica", Font.ITALIC, 10));
    inputPanel.add(infoH);
    inputPanel.add(Box.createVerticalStrut(10));
    inputPanel.add(vertical);
    JLabel infoW = new JLabel("width must be divisible by 7");
    infoW.setFont(new Font("Helvetica", Font.ITALIC, 10));
    inputPanel.add(infoW);

    widthField.addAncestorListener(new RequestFocusListener());

    int option = JOptionPane.showConfirmDialog(null, inputPanel,
            "Input", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
      orientation = orientationGroup.getSelection().getActionCommand();
      try {
        width = Integer.parseInt(widthField.getText());
        height = Integer.parseInt(heightField.getText());
        if ((width <= 0 || height <= 0)
                || (orientation.equals("horizontal") && height % 7 != 0)
                || (orientation.equals("vertical") && width % 7 != 0)) {
          throw new NumberFormatException();
        }
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid parameters passed!", "Error",
                JOptionPane.ERROR_MESSAGE);
        return false;
      }
    } else {
      return false;
    }
    paramsData = "width:" + width
            + " height:" + height
            + " orientation:" + orientation;
    return true;
  }

  private boolean getCheckerParams() {
    int width;
    boolean smallSquare;

    JTextField widthField = new JTextField(10);

    JCheckBox sqBox = new JCheckBox("Smaller square width");
    sqBox.setActionCommand("sqWidth");

    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
    inputPanel.add(new JLabel("Enter parameters:"));
    inputPanel.add(Box.createVerticalStrut(10));
    inputPanel.add(new JLabel("Width:"));
    inputPanel.add(widthField);
    inputPanel.add(Box.createVerticalStrut(5));
    inputPanel.add(sqBox);
    JLabel infoH = new JLabel("If unchecked, width must be divisible by 8");
    infoH.setFont(new Font("Helvetica", Font.ITALIC, 10));

    widthField.addAncestorListener(new RequestFocusListener());

    int option = JOptionPane.showConfirmDialog(null, inputPanel,
            "Input", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
      smallSquare = sqBox.isSelected();
      try {
        width = Integer.parseInt(widthField.getText());
        if ((width <= 0)
                || (!smallSquare && width % 8 != 0)) {
          throw new NumberFormatException();
        }
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid parameters passed!", "Error",
                JOptionPane.ERROR_MESSAGE);
        return false;
      }
    } else {
      return false;
    }
    if (smallSquare) {
      paramsData = "square_width:" + width;
    } else {
      paramsData = "width:" + width;
    }
    return true;
  }
}
