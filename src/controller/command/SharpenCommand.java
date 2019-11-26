package controller.command;

import java.awt.image.BufferedImage;

import controller.GeneratorCommand;
import model.images.Filter;
import model.images.FilterImage;

/**
 * This command class represents the controller for this specific class.
 */
public class SharpenCommand implements GeneratorCommand {

  /**
   * This constructor initializes the parameters.
   *
   * @param parameters nothing as of now. Extendable in the future.
   */
  public SharpenCommand(String parameters) {
    /* Parameters can be added and extendable in the future */
  }

  @Override
  public BufferedImage executeCommand(BufferedImage input) {
    if (input == null) {
      throw new IllegalArgumentException("No image to work on!");
    }
    return new FilterImage(input, Filter.SHARPEN).generateImage();
  }
}
