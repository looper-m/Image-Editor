package controller.command;

import java.awt.image.BufferedImage;

import controller.GeneratorCommand;
import model.flags.Switzerland;

public class SwitzerlandFlagCommand implements GeneratorCommand {
  private final int flagWidth;

  public SwitzerlandFlagCommand(String parameters) {
    String[] split = parameters.split(":");
    if(split.length < 2) {
      throw new IllegalArgumentException("Bad parameter!");
    }
    if(split[0].trim().equalsIgnoreCase("width")) {
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
