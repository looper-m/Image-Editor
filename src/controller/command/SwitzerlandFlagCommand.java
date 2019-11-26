package controller.command;

import java.awt.image.BufferedImage;

import controller.GeneratorCommand;
import model.flags.Switzerland;

/**
 * This command class represents the controller for this specific class.
 */
public class SwitzerlandFlagCommand implements GeneratorCommand {
  private final int flagWidth;

  /**
   * This constructor checks for parameter presence and syntax and then initializes the width.
   *
   * @param parameters argument containing the width of the flag
   */
  public SwitzerlandFlagCommand(String parameters) {
    String[] split = parameters.split(":");
    if (split.length < 2) {
      throw new IllegalArgumentException("Bad parameter!");
    }
    if (split[0].trim().equalsIgnoreCase("width")) {
      try {
        this.flagWidth = Integer.parseInt(split[1].trim());
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Bad width parameter!");
      }
    } else {
      throw new IllegalArgumentException(String.format("Unknown parameter passed: %s", parameters));
    }
  }

  @Override
  public BufferedImage executeCommand(BufferedImage input) {
    return new Switzerland(flagWidth).generateImage();
  }
}
