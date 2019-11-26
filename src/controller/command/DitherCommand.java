package controller.command;

import java.awt.image.BufferedImage;

import controller.GeneratorCommand;
import model.images.DitherFilter;
import model.images.Filter;

/**
 * This command class represents the controller for this specific class.
 */
public class DitherCommand implements GeneratorCommand {

  /**
   * This constructor initializes the parameters.
   *
   * @param parameters nothing as of now. Extendable in the future.
   */
  public DitherCommand(String parameters) {
    /* Parameters can be added and extendable in the future */
  }

  @Override
  public BufferedImage executeCommand(BufferedImage input) {
    if (input == null) {
      throw new IllegalArgumentException("No image to work on!");
    }
    return new DitherFilter(input, Filter.DITHER).generateImage();
  }
}
