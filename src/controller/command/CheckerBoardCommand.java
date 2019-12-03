package controller.command;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import controller.GeneratorCommand;
import model.patterns.CheckerBoard;

/**
 * This command class represents the controller for this specific class.
 */
public class CheckerBoardCommand implements GeneratorCommand {
  private int width = 800;
  private int squareWidth = -1;

  /**
   * This constructor checks for parameter presence and syntax and then initializes the width of the
   * image or individual square width.
   *
   * @param parameters argument containing the width of the checkerboard or one square in it.
   */
  public CheckerBoardCommand(String parameters) {
    Set<String> options = new HashSet<>();
    options.add("width");
    options.add("square_width");

    String[] split = parameters.split("\\s+");
    String[] keyValue = split[0].split(":");
    if (keyValue.length < 2) {
      throw new IllegalArgumentException("Bad key value pair!");
    }
    if (options.contains(keyValue[0].trim())) {
      if (keyValue[0].trim().equalsIgnoreCase("width")) {
        try {
          this.width = Integer.parseInt(keyValue[1].trim());
        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Bad width parameter!");
        }
      } else {
        try {
          this.squareWidth = Integer.parseInt(keyValue[1].trim());
        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Bad square_width parameter!");
        }
      }
    } else {
      throw new IllegalArgumentException(String.format("Unknown parameter passed: %s",
              parameters));
    }

  }

  @Override
  public BufferedImage executeCommand(BufferedImage input) {
    if(squareWidth == -1) {
      return CheckerBoard.getBuilder()
              .imageWidth(this.width)
              .build()
              .generateImage();
    } else {
      return CheckerBoard.getBuilder()
              .squareSize(this.squareWidth)
              .build()
              .generateImage();
    }
  }
}
