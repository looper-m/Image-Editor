package controller.command;

import java.awt.image.BufferedImage;

import controller.GeneratorCommand;
import model.images.Filter;
import model.images.MosaicFilter;

public class MosaicCommand implements GeneratorCommand {
  private final int seeds;

  public MosaicCommand(String parameters) {
    String[] split = parameters.split(":");
    if(split.length < 2) {
      throw new IllegalArgumentException("Bad parameter!");
    }
    if(split[0].trim().equalsIgnoreCase("seeds")) {
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
    return new MosaicFilter(input, Filter.MOSAIC, this.seeds).generateImage();
  }
}
