package controller.command;

import java.awt.image.BufferedImage;

import controller.GeneratorCommand;
import model.images.DitherFilter;
import model.images.Filter;

public class DitherCommand implements GeneratorCommand {
  private final String parameters;

  public DitherCommand(String parameters) {
    this.parameters = parameters;
  }

  @Override
  public BufferedImage executeCommand(BufferedImage input) {
    return new DitherFilter(input, Filter.DITHER).generateImage();
  }
}
