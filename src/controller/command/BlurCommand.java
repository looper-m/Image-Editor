package controller.command;

import java.awt.image.BufferedImage;

import controller.GeneratorCommand;
import model.images.Filter;
import model.images.FilterImage;

/**
 * This command class represents the controller for this specific class.
 */
public class BlurCommand implements GeneratorCommand {
  private final String parameters;

  /**
   * This constructor initializes the parameters.
   *
   * @param parameters nothing as of now. Extendable in the future.
   */
  public BlurCommand(String parameters) {
    this.parameters = parameters;
  }

  @Override
  public BufferedImage executeCommand(BufferedImage input) {
    if (input == null) {
      throw new IllegalArgumentException("No image to work on!");
    }
    return new FilterImage(input, Filter.BLUR).generateImage();
  }
}
