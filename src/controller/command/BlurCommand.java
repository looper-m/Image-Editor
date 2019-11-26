package controller.command;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import controller.GeneratorCommand;
import model.GeneratorModel;
import model.images.Filter;
import model.images.FilterImage;

public class BlurCommand implements GeneratorCommand {
  private final String parameters;

  public BlurCommand(String parameters) {
    this.parameters = parameters;
  }

  @Override
  public BufferedImage executeCommand(BufferedImage input) {
    return new FilterImage(input, Filter.BLUR).generateImage();
  }
}
