package controller.command;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import controller.GeneratorCommand;
import model.patterns.Orientation;
import model.patterns.Rainbow;

public class RainbowCommand implements GeneratorCommand {
  private int width = 700;
  private int height = 700;
  private Orientation orientation = Orientation.HORIZONTAL;

  public RainbowCommand(String parameters) {
    Set<String> options = new HashSet<>();
    options.add("width");
    options.add("height");
    options.add("orientation");

    String[] split = parameters.split("\\s+");
    for (String option_i : split) {
      String[] keyValue = option_i.split(":");
      if(keyValue.length < 2) {
        throw new IllegalArgumentException("Bad key value pair!");
      }
      if (options.contains(keyValue[0].trim())) {
        if (keyValue[0].trim().equalsIgnoreCase("width")) {
          try {
            this.width = Integer.parseInt(keyValue[1].trim());
          } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Bad width parameter!");
          }
        } else if (keyValue[0].trim().equalsIgnoreCase("height")) {
          try {
            this.height = Integer.parseInt(keyValue[1].trim());
          } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Bad height parameter!");
          }
        } else {
          if (keyValue[1].trim().equalsIgnoreCase("horizontal")) {
            this.orientation = Orientation.HORIZONTAL;
          } else if (keyValue[1].trim().equalsIgnoreCase("vertical")) {
            this.orientation = Orientation.VERTICAL;
          }
        }
      } else {
        throw new IllegalArgumentException(String.format("Unknown parameter passed: %s",
                parameters));
      }
    }
  }

  @Override
  public BufferedImage executeCommand(BufferedImage input) {
    // WIDTH HEIGHT MUST BE DIVISIBLE BY 7
    return Rainbow.getBuilder()
            .imageWidth(this.width)
            .imageHeight(this.height)
            .orientation(this.orientation)
            .build()
            .generateImage();
  }
}
