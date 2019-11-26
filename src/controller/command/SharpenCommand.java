package controller.command;

import java.awt.image.BufferedImage;

import controller.GeneratorCommand;
import model.images.Filter;
import model.images.FilterImage;

public class SharpenCommand implements GeneratorCommand {
  private final String parameters;

  public SharpenCommand(String parameters) {
    this.parameters = parameters;
  }

  @Override
  public BufferedImage executeCommand(BufferedImage input) {
    return new FilterImage(input, Filter.SHARPEN).generateImage();
  }
}
