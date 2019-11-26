package controller.command;

import java.awt.image.BufferedImage;

import controller.GeneratorCommand;
import model.images.Filter;
import model.images.MosaicFilter;

/**
 * This command class represents the controller for this specific class.
 */
public class MosaicCommand implements GeneratorCommand {
  private final int seeds;

  /**
   * This constructor checks for parameter presence and syntax and then initializes the seeds.
   *
   * @param parameters argument containing the seed amount
   */
  public MosaicCommand(String parameters) {
    String[] split = parameters.split(":");
    if (split.length < 2) {
      throw new IllegalArgumentException("Bad parameter!");
    }
    if (split[0].trim().equalsIgnoreCase("seeds")) {
      try {
        this.seeds = Integer.parseInt(split[1].trim());
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Bad width parameter!");
      }
    } else {
      throw new IllegalArgumentException(String.format("Unknown parameter passed: %s", parameters));
    }
  }

  @Override
  public BufferedImage executeCommand(BufferedImage input) {
    if (input == null) {
      throw new IllegalArgumentException("No image to work on!");
    }
    return new MosaicFilter(input, Filter.MOSAIC, this.seeds).generateImage();
  }
}
