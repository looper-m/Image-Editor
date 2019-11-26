package controller.command;

import java.awt.image.BufferedImage;

import controller.GeneratorCommand;
import model.images.Transform;
import model.images.TransformImage;

public class GreyscaleCommand implements GeneratorCommand {
  private final String parameters;

  public GreyscaleCommand(String parameters) {
    this.parameters = parameters;
  }

  @Override
  public BufferedImage executeCommand(BufferedImage input) {
    return new TransformImage(input, Transform.GREYSCALE).generateImage();
  }
}
